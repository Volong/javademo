package github.io.longo.proxy;

public class ProxyTest {

    public static void main(String[] args) {
        
        // 打开这个开关，可以把生成的代理类保存到磁盘
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");  

        // 创建目标对象（被代理对象）
        UserService userService = new UserServiceImpl();
        
        // 创建一个InvocationHandler实例，并传递被代理对象
        MyInvocationHandler handler = new MyInvocationHandler(userService);
        
        // 生成代理类
        UserService proxy = (UserService)handler.getProxy();
        
        proxy.add();
    }
}
