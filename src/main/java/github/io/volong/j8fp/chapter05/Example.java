package github.io.volong.j8fp.chapter05;

import github.io.volong.j8fp.Artist;
import github.io.volong.j8fp.SampleData;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example {

    public static void main(String[] args) {

        // 找出成员最多的乐队
        Stream<Artist> artists = SampleData.threeArtists();
        Optional<Artist> artist = biggestGroup(artists);
        System.out.println(artist.get());

        // 成员数最多的乐队
        artists = SampleData.threeArtists();
        Artist artist2 = artists.max(Comparator.comparing(artist1 -> artist1.getMembers().count())).get();
        System.out.println(artist2);

        // 根据是否只有一个人将乐队进行分组
        artists = SampleData.threeArtists();
        Map<Boolean, List<Artist>> collect = artists.collect(Collectors.partitioningBy(Artist::isSolo));
        System.out.println(collect);

        // 格式化艺术家的姓名
        artists = SampleData.threeArtists();
        String collect1 = artists.map(Artist::getName).collect(Collectors.joining(", ", "[", "]"));
        System.out.println(collect1);
    }

    /**
     *
     * 找出成员最多的乐队
     *
     * @param artists
     * @return
     */
    public static Optional<Artist> biggestGroup(Stream<Artist> artists) {
        Function<Artist, Long> getCount = artist -> artist.getMembers().count();
        return artists.max(Comparator.comparing(getCount));
//        return artists.collect(Collectors.maxBy(Comparator.comparing(getCount)));
    }
}
