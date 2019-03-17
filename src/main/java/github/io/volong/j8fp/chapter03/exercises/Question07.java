package github.io.volong.j8fp.chapter03.exercises;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Question07 {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("aaaB", "aB", "a");
        System.out.println(mostLowercaseString(strings).get());
    }

    public static Optional<String> mostLowercaseString(List<String> strings) {
        return strings.stream().max(Comparator.comparing(Question06::countLowercaseLetters));
    }
}
