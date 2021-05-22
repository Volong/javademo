package github.io.ylongo.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class J8Main {


    public static void trackStatistics(Album album) {

        IntSummaryStatistics intSummaryStatistics = album.getTracks().mapToInt(Track::getLength).summaryStatistics();

        int max = intSummaryStatistics.getMax();
        int min = intSummaryStatistics.getMin();
        double average = intSummaryStatistics.getAverage();
        long sum = intSummaryStatistics.getSum();

    }

    /**
     * 找出最大值
     */
    public Optional<Artist> biggestGroup(Stream<Artist> artists) {
        return artists.max(Comparator.comparing(artist -> artist.getMembers().count()));
//        或者
//        Function<Artist, Long> getCount = artist -> artist.getMembers().count();
//        return artists.max(Comparator.comparing(getCount));
    }


    /**
     * 数据分块，将一个集合的数据分成两个集合
     * 集合中的key只能是Boolean类型
     */
    public Map<Boolean, List<Artist>> bandsAndSolo(Stream<Artist> artists) {
        return artists.collect(Collectors.partitioningBy(Artist::isSolo));
    }


    /**
     * 数据分组，与partitioningBy类似，只是返回的集合中key可以是任意类型
     */
    public Map<Artist, List<Album>> albumsByArtist(Stream<Album> albums) {
        return albums.collect(Collectors.groupingBy(Album::getMainMusician));
    }


    /**
     * 字符串拼接
     */
    public String appendName(Stream<Artist> artists) {
        return artists.map(Artist::getName).collect(Collectors.joining(",", "[", "["));
    }


    /**
     * 下游收集器使用
     */
    public Map<Artist, Long> numberOfAlbums(Stream<Album> albums) {
        // 对收集到的元素再进行一次计数处理，所以返回值变成了Long
        return albums.collect(Collectors.groupingBy(Album::getMainMusician, Collectors.counting()));
    }


    public static Map<String, Long> countWords(Stream<String> names) {
        return names.collect(Collectors.groupingBy(name -> name, Collectors.counting()));
    }

    public static void main(String[] args) {

        Stream<String> names = Stream.of("John", "Paul", "George", "John", "Paul", "John");

        System.out.println(countWords(names));
    }
}
