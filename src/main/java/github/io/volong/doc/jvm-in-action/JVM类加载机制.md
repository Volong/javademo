#### 051 使用jmap

- `jmap -histo PID`

  打印各种对象占用内存空间的大小，按照降序排序

- `jmap -dump:live,format=b,file=fileName.hprof PID`

  在当前目录下生成一个`fileName.hprof`的内存快照

#### 058 优化FullGC的性能

`-XX:+CMSParallelInitialMarkEnabled`在`CMS`垃圾回收器的`初始标记`阶段开启对线程并发执行。

`-XX:+CMSScavengeBeforeRemark`在`CMS`的`重新标记`阶段之前，尽量先执行一次`Young GC`， 回收掉年轻代中没人引用的对象，那么在`CMS`的`重新标记`阶段就可以少扫描一些对象，减少耗时。

> 之所以`CMS`会扫描到年轻代是因为可能有些年轻代的对象引用了老年代的对象，提前做`Young GC`可以回收掉年轻代中的对象，减少耗时



---



#### 059 频繁Full GC的不合理参数

`-XX:SoftRefLRUPolicyMSPerMB`表示每1 MB的空闲内存空间允许`SoftReference`对象存活多久，默认为`1000`毫秒

> JVM在反射过程中动态生成的类`Class`对象都是通过`SoftReference`修饰的软引用对象

GC时判断`SoftReference`对象是否需要回收通过如下公式进行判断：

`clock - timestamp <= freespace * SoftRefLRUPolicyMSPerMB` 

- `clock - timestamp`表示一个软引用对象多久没有被访问过了
- `freespace`表示JVM中空闲的内存空间



---



#### 064 内存泄漏分析

频繁导致`Full GC`的可能性如下：

- 内存分配不合理，导致对象频繁进入老年代，进而引发`Full GC`
- 存在内存泄漏。也就是内存里面驻留了大量的对象塞满了老年代，导致稍微有一些对象进入老年代就会触发`Full GC`
- 大量使用发射导致永久代里面的类太多，触发了`Full GC`
- 代码中调用`System.gc()`

通过`jmap`导出内存快照，再使用`MAT`进行分析内存是否泄漏







