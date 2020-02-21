package github.io.longo.jcpb;
public class TestThreadLocal {

    //(1) 创建线程变量
//    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	
    public static ThreadLocal<String> inheritThreadLocal = new InheritableThreadLocal<>();
    
    public static void main(String[] args) {

        //(2)  设置线程变量
    	inheritThreadLocal.set("hello world");
        //(3) 启动子线程
        Thread thread = new Thread(new  Runnable() {
            public void run() {
                //(4)子线程输出线程变量的值
                System.out.println("thread:" + inheritThreadLocal.get());
                inheritThreadLocal.set("hello world1");
                System.out.println("thread:" + inheritThreadLocal.get());
                
            }
        });
        thread.start();

        //(5)主线程输出线程变量值
        System.out.println("main:" + inheritThreadLocal.get());

    }
}