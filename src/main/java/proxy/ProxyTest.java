package proxy;

public class ProxyTest {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        
        MyInvocationHandler handler = new MyInvocationHandler(userService);
        
        UserService proxy = (UserService)handler.getProxy();
        
        proxy.add();
    }
}
