package demo01;

import com.alibaba.fastjson.JSONObject;

public class User {
	
	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public static void main(String[] args) {

	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < 10000; i++) {
	        JSONObject json = new JSONObject();
	        json.put("id", i);
	        json.put("pj_title", "I like Solr");
	        json.put("pj_source", i);
	        sb.append(json.toString()).append(",");
	    }
	    System.out.println(sb.toString());
	}
	
}
