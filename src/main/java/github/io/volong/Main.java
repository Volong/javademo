package github.io.volong;

import github.io.volong.demo01.Source;
import org.apdplat.word.dictionary.DictionaryFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    private static int count = 0;

        public static void main(String[] args) {

            DictionaryFactory.getDictionary().clear();
            DictionaryFactory.getDictionary().addAll(null);
        }

    public static boolean isPalindrome(String s) {

        for (int i = 0, j = s.length() -1; i < j; )  {

            char a = s.charAt(i);
            char b = s.charAt(j);

            if (!isNumAndLetter(a)) {
                i++;
                continue;
            }



            if (!isNumAndLetter(b)) {
                j--;
                continue;
            }



            if (!ignoreLetterEqual(a, b)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    public static boolean isNumAndLetter(char c) {
        return (c >= 'a'  && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <='9');
    }

    public static boolean ignoreLetterEqual(char a, char b) {
        if (a >= 'A' && a <= 'Z') {
            a += 32;
        }

        if (b >= 'A' && b <= 'Z') {
            b += 32;
        }

        return a == b;

    }
}
