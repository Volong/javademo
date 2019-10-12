package github.io.volong;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        List<Data> datas = new ArrayList<>();

        for (int i = 0; i < 10000;i++) {
            datas.add(new Data());
        }

        Thread.sleep(60 * 60 * 1000);
    }

    private static class Data{

    }
}
