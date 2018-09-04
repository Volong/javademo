

- HashMap

- LinkedHashMap

- ConcurrentHashMap

- ArrayList

- LinkedList

- HashSet  

- TreeSet  

- HashMap、Hashtable的区别

- ArrayList、LinkedList、Vector的区别

- HashMap、ConcurrentHashMap的区别

- HashMap、LinkedHashMap的区别

- ConcurrentHashMap 是怎么实现线程安全的

- HashMap 的长度为什么是 2 的幂次方

- HashMap 在 JAVA8 做了什么优化 

- 创建线程的方式

- 线程池

- Runnable 接口和 Callable 接口的区别

- wait 方法和 sleep 方法的区别

- ReentrantLock

- ReadWriteLock

- CAS

- 悲观锁

- 乐观锁

- volatile关键字的作用和原理

- ThreadLocal

- 创建线程池的4种方式

- ThreadPoolExecutor 的内部工作原理

- 分布式环境下，怎么保证线程安全

- synchronized

- volatile

- lock

- CountDownLatch 

- CyclicBarrier  

- Semaphore  

- 垃圾回收算法

- 类加载的过程

- 双亲委派模型

- 什么情况会打破双亲委派机制 

- 有哪些类加载器，原理是怎样  

- 设计模式

- MySQL 事务

- MySQL 储存引擎的区别 

- 父子线程穿透如何解决 

- JVM 如何加载类  

- 什么是 IOC

- Spring 事务

- B 树

- 二叉树

- B+ 树

- trie 树

- 双数组 trie 树

- 分布式锁的实现

- Spring Bean 的生命周期

- Spring IOC 如何实现

- Spring AOP 实现原理

- 动态代理

- Spring 跟 SpringBoot 的区别

- Redis 的数据类型

- HTTP 3 次握手 

- HTTP

- TCP

- IP 

- NIO

- BIO

- AIO

- GC 调优 

- GC 原理 

- JAVA 内存模型 

- 数据结构

### 算法 

贪婪法（Greedy Algorithm）
> 原文：https://gitbook.cn/gitchat/column/5b6d05446b66e3442a2bfa7b/topic/5b8ba1b315deb02924cdd6c7

又称贪心算法，是寻找最优解问题的常用方法，这种方法模式一般将求解过程分成若干个步骤，但每个步骤都应用贪心原则，选取当前状态下最好的或最优的选择（局部最有利的选择），并以此希望最后堆叠出的结果也是最好或最优的解。

贪婪法的每次决策都以当前情况为基础并根据某个最优原则进行选择，不从整体上考虑其他各种可能的情况。

贪婪法只在很少的情况下可以得到真正的最优解，比如最短路径问题、图的最小生成树问题。

具体实现见: [Greedy](https://github.com/Volong/javademo/blob/master/src/main/java/github/io/volong/algorithm/greedy/Greedy.java)
