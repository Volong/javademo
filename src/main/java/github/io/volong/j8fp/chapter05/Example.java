package github.io.volong.j8fp.chapter05;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import github.io.volong.j8fp.Album;
import github.io.volong.j8fp.Artist;
import github.io.volong.j8fp.SampleData;

public class Example {

    public static void main(String[] args) {

        Stream<Artist> artists = SampleData.threeArtists();

        // 成员数最多的乐队
        artists = SampleData.threeArtists();
        Function<Artist, Long> getCount = artist -> artist.getMembers().count();
		Artist maxArtist = artists.max(Comparator.comparing(getCount)).get();
		// maxArtist = artists.collect(Collectors.maxBy(Comparator.comparing(getCount))).get();
        System.out.println(maxArtist);

        
        /*
         * partitioningBy 接受一个流，并将其分成两部分，
         * 它使用 Predicate 对象判断一个元素应该属于哪个部分，并根据布尔值返回一个 Map 到列表
         * 
         * 根据是否只有一个人将乐队进行分组
         * 
         */
        artists = SampleData.threeArtists();
        Map<Boolean, List<Artist>> collect = artists.collect(Collectors.partitioningBy(Artist::isSolo));
        System.out.println(collect);

        /*
         * groupingBy 接受一个分类函数，用来对数据分组，使用的分类器是一个 Function 对象
         *
         * 使用主唱对专辑分组
         *
         */
        
        Stream<Album> albums = SampleData.albums();
        albums.collect(Collectors.groupingBy(album -> album.getMainMusician()));


        /*
         * Collectors.joining 从一个流得到一个字符串，允许用户提供分隔符（用以分隔元素）、前缀和后缀
         *
         * 格式化艺术家的姓名
         * 
         */
        artists = SampleData.threeArtists();
        String collect1 = artists.map(Artist::getName)
                                 .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(collect1);

        /*
         * groupingBy
         *
         * 计算每个艺术家的专辑数
         */
        albums = SampleData.allAlbums();
        Map<Artist, Long> collect2 = albums.collect(Collectors.groupingBy(album -> album.getMainMusician(), Collectors.counting()));
        System.out.println(collect2);

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
