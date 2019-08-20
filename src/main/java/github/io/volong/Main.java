package github.io.volong;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

    public static void main(String[] args) throws IOException {
        // /Users/hlonay/Downloads/2019-08-06_14_02_17-api.log

        BufferedReader reader = new BufferedReader(new FileReader("/Users/hlonay/Downloads/2019-08-06_14_02_17-api.log"));

        String str = null;

        while ((str = reader.readLine()) != null) {

            if (str.contains("solr查询时间为")) {
                String[] split = str.split("\\|");
                System.out.println(split[8]);
            }
        }
    }

}
