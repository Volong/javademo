
> 原文：https://javarevisited.blogspot.com/2012/01/difference-thread-vs-runnable-interface.html

1. Java 不支持多继承，所以只能继承一个类。如果你继承了 Thread 类，那么你就不能再继承其它的类。

2. 继承一个类意味着你想要添加或者更改某些功能。但是如果我们不想对 Thread 类做其它的更改，应该使用 Runnable 接口。

3. **Runnable** 表示一个可以被 `Thread` 或者 `Executors` 执行的任务。因此将一个任务逻辑拆分为 Runnable 而不是 Thread 是一个种很好的设计。

4. 将任务拆分为 **Runnable** 意味着我们可以重复使用这个任务，并且自由的以不同的方式执行。因为 Thread 在重新完成前不能被重新启动。

5. Executors 接收一个 Runnable 作为任务，并且有工作线程去执行这个任务

6. 继承所有的 Thread 方法用来表示一个任务，将会增加额外的开销。而这个任务可以轻松的使用 Runnable 来表示
