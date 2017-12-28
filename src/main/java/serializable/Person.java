package serializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * <p> 序列化只会保存对象的状态，即成员变量，即静态变量会排除在外
 * <p> 当同时实现了 {@link Serializable} 与 {@link Externalizable} 两个接口时，只有 {@link Externalizable} 会生效
 * 
 * <p> 参见: @see <a href="http://www.blogjava.net/jiangshachina/archive/2012/02/13/369898.html">理解Java对象序列化</a>
 * @date 2017年12月28日 上午11:44:54
 *
 */
public class Person implements Serializable, Externalizable {

    private static final long serialVersionUID = 7486001556610386237L;

    private String name;
    
    /**
     * 声明为 transient 后，默认序列化机制就会忽略该字段
     * 但是可以通过自定义的 {@link #writeObject(ObjectOutputStream)} 来显式的写入
     * 如果相同类型的要写入多个，则在读取的时候需要有相同数量的读取操作并且按照写入的顺序读取 {@link #readObject(ObjectInputStream)}
     */
    private transient Integer age;
    
    private transient Integer height; 
    
    private Gender gender;

    /**
     * 实现 {@link Externalizable} 接口，读取对象的时候需要调用 public 的无参构造函数
     */
    public Person() {
        System.out.println("无参构造器");
    }

    public Person(String name, Integer age, Integer height, Gender gender) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.gender = gender;
        System.out.println("四参构造函数");
    }


    public Person(String name, Integer age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        System.out.println("三参构造函数");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", height=" + height + ", gender=" + gender + "]";
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("默认写入对象");
        out.defaultWriteObject();
        out.writeInt(height);
        out.writeInt(age);
    }
    
    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        System.out.println("默认读取对象");
        in.defaultReadObject();
        height = in.readInt();
        age = in.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(height);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        name = (String) in.readObject();
        /*
         *  要按照顺序读取，不然值会混乱
         *  因为 age 获取的是 height 的值
         */
        age = in.readInt();
    }

    
}
