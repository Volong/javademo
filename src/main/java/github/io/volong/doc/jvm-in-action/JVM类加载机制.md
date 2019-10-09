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



#### 060 





