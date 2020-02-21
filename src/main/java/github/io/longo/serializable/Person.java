package github.io.longo.serializable;

import java.io.Serializable;

/**
 *
 * 父类对象没有实现 {@link Serializable} 接口，则父类里面的变量不会被序列化
 *
 * @date 2017年12月28日 下午5:13:03
 *
 */
public class Person {

    protected String name;

    /**
     * 没有父类无参构造器的时候会报错
     */
    public Person() {
        System.out.println("父类构造器");
    }

    public Person(String name) {
        System.out.println("父类有参构造器");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
