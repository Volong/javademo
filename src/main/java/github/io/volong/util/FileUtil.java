package github.io.volong.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

    public static List<String> readTextFile(String fileName) throws IOException {
        File inputFile = new File(fileName);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String str = null;
            List<String> strs = new ArrayList<>();
            
            while ((str = reader.readLine()) != null) {
                strs.add(str);
            }
            
            return strs;
        }
        
    }
    public static List<String> readTextFile(String fileName, String regex) throws IOException {
        File inputFile = new File(fileName);
        
        Pattern pattern = Pattern.compile(regex);
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String str = null;
            List<String> strs = new ArrayList<>();
            
            while ((str = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(str);
                if (matcher.matches()) {
                    strs.add(str);
                }
            }
            
            return strs;
        }
        
    }
}
