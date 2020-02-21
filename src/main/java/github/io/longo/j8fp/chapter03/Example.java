package github.io.longo.j8fp.chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import github.io.longo.j8fp.Artist;
import github.io.longo.j8fp.SampleData;
import github.io.longo.j8fp.Track;

public class Example {

    public static void main(String[] args) {

        List<Artist> allArtists = SampleData.getThreeArtists();
// ---
        // 使用内部迭代计算来自伦敦的艺术家人数
        allArtists.stream()
                  .filter(artist -> artist.isFrom("London"))
                  .count();

        // 惰性求值方法：并不会有值输出
        allArtists.stream()
                  .filter(artist -> {
                      System.out.println(artist.getName());
                      return artist.isFrom("UK");
                  });

        // 及早求值方法：会有值输出
        allArtists.stream()
                  .filter(artist -> {
                      System.out.println(artist.getName());
                      return artist.isFrom("UK");
                  }).count();


        // collect(toList())
        List<String> collect = Stream.of("a", "b", "c") // 使用一组初始值生成新的Stream
                                     .collect(Collectors.toList()); //

        // map
        List<String> collected = new ArrayList<>();
        for (String string : Arrays.asList("a", "b", "hello")) {
            String uppercaseString = string.toUpperCase();
            collected.add(uppercaseString);
        }


        List<String> collect1 = Stream.of("a", "b", "hello")
                                      .map(str -> str.toUpperCase())
                                      .collect(Collectors.toList());


        // filter
        List<String> beginningWithNumbers = new ArrayList<>();
        for(String value : Arrays.asList("a", "1abc", "abc1")) {
            if (Character.isDigit(value.charAt(0))) {
                beginningWithNumbers.add(value);
            }
        }

        List<String> collect2 = Stream.of("a", "1abc", "abc1")
                .filter(str -> Character.isDigit(str.charAt(0)))
                .collect(Collectors.toList());


        // flatMap
        Stream.of(Arrays.asList("1", "2"), Arrays.asList("3", "4"))
              .flatMap(arr -> arr.stream())
              .collect(Collectors.toList());

        // max
        List<Track> tracks = Arrays.asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));

        tracks.stream()
              .max(Comparator.comparing(track -> track.getLength()))
              .get();

        // reduce
        Integer reduce = Stream.of(1, 2, 3)
                .reduce(0, (a, b) -> a + b);

    }
}
