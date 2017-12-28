package demo01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WriteFile {

    public static void main(String[] args) {
        
        try {
            File inputFile = new File("D:\\E\\project\\import\\src\\main\\resources\\run\\databases.properties");
            
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            
            String str = null;
            
            // szmama_dbname.product=sz_final
            String regex = ".*product=(.*)_final";

            Pattern compile = Pattern.compile(regex);
            while ((str = reader.readLine()) != null) {
                
                Matcher matcher = compile.matcher(str);
                if (matcher.matches()) {
                    System.out.println(matcher.group(1) + "_final");
                }
            }
            
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
