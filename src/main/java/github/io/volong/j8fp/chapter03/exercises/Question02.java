package github.io.volong.j8fp.chapter03.exercises;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import github.io.volong.j8fp.Artist;
import github.io.volong.j8fp.SampleData;

public class Question02 {

    public static void main(String[] args) {

        List<Artist> artists = Arrays.asList(SampleData.johnColtrane, SampleData.theBeatles);
        int totalMembers = 0;
        for (Artist artist : artists) {
            Stream<Artist> members = artist.getMembers();
            totalMembers += members.count();
        }
        System.out.println("totalMembers:" + totalMembers);

        Long aLong = artists.stream()
                .map(artist -> artist.getMembers().count())
                .reduce((a, b) -> a + b).get();
        System.out.println("aLong:" + aLong);
    }
}
