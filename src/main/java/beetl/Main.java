package beetl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.JPA2NameConversion;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;

public class Main {

    public static void main(String[] args) throws IOException {

        // TODO Auto-generated constructor stub

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://10.0.0.120:3306/solr?tinyInt1isBit=false&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
        String userName = "root";
        String password = "sa";

        ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
        DBStyle mysql = new MySqlStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        JPA2NameConversion nc = new JPA2NameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[] { new DebugInterceptor() });

        // 使用内置的生成的sql 新增用户，如果需要获取主键，可以传入KeyHolder
//        User user = new User();
//        user.setAge(19);
//        user.setName("xiandafu");
//        sqlManager.insert(user);

        // 使用内置sql查询用户
//        int id = 1;
//        user = sqlManager.unique(User.class, id);

        // 模板更新,仅仅根据id更新值不为null的列
//        User newUser = new User();
//        newUser.setId(1);
//        newUser.setAge(20);
//        sqlManager.updateTemplateById(newUser);

        // 模板查询
//        User query = new User();
//        query.setName("xiandafu");
//        List<Mamaquan> list = sqlManager.template(mmq);
//        List<Mamaquan> select = sqlManager.select("mamaquan.select", Mamaquan.class, mmq, 1, 10000);
        
        int atid = 13000000;
        // 13973942
        while (atid < 14000000) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("x.txt", true));
            
            List<Mamaquan> select = sqlManager.query(Mamaquan.class).andBetween("atid", atid, atid += 10000).select();
            for (Mamaquan m : select) {
                writer.write(m.getDesc());
                writer.append("\n");
            }
            
        }
//        writer.flush();
        // 使用user.md 文件里的select语句，参考下一节。
//        User query2 = new User();
//        query.setName("xiandafu");
//        List<User> list2 = sqlManager.select("user.select", User.class, query2);
        
        
        // 这一部分需要参考mapper一章
        // UserDao dao = sqlManager.getMapper(UserDao.class);
        // List<User> list3 = dao.select(query2);
    }
}
