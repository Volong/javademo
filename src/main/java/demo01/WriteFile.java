package demo01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WriteFile {

    public static void main(String[] args) {
        
        try {
            File inputFile = new File("C:\\Users\\Volong\\Downloads\\log\\ai.2018-01-09_0.log");
            
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            
            String str = null;
            
            // szmama_dbname.product=sz_final
            String regex = ".*product=(.*)_final";

            Pattern compile = Pattern.compile(regex);
            int i = 0;
            while ((str = reader.readLine()) != null) {
                if (str.contains("百度图片处理时间为")) {
                    System.out.println(str);
                    i++;
                }
            }
            System.out.println(i);
                  
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
