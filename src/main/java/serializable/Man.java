package serializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * <p> 序列化只会保存对象的状态，即成员变量
 * <p> 当同时实现了 {@link Serializable} 与 {@link Externalizable} 两个接口时，
 *     只有 {@link Externalizable} 会生效
 * <p> 实现了 {@link Externalizable} 接口需要自己重写 
 *     {@link #readExternal(ObjectInput)} 与 {@link #writeExternal(ObjectOutput)}
 *     具体的实现
 * <p> 参见: @see <a href="http://www.blogjava.net/jiangshachina/archive/2012/02/13/369898.html">理解Java对象序列化</a>
 * <p>      @see <a href="https://www.ibm.com/developerworks/cn/java/j-lo-serial/index.html">Java 序列化的高级认识</a>
 * @date 2017年12月28日 上午11:44:54
 *
 */
public class Man extends Person implements Serializable, Externalizable {

    /**
     * <p> 序列化 ID：是为了保证反序列化的时候保持一致性
     * 
     * <p> 如果没有特殊的要求，使用默认的 1L 就可以了
     * <p> 不要 xjb 乱改，如果将对象序列化后再存到 redis，但是某次把这个序列化 ID 改了，那你就等着哭吧
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 声明为 transient 后，默认序列化机制就会忽略该字段
     * 但是可以通过自定义的 {@link #writeObject(ObjectOutputStream)} 来显式的写入
     * 如果相同类型的要写入多个，则在读取的时候需要有相同数量的读取操作并且按照写入的顺序
     * 读取 {@link #readObject(ObjectInputStream)}
     */
    private transient Integer age;
    
    private transient Integer height; 
    
    private Gender gender;

    /**
     * 静态变量不会被保存在序列化后的文件中
     */
    public static Integer weight = 180;
    
    /**
     * 实现 {@link Externalizable} 接口，读取对象的时候需要调用 public 的无参构造函数
     */
    public Man() {
        System.out.println("无参构造器");
    }
    
    public Man(String name) {
        super(name);
        System.out.println("单参构造函数");
    }
    
    public Man(String name, Integer age, Integer height, Gender gender) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.gender = gender;
        System.out.println("四参构造函数");
    }


    public Man(String name, Integer age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        System.out.println("三参构造函数");
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
        return "Man [age=" + age + ", height=" + height + ", gender=" + gender + ", name=" + name + "]";
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
//        out.writeObject(name);
//        out.writeInt(height);
//        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

//        name = (String) in.readObject();
         /*  
          * 要按照顺序读取，不然值会混乱
          *  因为 age 获取的是 height 的值
          */
         
//        age = in.readInt();
    }

    
}
