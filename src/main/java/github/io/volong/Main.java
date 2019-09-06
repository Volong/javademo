package github.io.volong;

import org.apdplat.word.dictionary.DictionaryFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    public static void main(String[] args) {

        int[] nums = {0, 1, 2};
        List<List<Integer>> permute = permute(nums);

        Object[] objects = permute.toArray();
        System.out.println(Arrays.toString(objects));
    }

    private static void permuteRec(List<Integer> nums, int start, List<List<Integer>> result) {
        if (start == nums.size()) {
            result.add(new ArrayList<>(nums));
        } else {
            for (int i = start; i < nums.size(); ++i) {
                Collections.swap(nums, i, start);
                permuteRec(nums, start + 1, result);
                Collections.swap(nums, i, start);
            }
        }
    }

    // Time: O(n*n!), Space: O(n)
    private static List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> list = new ArrayList<>();
        for (int num : nums) list.add(num);

        permuteRec(list, 0, result);
        return result;
    }


}
