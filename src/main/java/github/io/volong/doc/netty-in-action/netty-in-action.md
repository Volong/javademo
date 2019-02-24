### 1.3 Netty 的核心组件

- Channel
- 回调
- Future
- 事件和 ChannelHandler

#### 1.3.1 Channel

Channel 代表一个到实体（如一个硬件设备、一个文件、一个网络套接字或者一个能够执行一个或者多个不同的 I/O 操作的程序组件）的开放连接，如读操作和写操作。

可以把 Channel 看作是传入（入站）或者传出（出站）数据的载体。因此，它是可以被打开或者被关闭。

#### 1.3.2 回调

一个回调其实就是一个方法。

> 例：被回调触发的 ChannelHandler
>
> ```java
> public class ConnectHandler extends ChannelInboundHandlerAdapter {  
>     
>     // 当一个新的连接已经被建立时，channelActive(ChannelHandlerContext)将会被调用         
>     @Override     
>     public void channelActive(ChannelHandlerContext ctx) throws Exception {				
>         System.out.println("Client " + ctx.channel().remoteAddress() + " connected");     	  }
> }
> ```

#### 1.3.3 Future

Future 可以看作是一个异步操作的结果的占位符，它将在未来的某个时刻完成，并提供对结果的访问。

Netty 提供了 `ChannelFuture` 用于在执行异步操作时使用。每个 Netty 的出站 I/O 操作都将返回一个 `ChannelFuture`，它们都不会被阻塞。
`ChannelFuture` 提供了几种方法使得我们能够注册一个或者多个 `ChannelFutureListener` 实例。监听器的回调方法 `operationComplete()` 将会在对应的操作完成时被调用。然后监听器可以判断该操作是成功还是出错，如果出错了，我们可以检索产生的 `Throwable`。

> 例：异步的建立连接
>
> ```java
> Channel channel = ...;
> // 异步的连接到远程节点
> ChannelFuture future = channel.connect(new InetSocketAddress("127.0.0.1", 25));
> ```
>
> 例：回调实战
>
> ```java
> Channel channel = ...;
> ChannelFuture future = channel.connect(new InetSocketAddress("127.0.0.1", 25));
> // 注册一个 ChannelFutureListener 以便在操作完成时获得通知
> future.addListener(new ChannelFutureListener() {
>     
>     @Override
>     public void operationComplete(ChannelFuture future) {
>         // 如果操作成功
>         if (future.isSuccess()) {
>             Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
>             // 将数据异步的发送到远程节点
>             ChannelFuture wf = future.channel().writeAndFlush(buffer);
>         } else {
>             // 如果发生错误
>             Throwable cause = future.cause();
>             cause.printStackTrace();
>         }
>     }
> });
> ```