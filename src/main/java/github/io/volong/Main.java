package github.io.volong;

public class Main {

    public static void main(String[] args) {
        
        try {
            System.out.println(1);
            System.exit(3);
        } finally {
            System.out.println(2);
        }
    }
}

