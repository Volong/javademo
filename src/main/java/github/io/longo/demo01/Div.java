package github.io.longo.demo01;

import java.util.Scanner;

public class Div {

    public static void main(String[] args) {
        
        double d = 226.478;
        
        Scanner scan = new Scanner(System.in);
        
        while (scan.hasNextLine()) {
            String nextLine = scan.nextLine();
            double parseDouble = Double.parseDouble(nextLine);
            System.out.println("差:" + (d - parseDouble));
            System.out.println("相差多少倍:" + ((d - parseDouble) /  d));
        }
    }
}
