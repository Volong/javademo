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
			File f = new File("C:\\Users\\Volong\\Desktop\\2017-11-27_18_54_52-api.log");
			reader = new BufferedReader(new FileReader(f));
			String str = "";
			
			String regex = "mamaquan_boost_dtt";
			
//			Pattern compile = Pattern.compile(regex);
			int i = 0;
			while ((str = reader.readLine()) != null) {
				
				if (str.contains(regex)) {
				    String[] split = str.split("|");
				    reader.readLine();
				    String readLine = reader.readLine();
				    if (readLine.startsWith("WARN")) {
				        System.out.println(readLine);
				        i++;
				    }
				}
			}
			System.out.println(i);
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
	
	/**
	 * 获取联想接口耗时信息
	 * 
	 * @time 2017年9月1日 上午11:22:16
	 * @author volong
	 */
//	@Test
	public static void main(String[] args) {

	    System.out.println(4933 % 6);
	}
	
	/**
	 * 
	 * 获取 BI 联想词接口耗时信息
	 * @time 2017年9月1日 上午11:22:38
	 * @author volong
	 */
	@Test
	public void test04() {

		BufferedReader reader = null;
		try {
			File f = new File("C:\\Users\\Volong\\Desktop\\127\\BI.log");
			reader = new BufferedReader(new FileReader(f));
			String str = "";
			
			String urlRegex = "api/ss/querySsTopKeyword，耗时=(\\d+)ms";
			Pattern urlCompile = Pattern.compile(urlRegex);
			
			while ((str = reader.readLine()) != null) {
				Matcher urlMatcher = urlCompile.matcher(str);
				if (urlMatcher.find()) {
					System.out.println(urlMatcher.group(1));
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
	
	@Test
	public void test05() {
		
		BufferedReader reader = null;
		try {
			File f = new File("C:\\Users\\Volong\\Downloads\\log\\2017-09-05_11-20-32-nodeSpeed-2017-09-04.log");
			reader = new BufferedReader(new FileReader(f));
			String str = "";
			
			String urlRegex = ".*autocomplete-success.*返回前端: (\\d+)ms";
			Pattern urlCompile = Pattern.compile(urlRegex);
			
			while ((str = reader.readLine()) != null) {
				Matcher urlMatcher = urlCompile.matcher(str);
				if (urlMatcher.find()) {
					System.out.println(urlMatcher.group(1));
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
	
	@Test
	public void test06() {
		BufferedReader reader = null;
		try {
			File f = new File("C:\\Users\\Volong\\Desktop\\2017-09-20_09-02-35-api.log");
			reader = new BufferedReader(new FileReader(f));
			String str = "";
			
			
			while ((str = reader.readLine()) != null) {
				if (str.contains("contentType")) {
					System.out.println(str);
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
