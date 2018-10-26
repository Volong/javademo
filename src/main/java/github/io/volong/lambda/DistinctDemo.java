package github.io.volong.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream.distinct() 去除重复的数据
 */
public class DistinctDemo {

    public static void main(String[] args) {
        
        List<String> asList = Arrays.asList("a", "b", "a", "c", "d", "e");
        
        List<String> collect = asList.stream().distinct().collect(Collectors.toList());
        
        System.out.println(collect);
    }
}
