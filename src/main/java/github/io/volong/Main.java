package github.io.volong;

import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		BigInteger bi = new BigInteger("11111111111111111111111110000000", 2);
		System.out.println(Long.parseLong(bi.toString()));
	}
}
