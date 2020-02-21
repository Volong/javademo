package github.io.longo.ltr.example;

import java.util.Arrays;

/**
 * @since 2019-06-28 18:02
 */
public class Demo {

    public static void main(String[] args) {

        Object[] array = Arrays.asList("A").toArray();
        System.out.println(array.getClass() == Object[].class);
    }
}
