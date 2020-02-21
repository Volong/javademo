package github.io.longo.jvm;

public class RuntimeConstantPoolOOM {
    public static void main (String[] args) { 
        
//        String str1= new StringBuilder("计算机").append("软件").toString();
        String str1 = "计算机软件";
        System.out.println(str1.intern() == str1);
        
        String str = "java";
        String str2= new StringBuilder("ja"). append("va").toString();
        System.out.println(str2.intern() == str2); 
    }
}