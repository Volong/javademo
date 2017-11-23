package util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

/**
 * String工具类
 * 
 * @author niexiaolong
 * 
 */
public class StringUs extends StringUtils {
	 
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
	 * 
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
	 * 16进制字符串转普通字符串
	 * @param uStr
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String hex2String(String uStr) throws UnsupportedEncodingException {
		byte[] bs = new byte[uStr.length() / 2];
		for (int i = 0; i < bs.length; i++) {
			bs[i] = (byte) Integer.parseInt(uStr.substring(i * 2, i * 2 + 2),16);
		}
		return new String(bs, "utf-8");
	}

	/**
	 * 普通字符串转16进制字符串
	 * @param uStr
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String string2Hex(String uStr) throws UnsupportedEncodingException {
		StringBuilder str = new StringBuilder();
		byte[] bs = new byte[] {};
		bs = uStr.getBytes("utf-8");
		for (byte b : bs) {
			str.append(Integer.toHexString(b & 0xff));
		}
		return str.toString();
	}
	
	/**
	 * 完整url转为自协议
	 * http://ly.mama.cn or https://ly.mama.cn   ->   //ly.mama.cn
	 * @param url
	 * @return
	 */
	public static String selfProtocol(String url){
		url = url.substring(url.indexOf(":") +1);
		return url;
	}
}
