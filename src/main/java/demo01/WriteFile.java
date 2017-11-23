package demo01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WriteFile {

    public static void main(String[] args) {
        
        try {
            File inputFile = new File("C:\\Users\\Volong\\Desktop\\压测记录\\solr-six-mixed-5000-bak.txt");
            
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            
            File outputFile = new File("C:\\Users\\Volong\\Desktop\\压测记录\\solr-six-mixed-5000.txt");
            BufferedWriter write = new BufferedWriter(new FileWriter(outputFile));
            
            String str = null;
            String regex = "192.168.20.5\\d:8983";
            
            for (int i = 1; i <= 5000; i++) {
                str = reader.readLine();
                if (str == null) {
                    break;
                }
                String replaceFirst = "";
                if (i % 6 == 1) {
                  replaceFirst = str;  
                } else if (i % 6 == 2) {
                    replaceFirst = str.replaceFirst(regex, "192.168.20.51:8983");
                } else if (i % 6 == 3){
                    replaceFirst = str.replaceFirst(regex, "192.168.20.52:8983");
                } else if (i % 6 == 4) {
                    replaceFirst = str.replaceFirst(regex, "192.168.20.53:8983");
                } else if (i % 6 == 5) {
                    replaceFirst = str.replaceFirst(regex, "192.168.20.54:8983");
                } else if (i % 6 == 0) {
                    replaceFirst = str.replaceFirst(regex, "192.168.20.55:8983");
                }
                
                write.write(replaceFirst);
                System.out.println(replaceFirst);
                write.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
