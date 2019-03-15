package github.io.volong.j8fp.chapter05;

import github.io.volong.j8fp.Artist;
import github.io.volong.j8fp.SampleData;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example {

    public static void main(String[] args) {

        Stream<Artist> artists = SampleData.threeArtists();
        Optional<Artist> artist = biggestGroup(artists);
        System.out.println(artist.get());

        Artist artist2 = artists.max(Comparator.comparing(artist1 -> artist1.getMembers().count())).get();
        System.out.println(artist2);

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
        return artists.collect(Collectors.maxBy(Comparator.comparing(getCount)));
    }
}
