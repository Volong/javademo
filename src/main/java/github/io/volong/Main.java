package github.io.volong;

public class Main {

    public static void main(String[] args) {
        double x = 1.0 / 0.0;
        System.out.println(new Double(x).equals("Infinity"));
    }
}
