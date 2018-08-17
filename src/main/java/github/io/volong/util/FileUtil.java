package github.io.volong.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
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
    
    public static void readTextFileIntoFile(String fileName, String regex, String toFile) throws IOException {
        File inputFile = new File(fileName);
        
        Pattern pattern = Pattern.compile(regex);
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFile, true))) {
            String str = null;
            
            while ((str = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(str);
                if (matcher.matches()) {
                    final String group = matcher.group(1);
                    writer.write(group);
                    writer.newLine();
                }
            }
            writer.flush();
        }
    }
    
    public static void readZipFile(String zipFile, String toDir) throws IOException {
        try (FileSystem fs = FileSystems.newFileSystem(Paths.get(zipFile), FileUtil.class.getClassLoader())) {
            
            for (Path path : fs.getRootDirectories()) {
                
                Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
                    
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        // 拷贝到本地文件系统
                        Path temp = Paths.get(toDir, System.currentTimeMillis() + ".txt");
                        Files.copy(file, temp, StandardCopyOption.REPLACE_EXISTING);
                        
                        readTextFileIntoFile(temp.toFile().getPath(), ".*from = 0 的客户端为:(\\w+)\"}", toDir + "/out.txt");
                        
                        return FileVisitResult.CONTINUE;
                    }
                    
                });
            
            }
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        
        String zipDir = "C:/Users/Volong/Downloads/api_log";
        String toDir = "C:/Users/Volong/Downloads/api_log_txt";
        File file = new File(zipDir);
        File[] listRoots = file.listFiles();
        for (File f : listRoots) {
            final String path = f.getPath();
            System.out.println(path);
            readZipFile(path, toDir);
        }
        
    }
    
    public static void readZipFile1(String zipFile) throws IOException {
        try (FileSystem fs = FileSystems.newFileSystem(Paths.get(zipFile), FileUtil.class.getClassLoader())) {
            
            for(Path path : fs.getRootDirectories()){                
                Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
                    
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        // 拷贝到本地文件系统
                        Path temp = Paths.get("target/corpus-"+System.currentTimeMillis()+".txt");
                        Files.copy(file, temp, StandardCopyOption.REPLACE_EXISTING);
                        return FileVisitResult.CONTINUE;
                    }
                    
                });
            }
        }
    }
}
