package json;

import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class FastJson {
	
	public static void main(String[] args) {
		
		String str = "{\"code\":0,\"msg\":\"SUCCESS\",\"data\":{\"order_amount\":\"0.50\",\"child_order\":{\"LY17413941382054\":{\"out_type\":0},\"LY17413941382064\":{\"out_type\":1}},\"out_type\":-1,\"order_sn\":\"LY17413941382044\"}}";
		JSONObject json = JsonUtils.toJSON(str);
		JSONObject childOrderSn = json.getJSONObject("data").getJSONObject("child_order");
		Set<String> keySet = childOrderSn.keySet();
		for (String string : keySet) {
			String value = childOrderSn.getString(string);
			System.out.println(string);
			System.out.println(JsonUtils.toJSON(value).getString("out_type"));
			System.out.println(value);
		}
	}
	

}
