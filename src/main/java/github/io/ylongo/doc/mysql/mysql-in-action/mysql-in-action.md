> [从零开始带你成为MySQL实战优化高手](https://apppukyptrl1086.pc.xiaoe-tech.com/detail/p_5e0c2a35dbbc9_MNDGDYba/6)



##### 03 通过更新语句初步了解InnoDB存储引擎的架构设计

- InnoDB存储引擎中缓存池的作用

  InnoDB在更新数据时，会先看这条数据是否在缓存池（Buffer Pool）中，如果不在的话，会先从磁盘中加载到缓冲池里，还会对这条记录加独占锁

- undo日志如何进行回滚

  更新数据时，在事务提交之前会先将原来的旧值写入到`undo`日志中，这样在事务提交前可以对数据进行回滚

更新数据的流程：

- 先看这条数据是否在缓冲池（Buffer Pool）中，不在的话，会从磁盘加载到缓冲池中来，而且还会对这条记录加独占锁

- 将原来的旧值写入到`undo`日志中，这样在事务提交前可以对数据进行回滚

- 先更新缓冲池中的数据，那么这条数据就变成了脏数据

- 把对内存中所做的修改写入到`Redo Log Buffer`中

  > 这是`redo`日志的缓冲区

> 如果这时事物还没有提交，MySQL宕机了，内存里面的数据都会丢失。
>
> 但是由于没有提交事务，所以并不会有任何问题

- 提交事务的时候将`redo`日志写入磁盘中

  会根据以下策略将`redo`日志从`Redo Log Buffer`中刷到磁盘文件中。

  > 具体的策略是通过`innodb_flush_log_at_trx_commit`来配置的

  - **0**

    提交事务时不会将`redo log buffer`里的数据刷入磁盘文件，如果mysql宕机了，此时内存里的数据全部会丢失

  - **1**

    提交事务时，必须把`redo log`从内存中刷到磁盘文件，只要事物提交成功了，那么`redo log`就一定在磁盘中了

  - **2**

    提交事务时，把`redo`日志写入磁盘文件对应的`os cache`中，然后过段时间再写入磁盘文件

---

##### 04 `binlog`是什么

- 什么是`binlog`

  归档日志，记录的是偏向于逻辑性的日志。

  > 例如：对某个表的某一行记录做了修改，修改后的值是什么

  `binlog`不是InnoDB引擎特有的日志文件，是属于mysql server自己的日志文件

- `binlog`日志的刷盘策略

  通过`sync_binlog`参数来控制`binlog`的刷盘策略。

  - 默认为**0**，即将数据写入到os cache内存缓存中
  - 为**1**，提交事务时，直接将binlog写入到磁盘文件中

- 基于`binlog`和`redo log`完成事务的提交

  - 在提交事务时，会把`redo log`日志写入磁盘文件中去，还会把这次更新对应的`binlog`日志写入到磁盘文件中去

  - 当把`binlog`写入磁盘文件后，会把本次更新对应的`binlog`文件名称和这次更新的`binlog`日志在文件里的位置，都写入到`redo log`日志文件里去，同时在`redo log`日志文件里写入一个`commit`标记

  - 在完成这个事情之后，才算最终完成了事务的提交

- 在`redo`日志中写入`commit`标记的意义是什么

  用来保持`redo log`日志与`binlog`日志一致

---

##### 20 LRU链表尾部的缓存页，是如何淘汰他们刷入磁盘的

mysql有一个后台定时任务每隔一段时间将LRU链表冷数据区尾部的缓存页，刷入到磁盘里去，清空这些缓存页，然后加回到`free`链表中去。

同时，后台线程会在mysql比较空闲的时候，把`flush`链表中的缓存页都刷入磁盘中。

---

##### 21 通过多个Buffer Pool来优化数据库的并发性能

多线程并发访问同一个Buffer Pool时，会进行加锁处理，依次串行执行。

默认情况下，如果给Buffer Pool分配的内存小于**1G**，那么最多只会有一个Buffer Pool。

- `innodb_buffer_pool_size`

  设置Buffer Pool的大小

- `innodb_buffer_pool_instances`

  设置Buffer Pool的个数

---

##### 22 动态调整Buffer Pool

Buffer Pool是由很多`chunk`组成的。通过`innodb_buffer_pool_chunk_size`来控制，默认值为**128M**。

每个Buffer Pool里面的每个`chunk`就是一系列的控制块和缓存页，这些`chunk`共享同一个`free`、`flush`、`lru`链表。

动态调整Buffer Pool时，只要申请一系列的`chunk`，每个`chunk`是连续的**128M**内存，然后把这些`chunk`分配给Buffer Pool就可以了。

##### 23 基于机器配置合理设置Buffer Pool

一般来说，设置Buffer Pool为机器内存的**50% ~ 60%**。



















