package github.io.longo.demo01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class WriteFile {

    public static void main(String[] args) {
        
        try {
            File inputFile = new File("C:\\Users\\Volong\\Desktop\\127\\ai.log");
            
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            
            String str = null;
            
            // szmama_dbname.product=sz_final
            String regex = ".*(dbname: |database\\.)(.*MAMA).*";
            
            String[] strs = {"shmama",
                    "wxmama",
                    "kmmama",
                    "zzmama",
                    "suzmama",
                    "dgmama",
                    "fsmama",
                    "zsmama",
                    "shantoumama",
                    "whmama",
                    "ccmama",
                    "hrbmama",
                    "njmama",
                    "nnmama",
                    "gymama",
                    "fzmama",
                    "xmmama",
                    "hzmama",
                    "sjzmama",
                    "ncmama"};
            
            List<String> asList = Arrays.asList(strs);
            System.out.println(asList.size());
            Pattern compile = Pattern.compile(regex);
            boolean flag = true;
            while ((str = reader.readLine()) != null) {
                
                if (str.contains("remoteAddr")) {
                    System.out.println(str);
                }
            }
                  
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
