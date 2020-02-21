package github.io.longo.demo01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class ImageProcess {

	/**
	 * 通过后缀获取图片
	 * @time 2017年8月24日 上午9:10:43
	 * @author volong
	 */
	@Test
	public void getImageWithSuffix() {
		
		try {
			File image = new File("D:\\image\\");

			File imageTxt = new File("D:\\yyy.txt");
			FileWriter out = new FileWriter(imageTxt);
			
			BufferedWriter write = new BufferedWriter(out); 
			
			if (image.isDirectory()) {
				String[] list = image.list();
				for (String s : list) {
					System.out.println(s);
					write.write("D:\\image\\" + s);
					write.newLine();
				}
			}
			
			write.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 找出错误的图片并删除
	 * @time 2017年8月24日 上午9:10:53
	 * @author volong
	 */
	@Test
	public void getErrorImageAndDelete() {
		
		try {
			File errorImage = new File("D:\\imageimport.txt");
			
			FileReader in = new FileReader(errorImage);
			
			BufferedReader reader = new BufferedReader(in);
			
			String str = "";
			String regex = "D:.*";
			Pattern compile = Pattern.compile(regex);
			while ((str = reader.readLine()) != null) {
				if (str.startsWith("Error processing file")) {
					Matcher matcher = compile.matcher(str);
					if (matcher.find()) {
						String fileName = matcher.group();
						
						File file = new File(fileName);
						if (file.exists()) {
							if (!file.delete()) {
								System.out.println("未删除:" + fileName);
							}
						} else {
							System.out.println("不存在:" + fileName);
						}
						
					}
				}
			}
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
