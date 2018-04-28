package github.io.volong.lambda;

public class RunnableTest {

    public static void main(String[] args) {
        
        // 匿名内部类
        Runnable runnable1 = new Runnable() {
            
            @Override
            public void run() {
                System.out.println("Hello world one");
            }
        };
        
        Runnable runnable2 = () -> System.out.println("Hello world two");
        
        runnable1.run();
        runnable2.run();
    }
}
