package github.io.volong.demo01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

public class MapSort {

	/**
	 * 根据TreeMap的key值来进行排序
	 * 能够把它保存的记录根据key排序,默认是按升序排序，也可以指定排序的比较器
	 * 当用Iterator 遍历TreeMap时，得到的记录是排过序的。
	 * TreeMap不允许key的值为null。非同步的。
	 */
	@Test
	public void testTreeMapKey(){
		Map<String,String> map = new TreeMap<String,String>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// 降序排列
				return o2.compareTo(o1);
			}
		});
		
		map.put("c", "ccccc");
        map.put("a", "aaaaa");
        map.put("b", "bbbbb");
        map.put("d", "ddddd");
        
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();
        while(it.hasNext()){
        	String key = it.next();
        	System.out.println(key+":"+map.get(key));
        }
	}
	
	@Test
	public void testTreeMapValue(){
		Map<String,String> map = new TreeMap<String,String>();
		map.put("d", "ddddd");
        map.put("b", "bbbbb");
        map.put("a", "aaaaa");
        map.put("c", "ccccc");

        //这里将map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String,String>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
        	//升序排序
			@Override
			public int compare(Entry<String, String> o1, Entry<String, String> o2) {
				return o1.getValue().compareTo(o1.getValue());
			}
		});
        
        for (Entry<String, String> entry : list) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
}
