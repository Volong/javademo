package github.io.volong.demo01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import github.io.volong.json.JsonUtils;

/**
 * String工具类
 * 
 * @author niexiaolong
 * 
 */
public class StringUtilsApi {

	/**
	 * 下划线转驼峰
	 * 
	 * @param param
	 * @return
	 */
	public static String underlineToCamel(String param) {
		if (StringUtils.isBlank(param)) {
			return null;
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == '_') {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 驼峰转下划线
	 * @param param
	 * @return
	 */
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append('_');
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 字符串反转移
	 * @param str
	 * @return
	 */
	public static String stripslashes(String str){
		if (StringUtils.isBlank(str)) {
			return str;
		}
		char[] sc = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sc.length; i++) {
			if (sc[i]=='\\') {
				if (i+1 <= sc.length-1 && sc[i+1]=='\\') {
					sb.append(sc[i]);
					i++;
				}
			}else{
				sb.append(sc[i]);
			}
		}
		return sb.toString();
	}
	
	@Test
	public void test(){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		
		map.put("1", "123");
		map.put("2", "123");
		map.put("3", "123");
		
		map1.put("1", "123");
		map1.put("2", "123"); 
		map1.put("3", "123");
		
		
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(map);
		list.add(map1);

		String str = JsonUtils.toString(list);
		
		System.out.println(str);
		
	}
}
