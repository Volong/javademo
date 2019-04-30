> 来自： [事务隔离级别和MVCC](<https://juejin.im/book/5bffcbc9f265da614b11b731/section/5c923cfcf265da60f00ecaa9>)

#### 事务并发执行遇到的问题

- **脏写 (Dirty Write)**

  一个事务修改了另一个未提交事务修改过的数据

- **脏读 (Dirty Read)**

  一个事务读到了另一个未提交事务修改过的数据

- **不可重复读 (Non-Repeatable Read)**

  一个事务读到了另一个已经提交的事务修改过的数据，并且其他事务每对该数据进行一次修改并提交后，该事务都能读取到最新值

- **幻读 (Phantom)**

  一个事务根据某些条件能够读取一些记录，之后另一个事务向表中插入了符合这些条件的数据，该事务根据同样的条件能够把另一个事务插入的数据读取出来

这些问题所造成的严重性如下：

```mysql
脏写 > 脏读 > 不可重复读 > 幻读
```

#### SQL 标准中的四种隔离级别

- `READ UNCOMMITTED` : 未提交读
- `READ COMMITTED` : 已提交读
- `REPEATABLE READ` : 可重复读
- `SERIALIZABLE` : 可串行化

不同的隔离级别，并发事务可以发生不同严重程度的问题，如下：

|      隔离级别      |  脏读  | 不可重复读 |  幻读  |
| :----------------: | :----: | :--------: | :----: |
| `READ UNCOMMITTED` |  可以  |    可以    |  可以  |
|  `READ COMMITTED`  | 不可以 |    可以    |  可以  |
| `REPEATABLE READ`  | 不可以 |   不可以   |  可以  |
|   `SERIALIZABLE`   | 不可以 |   不可以   | 不可以 |

> 由于**脏写**带来的问题很严重，因此在哪种隔离级别中，都不允许出现

#### MySQL 中支持的四种隔离级别

MySQL 支持 SQL 标准中 4 种隔离级别，但是有些不一样。MySQL 在 `REPEATABLE READ` 隔离级别下，是可以禁止**幻读**问题的发生。

> Oracle 只支持 `READ COMMITTED` 与 `SERIALIZABLE` 隔离级别

MySQL 默认的隔离级别为 `REPEATABLE READ`。

##### 如何设置事务的隔离级别

```mysql
SET [GLOBAL|SESSION] TRANSACTION ISOLATION LEVEL level;
```
level 的取值为 : `REPEATABLE READ` | `READ COMMITTED` | `READ UNCOMMITTED` | `SERIALIZABLE`

- **`GOBAL`** (全局范围)
  - 只对执行完该语句之后产生的会话起作用
  - 当前已经存在的会话无效
- **`SESSION`** (会话范围)
  - 对当前会话的所有后续事务有效
  - 该语句可以在已经开启的事务中执行，不会影响当前正在执行的事务
  - 如果在事务之间执行，则对后续的事务有效
- **不指定上述两个关键字**
  - 只对当前会话中下一个即将开启的事务有效
  - 下一个事务执行完后，后续事务将恢复到之前的隔离级别
  - 该语句不能在已经开启的事务中执行，会报错

### MVCC原理

#### 版本链

对于使用 `InnoDB` 存储引擎的表来说，它的聚簇索引记录中都包含两个必要的隐藏列 : 

- `trx_id` : 每次一个事务对某条聚簇索引记录进行改动时，都会把该事务的`事务 id `赋值给 `trx_id` 隐藏列
- `roll_pointer` : 每次对某条聚簇索引记录进行改动时，都会把旧的版本写入到 `undo 日志`中，然后这个隐藏列就相当于一个指针，可以通过它来找到该记录修改前的信息

每次对记录进行改动，都会记录一条 `undo 日志`，每条 `undo 日志`也都有一个 `roll_pointer` 属性 (`INSERT` 操作对应的 `undo 日志`没有该属性，因为该记录并没有更早的版本)，可以将这些 `undo 日志`都连起来，串成一个链表，像下图一样：

![](images/16a33e277a98dbec.jpg)

对该记录每次更新后，都会将旧值放到一条 `undo 日志`中，就算是该记录的一个旧版本，随着更新次数的增多，所有的版本都会被 `roll_pointer` 属性连接成一个链表，我们把这个链表称之为`版本链`，版本链的头节点就是当前记录最新的值。另外，每个版本中还包含生成该版本时对应的`事务 id`。

#### ReadView

对于使用 `READ UNCOMMITTED` 隔离级别的事务来说，由于可以读到未提交事务修改过的记录，所以直接读取记录的最新版本就好了。
对于使用 `SERIALIZABLE` 隔离级别的事务来说，使用加锁的方式来访问记录。
对于使用 `READ COMMITTED` 和 `REPEATABLE READ` 隔离级别的事务来说，都必须保证读到已经提交了的事务修改过的记录，也就是说假如另一个事务已经修改了记录但是尚未提交，是不能直接读取最新版本的记录的，核心问题就是：**需要判断一下版本链中的哪个版本是当前事务可见的**。

`ReadView `中主要包含 4 个比较重要的内容 : 

- `m_ids` : 表示在生成 `ReadView` 时当前系统中活跃的读写事务的`事务 id`列表

- `min_trx_id` : 表示在生成 `ReadView` 时当前系统中活跃的读写事务中最小的`事务 id`，也就是 `m_ids` 中的最小值

- `max_trx_id` : 表示生成 `ReadView` 时系统中应该分配给下一个事务的 `id` 值

- `creator_trx_id` : 表示生成该 `ReadView` 的事务的`事务 id`

  > 只有在对表中的记录做改动时 (执行 INSERT、DELETE、UPDATE 这些语句时) 才会为事务分配事务 id，否则在一个只读事务中的事务id值都默认为 0

有了 `ReadView` 后，在访问某条记录时，只需要按照下边的步骤判断记录的某个版本是否可见：

- 如果被访问版本的 `trx_id` 属性值与 `ReadView` 中的 `creator_trx_id` 值相同，意味着当前事务在访问它自己修改过的记录，所以该版本可以被当前事务访问
- 如果被访问版本的 `trx_id` 属性值小于 `ReadView` 中的 `min_trx_id` 值，表明生成该版本的事务在当前事务生成 `ReadView` 前已经提交，所以该版本可以被当前事务访问
- 如果被访问版本的 `trx_id` 属性值大于 `ReadView` 中的 `max_trx_id` 值，表明生成该版本的事务在当前事务生成 `ReadView` 后才开启，所以该版本不可以被当前事务访问
- 如果被访问版本的 `trx_id` 属性值在 `ReadView` 的 `min_trx_id` 和 `max_trx_id` 之间，那就需要判断一下 `trx_id` 属性值是不是在 `m_ids` 列表中，如果在，说明创建 `ReadView` 时生成该版本的事务还是活跃的，该版本不可以被访问；如果不在，说明创建 `ReadView` 时生成该版本的事务已经被提交，该版本可以被访问

在 MySQL 中，`READ COMMITTED` 和 `REPEATABLE READ` 隔离级别的的一个非常大的区别就是它们生成 `ReadView` 的时机不同 : 

- **`READ COMMITTED` —— 每次读取数据前都生成一个 `ReadView`**
- **`REPEATABLE READ` —— 只在第一次读取数据时生成一个 `ReadView`**

