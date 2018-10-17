package github.io.volong.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * http://www.java67.com/2018/06/java-8-optional-example-ispresent-orElse-get.html 
 */
public class OptionalTest {
    
    private static List<String> listOfBooks = Arrays.asList("Effective Java", "Clean Code", "Test Driven");

    
    public static void main(String[] args) {
        System.out.println(getBook("A"));
        System.out.println(getBook1("E"));
    }
    
    public static String getBook(String letter) {
        
        Optional<String> book = listOfBooks.stream().filter(b -> b.startsWith(letter))
                            .findFirst();
        
        return book.isPresent() ? book.get() : "Book Not Found";
    }
    
    public static String getBook1(String letter) {
        Optional<String> book = listOfBooks.stream().filter(b -> b.startsWith(letter)).findFirst();
        return book.orElse("Book Not Found");
    }
    
}
