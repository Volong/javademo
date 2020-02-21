package github.io.longo.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeTest {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        File file = new File("person.out");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        
//        Man man = new Man("yihailong", 18, 180, Gender.MALE);
        Man man = new Man("yihailong");
        out.writeObject(man);
        out.flush();
        out.close();
        
        System.out.println("name:" + man.getName());
        // 改变静态变量的值
        Man.weight = 200;
        
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Object readObject = in.readObject();
        in.close();
        System.out.println(readObject);
        /*
         *  weight 从初始值 180 变成了 200，说明静态变量并未保存在序列化文件中
         */
        System.out.println("静态变量 weight:" + ((Man)readObject).weight);
    }
}