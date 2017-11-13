package demo01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.solr.common.util.Hash;

import util.FileUtil;

public class SQL {

	public static void main(String[] args) {
	
	    try {
            List<String> readTextFile = FileUtil.readTextFile("C:\\Users\\Volong\\Desktop\\sql-full.sql");

            List<String> deltaSqlFile = FileUtil.readTextFile("C:\\Users\\Volong\\Desktop\\sql-delta.sql");
            Map<String, String> m = new HashMap<>();
            String deltaRegex = "(.*_delta_import_sql).*";
            Pattern compile = Pattern.compile(deltaRegex);
            
            for (String s : deltaSqlFile) {
                Matcher matcher = compile.matcher(s);
                if (matcher.matches()) {
                    m.put(matcher.group(1), s);
                }
            }
            // bk_wiki_article_delta_import_sql=
            String regex = "(.*_delta_import_sql)=(.*)";
            Pattern pattern = Pattern.compile(regex);
            for (String s : readTextFile) {
                Matcher matcher = pattern.matcher(s);
                if(matcher.matches()) {
                    System.out.println(matcher.group(1) + "=" + m.get(matcher.group(1)));
                } else {
                    System.out.println(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
