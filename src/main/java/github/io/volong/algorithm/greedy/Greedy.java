package github.io.volong.algorithm.greedy;

import java.util.ArrayList;
import java.util.List;

public class Greedy {

    public static List<Item> greedyAlgo(List<Item> items) {
        
        int weight = 0;
        
        List<Item> selectItems = new ArrayList<>();
        
        for (int i = 0; i < items.size(); i++) {
            if ((items.get(i).getWeight() + weight) <= 150) {
                weight += items.get(i).getWeight();
                selectItems.add(items.get(i));
            } 
        }
        return selectItems;
    }

    public static void main(String[] args) {
        int[] w= {35, 30, 60, 50, 40, 10, 25};
        int[] p= {10, 40, 30, 50, 35, 40, 30};
        
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
        
        items.sort((o1, o2) -> o2.getPrice() - o1.getPrice());
        
        System.out.println(items);
        
        List<Item> greedyAlgo = greedyAlgo(items);
        
        System.out.println(greedyAlgo);
        
        int sumWeight = greedyAlgo.stream().mapToInt(Item::getWeight).sum();
        System.out.println("总重量为:" + sumWeight);
        
        int sumPrice = greedyAlgo.stream().mapToInt(Item::getPrice).sum();
        System.out.println("总价值为:" + sumPrice);
    }
}
