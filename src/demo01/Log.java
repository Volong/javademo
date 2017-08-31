package demo01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class Log {

	/**
	 * 提取 IP
	 * @time 2017年8月18日 上午8:43:53
	 * @author volong
	 */
	@Test
	public void test01 () {

		BufferedReader reader = null;
		try {
			File f = new File("C:\\Users\\Volong\\Desktop\\127\\2017-08-18_11-08-35-soweb.log");
			reader = new BufferedReader(new FileReader(f));
			String str = "";
			
			String regex = "(.*)remote_addr\":\"(.{0,16})\",(.*)";
			
			Pattern compile = Pattern.compile(regex);
			
			while ((str = reader.readLine()) != null) {
				
				if (str.contains("remote_addr")) {
					String[] strs = str.split("\\|");
					String msg = strs[6];
					String replace = msg.replace("\\", "");
//					System.out.println();
					Matcher matcher = compile.matcher(replace);
					if (matcher.find()) {
						String ip = matcher.group(2);
						if (ip.length() > 15) {
							System.out.println(ip);
						}
//						System.err.println(replace);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
	}
	/**
	 * 提取导入数据量
	 * @time 2017年8月18日 上午8:44:25
	 * @author volong
	 */
	@Test
	public void test02() {
		BufferedReader reader = null;
		try {
			File f = new File("E:\\import.2017-06-22_0.log");
			reader = new BufferedReader(new FileReader(f));
			String str = "";
			
			String regex = "提交\\d+条数据到";
			
			String numberRegex = "\\d+";
			
			Pattern compile = Pattern.compile(regex);
			Pattern numberCompile = Pattern.compile(numberRegex);
			
			while ((str = reader.readLine()) != null) {
				Matcher matcher = compile.matcher(str);
				if (matcher.find()) {
					String group = matcher.group(0);
					Matcher numberMatcher = numberCompile.matcher(group);
					if (numberMatcher.find()) {
						String num = numberMatcher.group(0);
						System.out.println(num);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
