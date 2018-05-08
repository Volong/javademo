package github.io.volong.exception;

/**
 * 测试 {@link ClassNotFoundException} 
 *
 */
public class ClassNotFoundTest {

    public static void main(String[] args) {
        
        String className = "com.mysql.jdbc.driver";
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
