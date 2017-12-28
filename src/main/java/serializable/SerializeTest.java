package serializable;

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
        
        Person person = new Person("yihailong", 18, 180, Gender.MALE);
        out.writeObject(person);
        out.flush();
        out.close();
        
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Object readObject = in.readObject();
        in.close();
        System.out.println(readObject);
    }
}