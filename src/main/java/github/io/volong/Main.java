package github.io.volong;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import github.io.volong.util.FileUtil;

public class Main {
    
    public static void main(String[] args) throws IOException {
    
        // solr查询时间为:7ms,qTime为:4ms,elapsedTime为:8ms,当前查询的 collection 为:collection2,客户端为:ptk,具体来源为:PTK_FOOD
        List<String> readTextFile = FileUtil.readTextFile("C:\\Users\\Volong\\Downloads\\2018-07-10_14_37_12-api.log");
        Pattern pattern = Pattern.compile(".*q=(\\S+)&q.op.*");
        Pattern timePattern = Pattern.compile(".*solr查询时间为:(\\d+)ms,.*");

        double total = 0.0;
        double size = 0.0;
        HashSet<String> set = new HashSet<>();
        for (String s : readTextFile) {
            size++;
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
//                int time = Integer.parseInt(matcher.group(1));
//                if (time >= 500) {
//                    String[] split = s.split("\\|");
//                    set.add(split[3]);
//                }
                System.out.println(matcher.group(1));
            }
        }

        
//        for (String s : readTextFile) {
//            if (s.contains("查询参数为") && set.contains(s.split("\\|")[3])) {
//                System.out.println(s);
//            }
//        }
    }
}

