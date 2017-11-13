package demo01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import util.StringUs;

public class GenMethod {

	public static void main(String[] args) {
		
		File f = new File("C:\\Users\\Volong\\Desktop\\lire.txt");
		
		FileReader in;
		try {
			in = new FileReader(f);
			BufferedReader reader = new BufferedReader(in);
			
			String str = "";
			while ((str = reader.readLine()) != null) {
				
				if (StringUs.isNotBlank(str)) {
					System.out.println("@Field(\"" + str + "\")");
					System.out.println("private String " + str + ";");
				}
			}
			
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
