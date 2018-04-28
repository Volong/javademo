package github.io.volong.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static List<String> readTextFile(String fileName) throws IOException {
        File inputFile = new File(fileName);
        
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String str = null;
        List<String> strs = new ArrayList<>();
        
        while ((str = reader.readLine()) != null) {
            strs.add(str);
        }
        
        reader.close();
        
        return strs;
    }
}
