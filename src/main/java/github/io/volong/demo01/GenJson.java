package github.io.volong.demo01;

import com.alibaba.fastjson.JSONObject;

public class GenJson {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            JSONObject json = new JSONObject();
            json.put("id", 300 + i);
            json.put("so_title_t", "宝宝发烧");
            
            System.out.println(json);
            System.out.print(",");
        }
    }
}
