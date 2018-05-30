package github.io.volong.the.logic.of.java.programming.chapter08;

public class Main {

	public static <T> void test(T str) {
		if (str instanceof String) {
			System.out.println(str);
		} else if (str instanceof Integer) {
			System.out.println(((Integer) str).intValue());
		}
		System.out.println(123);
	}
	
	public static void main(String[] args) {
		
		new Main();
	}
}
