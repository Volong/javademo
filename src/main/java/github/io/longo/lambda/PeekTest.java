package github.io.longo.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * http://www.java67.com/2016/09/java-8-streampeek-example.html
 */
public class PeekTest {

    public static void main(String[] args) {

        List<String> versions = new ArrayList<>();
        versions.add("Lollipop");
        versions.add("KitKat");
        versions.add("Jelly Bean");
        versions.add("Ice Cream Sandwidch");
        versions.add("Honeycomb");
        versions.add("Gingerbread");

        List<String> collect = versions.stream().filter(s -> s.length() > 7)
                         .peek(s -> System.out.println("第 1 次过滤后的值为:" + s))
                         .filter(s -> s.startsWith("H"))
                         .peek(s -> System.out.println("第 2 次过滤后的值为:" + s))
                         .collect(Collectors.toList());
        
        System.out.println("最终的值为:" + collect);
    }
}
