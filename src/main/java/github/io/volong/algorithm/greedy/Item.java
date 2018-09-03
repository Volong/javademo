package github.io.volong.algorithm.greedy;

public class Item {

    private int weight;
    
    private int price;
    
    /**
     *  0: 未选中
     *  1: 已选中
     *  2: 不可选
     */
    private int status;
    
    private int num;
    
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Item [weight=" + weight + ", price=" + price + ", status=" + status + ", num=" + num + "]\n";
    }
 
}
