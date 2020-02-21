package github.io.longo.j8fp.chapter03.exercises;

public class Question06 {

    public static void main(String[] args) {
        System.out.println(countLowercaseLetters("aBcDM"));
    }

    public static int countLowercaseLetters(String string) {
        return (int) string.chars().filter(c -> Character.isLowerCase(c)).count();
    }
}
