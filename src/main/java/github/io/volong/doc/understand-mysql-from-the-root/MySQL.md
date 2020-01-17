> 神作：[MySQL 是怎样运行的：从根儿上理解 MySQL](https://juejin.im/book/5bffcbc9f265da614b11b731)  

##### 02 初识MySQL

- 查看当前服务器程序支持的存储引擎

  `show engines;`

- 创建表时指定存储引擎

  ```mysql
  CREATE TABLE 表名(
      建表语句;
  ) ENGINE = 存储引擎名称;
  ```

- 修改表的存储引擎

  ```mysql
  ALTER TABLE 表名 ENGINE = 存储引擎名称;
  ```

---

##### 03 启动选项和配置文件

- 类Unix操作系统中的配置文件

  <div align="center">
    <img src="images/mysql配置文件路径.png" height="500px" />
  </div>

  

- 查看系统变量的命令

  `show variables [like 匹配的模式];`

  例：`show variables like 'max_connections';`

---

##### 04 字符集和比较规则

- `ASCII`字符集

  共收录128个字符，包括空格、标点符号、数字、大小写字母和一些不可见字符。

  由于总共才128个字符，所以可以使用1个字节来进行编码

- `ISO 8859-1`字符集

  共收录256个字符，是在`ASCII`字符集的基础上又扩充了128个西欧常用字符(包括德法两国的字母)，也可以使用1个字节来进行编码。

  它的别名叫`latin1`

- `GB2312`字符集

  收录了汉字以及拉丁字母、希腊字母、日文平假名及片假名字母、俄语西里尔字母。

  其中收录汉字6763个，其他文字符号682个。

  同时这种字符集又兼容`ASCII`字符集，所以在编码方式上显得有些奇怪：

  - 如果该字符在`ASCII`字符集中，则采用1字节编码。
  - 否则采用2字节编码。

- `GBK`字符集

  `GBK`字符集只是在收录字符范围上对`GB2312`字符集作了扩充，编码方式上兼容`GB2312`。

- `utf8`字符集

  收录所有的字符，而且还在不断扩充。

  这种字符集兼容`ASCII`字符集，采用变长编码方式，编码一个字符需要使用1～4个字节。

  > utf8只是Unicode字符集的一种编码方案，Unicode字符集可以采用utf8、utf16、utf32这几种编码方案，utf8使用1～4个字节编码一个字符，utf16使用2个或4个字节编码一个字符，utf32使用4个字节编码一个字符。

- MySQL中的`utf8`和`utf8mb4`

  - `utf8mb3`：阉割过的`utf8`字符集，只使用1～3个字节表示字符。

    我们常用的字符使用1～3个字节就可以表示了

  - `utf8mb4`：正宗的`utf8`字符集，使用1～4个字节表示字符

  在`MySQL`中`utf8`是`utf8mb3`的别名。

- 查看字符集的命令

  `show (character set | charset) [like 匹配的模式];`

  > `CHARACTER SET`和`CHARSET`是同义词，用任意一个都可以

- 查看比较规则的命令

  `show collation [like 匹配的模式]`

- 比较规则的规律

  - 比较规则名称以与其关联的字符集的名称开头

  - 后边紧跟着该比较规则主要作用于哪种语言

    比如：`utf8_polish_ci`表示以波兰语的规则比较

    `utf8_general_ci`是一种通用的比较规则

  - 名称后缀意味着该比较规则是否区分语言中的重音、大小写等

    |  后缀  |       英文释义       |       描述       |
    | :----: | :------------------: | :--------------: |
    | `_ai`  | `accent insensitive` |    不区分重音    |
    | `_as`  |  `accent sensitive`  |     区分重音     |
    | `_ci`  |  `case insensitive`  |   不区分大小写   |
    | `_cs`  |   `case sensitive`   |    区分大小写    |
    | `_bin` |       `binary`       | 以二进制方式比较 |

- 4个级别的字符集和比较规则分别是

  - 服务器级别
  - 数据库级别
  - 表级别
  - 列级别

- 服务器级别的字符集和比较规则的系统变量分别是

  - `character_set_server` - 服务器级别的字符集
  - `collation_server` - 服务器级别的比较规则

- 创建数据库时指定数据库的字符集和比较规则

  `create database 数据库名 character set 字符集名称 collate 比较规则名称;`

- 修改数据库字符集和比较规则

  `alter database 数据库名 character set 字符集名称 collate 比较规则名称;`

- 当前数据库使用的字符集和比较规则的系统变量分别是

  - `character_set_database` - 当前数据库的字符集
  - `collation_database` - 当前数据库的比较规则
  >  ***character_set_database*** 和 ***collation_database*** 这两个系统变量是只读的，不能通过修改这两个变量的值而改变当前数据库的字符集和比较规则。

- 创建表时指定字符集和比较规则

  ```mysql
  CREATE TABLE 表名 (列的信息)
      [CHARACTER SET 字符集名称]
      [COLLATE 比较规则名称]
  ```

- 修改表的字符集和比较规则

  ```mysql
  ALTER TABLE 表名
      [CHARACTER SET 字符集名称]
      [COLLATE 比较规则名称]
  ```

- 创建表的时候指定列的字符集和比较规则

  ```mysql
  CREATE TABLE 表名(
      列名 字符串类型 [CHARACTER SET 字符集名称] [COLLATE 比较规则名称],
      其他列...
  );
  ```

- 修改列的时候指定列的字符集和比较规则

  ```mysql
  ALTER TABLE 表名 MODIFY 列名 字符串类型 [CHARACTER SET 字符集名称] [COLLATE 比较规则名称];
  ```

- 仅修改字符集或仅修改比较规则时的变化规则

  - 只修改字符集，则比较规则将变为修改后的字符集默认的比较规则

  - 只修改比较规则，则字符集将变为修改后的比较规则对应的字符集

- 客户端与服务端通信时字符集的转换

  |          系统变量          |                             描述                             |
  | :------------------------: | :----------------------------------------------------------: |
  |   `character_set_client`   |                 服务器解码请求时使用的字符集                 |
  | `character_set_connection` | 服务器处理请求时会把请求字符串从`character_set_client`转为`character_set_connection` |
  |  `character_set_results`   |             服务器向客户端返回数据时使用的字符集             |

  客户端一般情况下使用的字符集与操作系统一致。

  通过`set names 字符集名`语句可以将这三个系统变量的值设置成和客户端一样。

---

##### 05 InnoDB记录存储结构

- 什么是InnoDB页

  InnoDB将数据划分为若干个页，以页作为磁盘和内存之间交互的基本单位，InnoDB中页的大小一般为 ***16*** KB。也就是说从磁盘中读取16KB的内容到内存中，一次最少把内存中的16KB内容刷新到磁盘中。

- InnoDB行格式的类型

  - Compact

  - Redundant

    `MySQL5.0`之前用的一种行格式

  - Dynamic

  - Compressed

- 怎么指定行格式

  - 创建表时指定

    ```mysql
    CREATE TABLE 表名 (
      列的信息
    ) ROW_FORMAT=行格式名称
    ```

  - 修改表时指定

    ```mysql
    ALTER TABLE 表名 ROW_FORMAT=行格式名称
    ```

- COMPACT行格式

  <div align="center">
    <img src="images/compact行格式.png"
  </div>

- Dynamic/Compressed行格式

  - Dynamic行格式Compact行格式相似，只不过在处理`行溢出`数据时不会在记录的真实数据处存储字段真实数据的前`768`个字节，而是把所有的字节都存储到其他页面中，只在记录的真实数据处存储其他页面的地址。

  <div align="center">
    <img src="images/dynamic行格式.png"
  </div>

  - `Compressed`行格式和`Dynamic`不同的一点是，`Compressed`行格式会采用压缩算法对页面进行压缩，以节省空间。

- 行溢出

  如果某一列中的数据非常多的话，在本记录的真实数据处只会存储该列的前`768`个字节的数据和一个指向其他页的地址，然后把剩下的数据存放到其他页中，这个过程也叫做`行溢出`，存储超出`768`字节的那些页面也被称为`溢出页`。

  <div align="center">
    <img src="images/行溢出.png"
  </div>

---

##### 06 InnoDB索引页结构

- 什么是索引（INDEX）页

  专门用来存放表中记录的页

- 索引页的结构

  索引页`16KB`大小的存储空间可以被划分为多个部分，不同部分有不同的功能。

  <div align="center">
    <img src="images/索引页的结构.png" height="400px">
  </div>

- 索引页各个部分的含义

  | 名称                 | 中文名             | 占用空间大小 | 简单描述                 |
  | :------------------- | :----------------- | :----------- | :----------------------- |
  | `File Header`        | 文件头部           | `38`字节     | 页的一些通用信息         |
  | `Page Header`        | 页面头部           | `56`字节     | 数据页专有的一些信息     |
  | `Infimum + Supremum` | 最小记录和最大记录 | `26`字节     | 两个虚拟的行记录         |
  | `User Records`       | 用户记录           | 不确定       | 实际存储的行记录内容     |
  | `Free Space`         | 空闲空间           | 不确定       | 页中尚未使用的空间       |
  | `Page Directory`     | 页面目录           | 不确定       | 页中的某些记录的相对位置 |
  | `File Trailer`       | 文件尾部           | `8`字节      | 校验页是否完整           |

- 记录在页中的存储

  存储的记录会按照我们指定的`行格式`存储到`User Records`部分。

  但是在一开始生成页的时候，其实并没有`User Records`这个部分，每当我们插入一条记录，都会从`Free Space`部分，也就是尚未使用的存储空间中申请一条数据大小的空间划分到`User Records`部分，当`Free Space`部分的空间全部被`User Records`部分替代掉之后，也就意味着这个页使用完了，如果还有新的记录插入，需要去申请新的页。

  <div align="center">
    <img src="images/数据在页中的存储方式.png">
  </div>

- 记录头信息的结构

  <div align="center">
    <img src="images/记录头信息的结构.png" >
  </div>

- 记录头信息各个部分的含义

  | 名称           | 大小（单位：bit） | 描述                                                         |
  | :------------- | :---------------: | :----------------------------------------------------------- |
  | `预留位1`      |        `1`        | 没有使用                                                     |
  | `预留位2`      |        `1`        | 没有使用                                                     |
  | `delete_mask`  |        `1`        | 标记该记录是否被删除                                         |
  | `min_rec_mask` |        `1`        | B+树的每层非叶子节点中的最小记录都会添加该标记               |
  | `n_owned`      |        `4`        | 表示当前记录拥有的记录数                                     |
  | `heap_no`      |       `13`        | 表示当前记录在本页的位置信息                                 |
  | `record_type`  |        `3`        | 表示当前记录的类型：<br />`0`表示普通记录；<br />`1`表示B+树非叶节点记录（目录项）；<br />`2`表示最小记录；<br />`3`表示最大记录； |
  | `next_record`  |       `16`        | 表示下一条记录的相对位置                                     |

- 什么是伪记录或者虚拟记录

  InnoDB在每个页加了两条记录，一条代表最小记录，一条代表最大记录。

  这两条记录单独放在一个称为`Infimum + Supremum`的部分。

  <div align="center">
    <img src="images/伪记录结构.png" >
  </div>

  规定：最小记录的下一条记录就是本页中主键值最小的用户记录，而本页中主键值最大的用户记录的下一条记录就是最大记录。

- User Records中数据的存储结构

  <div align="center">
    <img src="images/User-Records中数据存储结构.png" >
  </div>

  InnoDB始终会维护一条记录的单链表，链表中的各个节点是按照主键值由小到大的顺序通过`next_record`连接起来的。

  当索引页中存在多条被删除掉的记录时，会通过`next_record`将这些被删除掉的记录组成一个垃圾链表，以备之后重用这部分存储空间。

- 页目录（Page Directory）的形成过程

  - 将所有正常的记录（包括最大和最小记录，不包括标记为已删除的记录）划分为几个组。

  - 每个组的最后一条记录（组内最大的那条记录）的头信息中的`n_owned`属性表示该组内共有几条记录。

  - 将每个组的最后一条记录的地址偏移量单独提取出来按顺序存储到`页目录`中。

    页目录中的这些地址偏移量被称为`槽`（Slot）

- 记录与页目录的逻辑关系

  <div align="center">
    <img src="images/记录与页目录的逻辑关系.png" >
  </div>

- 页目录每个分组中记录条数的规定

  - 对于最小记录所在的分组只能有 ***1*** 条记录
  - 最大记录所在的分组拥有的记录条数只能在 ***1~8*** 条之间
  - 剩下的分组中记录的条数范围只能在是 ***4~8*** 条之间

- 一个数据页中查找指定主键值的记录的过程为：

  - 通过二分法确定该记录所在的槽，并找到该槽所在分组中主键值最小的那条记录。
  - 通过记录的`next_record`属性遍历该槽所在的组中的各个记录。

- 什么是页面头部（Page Header）

  是`页`结构的第二部分，占用固定的`56`个字节，专门存储各种状态信息。

  比如本页中已经存储了多少条记录，第一条记录的地址是什么，页目录中存储了多少个槽等等。

- 什么是文件头部（File Header）

  存储各种页都通用的一些信息，占用固定的`38`个字节。

  比如这个页的编号，上一个页、下一个页是谁等等。

  `InnoDB`都是以页为单位存放数据的，如果数据占用的空间非常大，通过文件头部将这些数据页关联起来，所有的数据页其实是一个双链表：

  <div align="center">
    <img src="images/数据页通过双链表关联.png" >
  </div>

- 什么是文件尾部（File Trailer）

  为了检测一个页是否完整，由固定呢的`8`个字节组成。

  如果某页中的数据在内存中被修改了，那么在修改后的某个时间需要把数据同步到磁盘中。但是在同步了一半的时候中出问题了，所以需要对页是否完整进行校验。

  - 前4个字节代表页的校验和

    这个部分是和`File Header`中的校验和相对应的。每当一个页面在内存中修改了，在同步之前就要把它的校验和算出来，因为`File Header`在页面的前边，所以校验和会被首先同步到磁盘，当完全写完时，校验和也会被写到页的尾部，如果完全同步成功，则页的首部和尾部的校验和应该是一致的。

    如果出问题了，那么在`File Header`中的校验和就代表着已经修改过的页，而在`File Trailer`中的校验和代表着原先的页，二者不同则意味着同步中间出了错。

  - 后4个字节代表页面被最后修改时对应的日志序列位置（LSN）

---

##### 07 B+树索引

- 没有索引的列怎么查找

  从第一个页沿着双向链表一直往下找，在每一个页中从`最小记录`开始依次遍历单链表中的每条记录，然后对比每条记录是不是符合搜索条件。

- 建立索引的步骤

  - 下一个数据页中用户记录的主键值必须大于上一个页中用户记录的主键值

    在对页中的记录进行增删改操作的过程中，需要通过一些诸如移动记录的操作来始终保证这条规定一直成立，这个过程称之为`页分裂`

  - 给所有的页建一个目录项

    每个页对应一个目录项，每个目录项包括下边两个部分：

    - 页的用户记录中最小的主键值
    - 页号

  > 目录其实就是`索引`

  <div align="center">
    <img src="images/索引的建立过程.png" >
  </div>

- 目录项纪录与用户纪录的区别
  - `目录项记录`的`record_type`值是1，而普通用户记录的`record_type`值是0
  - `目录项记录`只有主键值和页的编号两个列，而普通的用户记录的列是用户自己定义的，可能包含很多列，另外还有`InnoDB`自己添加的隐藏列
  - 只有存储`目录项记录`的页中，主键值最小的`目录项记录`的`min_rec_mask`值为`1`，其他别的记录的`min_rec_mask`值都是`0`

  <div align="center">
    <img src="images/目录项纪录与用户纪录的区别.png" >
  </div>

- 根据主键值查找一条用户记录的步骤

  - 确定`目录项记录`页
  - 通过`目录项记录`页确定用户记录真实所在的页
  - 在真实存储用户记录的页中定位到具体的记录

- B+树的特点

  <div align="center">
    <img src="images/B+树结构.png" >
  </div>

  <div align="center">
    <img src="images/B+树.png" >
  </div>

  - 实际用户记录其实都存放在B+树的最底层的节点上，这些节点也被称为`叶子节点`或`叶节点`
  - 存放`目录项`的节点称为`非叶子节点`或者`内节点`
  - 最上面的节点称为`根节点`

- 聚族索引的特点

  - 使用记录主键值的大小进行记录和页的排序

    - 页内的记录是按照主键的大小顺序排成一个单向链表
    - 各个存放用户记录的页也是根据页中用户记录的主键大小顺序排成一个双向链表
    - 存放目录项记录的页分为不同的层次，在同一层次中的页也是根据页中目录项记录的主键大小顺序排成一个双向链表

  - `B+`树的叶子节点存储的是完整的用户记录。

    所谓完整的用户记录，就是指这个记录中存储了所有列的值（包括隐藏列）

  具有这两种特性的`B+`树称为`聚簇索引`。

  `InnoDB`存储引擎会自动的为我们创建聚簇索引。

  `聚簇索引`就是数据的存储方式（所有的用户记录都存储在了`叶子节点`），也就是所谓的索引即数据，数据即索引。