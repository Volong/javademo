package demo01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class SQL {

	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNext()) {
			
			System.out.println("输入原 fid");
			String orig = scanner.nextLine();
			
			System.out.println("输入要删除的 fid");
			String del = scanner.nextLine();
			
			String[] origs = orig.split(",");
			String[] dels = del.split(",");
			
			List<String> delsList = Arrays.asList(dels);
			
			StringBuilder sb = new StringBuilder();
			
			for (String s : origs) {
				if (delsList.contains(s.trim())) {

				} else {
					sb.append(s).append(",");
				}
			}
			
			sb.deleteCharAt(sb.length() - 1);
			
			System.out.println("fid:" + sb.toString());
		}
	}
}
