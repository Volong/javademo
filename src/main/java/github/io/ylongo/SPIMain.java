package github.io.ylongo;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

public class SPIMain {

    public static void main(String[] args) {

        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        System.out.println("Java SPI");
        Iterator<Driver> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Driver next = iterator.next();
            System.out.println("[class]:" + next.getClass() + " [loader]:" + next.getClass().getClassLoader());
        }

        System.out.println(serviceLoader.getClass().getClassLoader());
    }
}
