- `equals()` 与 `==` 的区别

  `==` 用来判断两个对象的地址是否相等

  `equals` 在没有重写时通过 `==` 来进行判断；否则通过具体的重写规则来判断

- `hashCode()` 与 `equals()` (在没有重写时的情况) 的关系

  如果两个对象通过 `equals()` 比较相等，那么它们的 `hashCode()` 值一定相等

  但是如果它们的 `hashCode()` 值相等，并不能说明它们使用 `equals()` 比较会相等

- simhash 算法

  用于海量文本去重。具有局部敏感性。

  >   参考：
  >
  >   [simhash算法原理及实现](http://www.lanceyan.com/tech/arch/simhash_hamming_distance_similarity.html)
  >
  >   [海量数据相似度计算之simhash和海明距离](http://www.lanceyan.com/tech/arch/simhash_hamming_distance_similarity.html)

- Comparable** 接口与 **Comparator** 接口的区别

  1. `Comparable` 接口一般用于自然排序，`Comparator` 接口常常用于自定义排序。
  2. 原始类都实现了 `Comparable` 接口。除非是自定义了实现了 `Comparator`，否则排序为自然排序。
  3. 没有实现 `Comparable` 接口的类在调用 `Arrays.sort` 时会抛出 `ClassCastException` 异常。

- 冒泡排序

  依次比较两个相邻的元素，如果第一个元素比第二个元素大，则交换它们的位置。

  每次比较完一轮，最大的元素会排到最后。接着对剩下的元素重复这个过程。

  具体实现：[BubbleSort](https://github.com/Volong/javademo/blob/master/src/main/java/github/io/volong/algorithm/sort/BubbleSort.java)

- 插入排序

- 快速排序

- 归并排序

- 二分查找

- HashMap

- LinkedHashMap

- ConcurrentHashMap

- ArrayList

- LinkedList

- HashSet  

- TreeSet  

- Hashtable、HashMap、TreeMap 有什么不同

  Hashtable 支持同步，不支持 null 键和值。

  HashMap 不支持同步，支持 null 键和值。通常情况下，put 或 get 操作，可以达到常数时间的性能。

  TreeMap 是基于红黑树的实现，提供顺序访问的 Map。put 或 get 或 remove 操作都是 O(log(n)) 的时间复杂度。具体顺序可以为自然顺序或者根据 Comparator 来判断。

- HashMap 什么情况下会产生死循环

  JDK1.7，在并发的情况下，rehash 操作会形成环形链表导致死循环。

  JDK1.8 不会出现这个问题。

  > 参考：[疫苗：JAVA HASHMAP的死循环](https://coolshell.cn/articles/9606.html)

- hashcode() 与 equals()  

  1.  如果两个对象通过 equals() 方法返回相等，那么它们的 hashcode 必须相等
  2.  在应用程序中对同一个对象多次调用 hashcode() 方法，返回值相同。
      但是在多次执行应用程序期间不需要保持相同。 
  3.  如果对象通过 equals() 方法返回不相等，那么不要求它们的 hashcode 也不相等。也就是说不同的对象，可能 hashcode 值相同。

  >   参考：[override-hashcode-in-java-example](https://javarevisited.blogspot.com/2011/10/override-hashcode-in-java-example.html)

- ArrayList、LinkedList、Vector的区别

- HashMap、ConcurrentHashMap的区别

- HashMap、LinkedHashMap的区别

- ConcurrentHashMap 是怎么实现线程安全的

- HashMap 的长度为什么是 2 的幂次方

- HashMap 在 JAVA8 做了什么优化 

- 创建线程的方式

- 线程池

- Runnable 接口和 Callable 接口的区别

- wait() 和 sleep() 的区别
  1.  wait 需要在同步的环境中被调用，sleep 没有这个要求
  2.  wait 的线程需要通过 notify() 或 notifyAll() 方法来唤醒，但是 sleep 则不需要
  3.  wait 通常是有条件的，但是 sleep 则没有
  4.  wait 会释放锁，但是 sleep 则不会
  5.  wait 在同步块锁定的对象上调用，但是 sleep 通过线程调用

- yield 与 sleep 的区别

  1.  yield 会临时暂停当前线程，将机会让给其余等待的线程去执行。但是如果其它的线程优先级比较低或者没有其它等待的线程，那么该线程将会继续执行。
  2.  sleep 暂停当前线程指定的时间。

- ReentrantLock

- ReadWriteLock

- CAS

  Compare And Swap 即比较并交换。

  例：`compareAndSwapLong(Object obj, long offset, long expect, long update)`

  比较对象 `obj` 中偏移量为 `offset` 的变量的值是不是和 `expect` 相等，相等则使用 `update` 值更新，然后返回 `true`，否者返回 `false`。

  `CAS` 会有如下问题：

  - `ABA` 问题

    > 1. 线程1 想将 A 替换成 C，但是在未替换成功的时候被阻塞。
    >
    > 2. 线程2 将 A 替换成 B，再替换成 A。
    > 3. 线程1 发现内存中的值还是 A，则将 A 替换成 B。

  - 长时间自旋问题

    > 如果 CAS 长时间更新不成功，会给 CPU 带来非常大的开销

  > 两个疑问 (待解决)：
  >
  > 1. 既然 CAS 有 ABA 问题，为什么 JUC 包还大量使用？是因为发生的概率比较低吗？
  > 2. 为什么长时间自旋会给 CPU 带来非常大的开销？

- 悲观锁

- 乐观锁

- volatile 关键字的作用和原理

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

**贪婪法（Greedy Algorithm）**
> 原文：https://gitbook.cn/gitchat/column/5b6d05446b66e3442a2bfa7b/topic/5b8ba1b315deb02924cdd6c7

又称贪心算法，是寻找最优解问题的常用方法，这种方法模式一般将求解过程分成若干个步骤，但每个步骤都应用贪心原则，选取当前状态下最好的或最优的选择（局部最有利的选择），并以此希望最后堆叠出的结果也是最好或最优的解。

贪婪法的每次决策都以当前情况为基础并根据某个最优原则进行选择，不从整体上考虑其他各种可能的情况。

贪婪法只在很少的情况下可以得到真正的最优解，比如最短路径问题、图的最小生成树问题。

具体实现见: [Greedy](https://github.com/Volong/javademo/blob/master/src/main/java/github/io/volong/algorithm/greedy/Greedy.java)

**分治法 (Divide and Conquer)**
分治，顾名思义，分而治之。将无法着手解决的大问题分解成一系列规模较小的相同问题，然后逐个解决小问题，
分治法产生的子问题与原始问题相同，只是规模减小，反复使用分治方法，可以使得子问题的规模不断减小，直到能够被直接求解为止。

应用：最轻、最重问题 (在一堆形状相同的物品中找出最重或最轻的那一个)；矩阵乘法；大整数乘法；快速排序；归并排序；快速傅里叶变换算法。


