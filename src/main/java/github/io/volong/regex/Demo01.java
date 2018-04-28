package github.io.volong.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo01 {

	public static void main(String[] args) {
		
		String str = "abbbc";
		
		String regex = "ab{1,3}?bbc";
		
		Pattern compile = Pattern.compile(regex);
		
		Matcher matcher = compile.matcher(str);
		
		boolean matches = matcher.matches();
		
		System.out.println(matches);
	}
}
