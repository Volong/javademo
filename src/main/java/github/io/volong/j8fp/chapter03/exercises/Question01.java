package github.io.volong.j8fp.chapter03.exercises;

import github.io.volong.demo01.Arr;
import github.io.volong.j8fp.Album;
import github.io.volong.j8fp.Artist;
import github.io.volong.j8fp.SampleData;
import jdk.nashorn.internal.ir.ReturnNode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static github.io.volong.j8fp.SampleData.sampleShortAlbum;
import static java.util.Arrays.asList;

public class Question01 {

    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);
        System.out.println(addUp(integerStream));

        List<Artist> threeArtists = SampleData.getThreeArtists();
        System.out.println(getNamesAndOrigins(threeArtists));

        List<Album> albums = asList(SampleData.manyTrackAlbum, sampleShortAlbum, SampleData.aLoveSupreme);
        System.out.println(getAlbumsWithAtMostThreeTracks(albums));
    }

    public static int addUp(Stream<Integer> numbers) {
//        numbers.reduce(0, (a, b) -> a + b);
        return numbers.reduce((a, b) -> a + b).get();
    }

    public static List<String> getNamesAndOrigins(List<Artist> artists) {
        return artists.stream()
                      .flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
                      .collect(Collectors.toList());
    }


    public static List<Album> getAlbumsWithAtMostThreeTracks(List<Album> input) {
        return input.stream().filter(a -> a.getTrackList().size() <= 3).collect(Collectors.toList());
    }
}
