package github.io.longo.algorithm.greedy;

import java.util.ArrayList;
import java.util.List;

public class Greedy {

    public static List<Item> greedyAlgo(List<Item> items, SelectPolicy policy) {

        int weight = 0;

        List<Item> selectItems = new ArrayList<>();

        sortItemsWithPolicy(items, policy);

        for (int i = 0; i < items.size(); i++) {
            if ((items.get(i).getWeight() + weight) <= 150) {
                weight += items.get(i).getWeight();
                selectItems.add(items.get(i));
            }
        }
        return selectItems;
    }

    private static void sortItemsWithPolicy(List<Item> items, SelectPolicy policy) {
        if (SelectPolicy.PRICE == policy) {
            items.sort((o1, o2) -> o2.getPrice() - o1.getPrice());
        } else if (SelectPolicy.WEIGHT == policy) {
            items.sort((o1, o2) -> o1.getWeight() - o2.getWeight());
        } else if (SelectPolicy.PRICE_OF_WEIGHT == policy) {
            items.sort((o1, o2) -> (o2.getPrice() / o2.getWeight()) - (o1.getPrice() / o1.getWeight()));
        }
    }

    public static void main(String[] args) {
        int[] w = { 35, 30, 60, 50, 40, 10, 25 };
        int[] p = { 10, 40, 30, 50, 35, 40, 30 };

        List<Item> items = new ArrayList<>();

        for (int i = 0; i < w.length; i++) {
            Item item = new Item();
            item.setWeight(w[i]);
            item.setNum(i + 1);
            items.add(item);
        }

        for (int i = 0; i < p.length; i++) {
            items.get(i).setPrice(p[i]);
        }

        System.out.println(items);

        output(greedyAlgo(items, SelectPolicy.PRICE), SelectPolicy.PRICE);

        output(greedyAlgo(items, SelectPolicy.WEIGHT), SelectPolicy.WEIGHT);

        output(greedyAlgo(items, SelectPolicy.PRICE_OF_WEIGHT), SelectPolicy.PRICE_OF_WEIGHT);
    }

    private static void output(List<Item> greedyAlgo, SelectPolicy policy) {
        int sumWeight = greedyAlgo.stream().mapToInt(Item::getWeight).sum();
        System.out.println("选择策略为: " + policy + " 的总重量为:" + sumWeight);

        int sumPrice = greedyAlgo.stream().mapToInt(Item::getPrice).sum();
        System.out.println("选择策略为: " + policy + " 的总价值为:" + sumPrice);
    }
}
