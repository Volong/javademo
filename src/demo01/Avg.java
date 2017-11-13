package demo01;

import java.util.Scanner;

public class Avg {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String line = scan.nextLine();
            
            String[] split = line.split("/");
            double total = 0;
            for (String s : split) {
                double parseDouble = Double.parseDouble(s.trim());
                total += parseDouble;
            }
            
            System.out.println(total / split.length);
        }
        
    }
}
