package github.io.volong.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将多个集合变成一个集合
 * http://www.java67.com/2016/03/how-to-use-flatmap-in-java-8-stream.html
 */
public class FlatMapTest {

    public static void main(String[] args) {

        List<String> teamIndia = Arrays.asList("Virat", "Dhoni", "Jadeja");

        String collect1 = teamIndia.stream().collect(Collectors.joining(" OR "));
        System.out.println("collect1:" + collect1);


        List<String> teamAustralia = Arrays.asList("Warner", "Watson", "Smith");
        List<String> teamEngland = Arrays.asList("Alex", "Bell", "Broad");
        List<String> teamNewZeland = Arrays.asList("Kane", "Nathan", "Vettori");
        List<String> teamSouthAfrica = Arrays.asList("AB", "Amla", "Faf");
        List<String> teamWestIndies = Arrays.asList("Sammy", "Gayle", "Narine");
        List<String> teamSriLanka = Arrays.asList("Mahela", "Sanga", "Dilshan");
        List<String> teamPakistan = Arrays.asList("Misbah", "Afridi", "Shehzad");

        List<List<String>> playersInWorldCup2016 = new ArrayList<>();
        playersInWorldCup2016.add(teamIndia);
        playersInWorldCup2016.add(teamAustralia);
        playersInWorldCup2016.add(teamEngland);
        playersInWorldCup2016.add(teamNewZeland);
        playersInWorldCup2016.add(teamSouthAfrica);
        playersInWorldCup2016.add(teamWestIndies);
        playersInWorldCup2016.add(teamSriLanka);
        playersInWorldCup2016.add(teamPakistan);

        List<String> collect = playersInWorldCup2016.stream().flatMap(l -> l.stream()).collect(Collectors.toList());
        
        System.out.println(collect);
    }

}
