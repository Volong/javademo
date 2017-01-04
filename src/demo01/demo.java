package demo01;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.math.NumberUtils;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;
import org.apdplat.word.vector.T;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import json.JsonUtils;

public class demo<T> {

	public void test() {
		Long startTime = System.currentTimeMillis();

		Long a = 0l,b = 0l;
		for (Long val = 0l; val < 1000000000; val += 5)
		{
			a = val * 8;
			b = val / 2;
		}
		System.out.println("a:" + a + "--------" + "b:" + b);
		
		Long midTime = System.currentTimeMillis();
		System.out.println("运行时间: " + (midTime - startTime));
		
		a = 0l;b = 0l;
		for (Long val = 0l; val < 1000000000; val += 5)
		{
			a = val << 3;
			b = val >> 1;
		}
		System.out.println("a:" + a + "--------" + "b:" + b);
		Long endTime = System.currentTimeMillis();

		System.out.println("运行时间:" + (endTime - midTime));

	}
	
	@Test
	public void test01() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("1", 1);
		map.put("2", 2);
		map.put(null, 3);
		map.put(null, null);
		map.put(null, 4);
		map.put("3", null);
		map.put("4", null);
		map.put("map", map);
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
			System.out.println("key:" + entry.getKey() + "---value:" + entry.getValue());
			
		}
		
	}
	
	@Test
	public void test02() {
		String s = "321";
		Integer i = 12345;
		s = s + i;
		System.out.println(s);
	}
	
	@Test
	public void test03() {
		String strUrl = "http://cdn.yaochufa.com/images/RoomImage/hl_58030_r_1_3653c707a3674ddd8329fc11f329ae72.jpg";
		String fileName = strUrl.substring(strUrl.lastIndexOf("/") + 1);
		System.out.println(fileName);
	}
	
	@Test
	public void test04() {
		String html = "<p>测试苏菲的世界了（主题滑雪泡温泉）</p><p><img src='http://7xqwe2.com2.z0.glb.qiniucdn.com/2de697365ed2ff3f95ae79d224d1a2cb.jpg'/><br/></p><p>2131313131313</p><p><br/></p><p><img src='http://7xqwe2.com2.z0.glb.qiniucdn.com/9d1698ce8b0fc0757c08d3cd6c067b9b.jpg'></p><p>sfsfsfsf fsfsf</p><p><img src='http://7xqwe2.com2.z0.glb.qiniucdn.com/15e73ddc3fa3462b1505437d21eb96aa.jpg'/><br/></p><p><br/></p><p><br/></p><p>da陆军司令副教授李副教授刘教授李副教授李副教授范龙介绍</p><p><img src='http://7xqwe2.com2.z0.glb.qiniucdn.com/21dac3b8bba1edbe2605cc4028e9f992.jpg'/></p><p><br/></p><p>适当放松放松放松法</p><p><br/></p><p><img src='http://7xqwe2.com2.z0.glb.qiniucdn.com/ebb00915ae80a9072e87ebbf53866bcb.jpg'/></p><p>放松放松放松孤鸿寡鹄格瑞特台湾</p><p><img src='http://7xqwe2.com2.z0.glb.qiniucdn.com/c4a92fb53d3f6bcb593006963504437a.jpg'/></p>";
		Document doc = Jsoup.parse(html);
		Elements es = doc.getElementsByTag("img");
		for (Element e : es) {
			String attr = e.attr("src");
			System.out.println(attr);
		}
	}
	
	@Test
	public void test05() {
		String fileName = "http://cdn-ly.mama.cn/a2ea1926cbc5adaa0ba417c86c73d5e4.jpg";
		System.out.println(fileName.startsWith("http://cdn-ly.mama.cn"));
		System.out.println(fileName.startsWith("https://cdn-ly.mama.cn"));
		if(!fileName.startsWith("http://cdn-ly.mama.cn") || !fileName.startsWith("https://cdn-ly.mama.cn")){
			System.out.println("xxxx");
		}
	}
	
	@Test
	public void test06() {
		final ExecutorService exec = Executors.newFixedThreadPool(1);  
        
        Callable<String> call = new Callable<String>() {  
            public String call() throws Exception {  
                //开始执行耗时操作  
                Thread.sleep(1000 * 5);  
                return "线程执行完成.";  
            }  
        };  
          
        try {  
            Future<String> future = exec.submit(call);  
            String obj = future.get(1000 * 1, TimeUnit.MILLISECONDS); //任务处理超时时间设为 1 秒  
            System.out.println("任务成功返回:" + obj);  
        } catch (TimeoutException ex) {  
            System.out.println("处理超时啦....");  
            ex.printStackTrace();  
        } catch (Exception e) {  
            System.out.println("处理失败.");  
            e.printStackTrace();  
        }  
        // 关闭线程池  
        exec.shutdown();  
	}
	
	@Test
	public void test07() {
		BigDecimal i = new BigDecimal(0.00);
		int j = i.compareTo(BigDecimal.ZERO);
		System.out.println(j);
	}
	
	@Test
	public void test08() {
		
		List<Integer> goTimes = new ArrayList<Integer>();
		goTimes.add(1);
		goTimes.add(2);
		goTimes.add(-2);
		Collections.sort(goTimes, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;//从小到大
			}
		});
		
		System.out.println(Arrays.toString(goTimes.toArray()));
	}
	
	String str = new String("good");
	char[] ch = {'a', 'b', 'c'};
	
	@Test
	public void test09() {
		
		demo d = new demo();
		d.change(d.str, d.ch);
		System.out.print(d.str + "-");
		System.out.print(d.ch);
		
	}
	
	public void change(String str, char ch[]) {
		str = "test ok";
		ch[0] = 'g';
	}
	
	/**
	 * Byte,Short,Integer,Long 有固定范围: -128 到 127.
	 * 对于 Character,范围是 0 到 127.
	 * 除了 Integer可以通过参数改变范围外,其它的都不行.
	 * @time 2016年10月21日 上午8:47:05
	 * @author volong
	 */
	@Test
	public void test10() {
		Long i1 = 1l;
		Long i2 = 1l;
		if (i1 == i2) {
			System.out.println("i1 == i2");
		} else {
			System.out.println("i1 != i2");
		}
		
		Long i3 = 128l;
		Long i4 = 128l;
		if (i3 == i4) {
			System.out.println("i3 == i4");
		} else {
			System.out.println("i3 != i4");
		}
	}
	
	/**
	 * 判断是否是数字
	 * @time 2016年10月22日 下午1:28:36
	 * @author volong
	 */
	@Test
	public void test11() {
		String value = "";
		boolean number = NumberUtils.isNumber(value);
		System.out.println(number);
	}
	
	/**
	 * 神TM空字符串也是JSON字符串
	 * @time 2016年10月24日 下午1:21:09
	 * @author volong
	 */
	@Test
	public void test12() {
		String value = "";
		boolean json = JsonUtils.isJson(value);
		System.out.println(json);
	}
	
	@Test
	public void test13() {
		String s = "adsfaa%d%s%s";
		s = String.format(s, 123, "wwwww",111);
		System.out.println(s);
	}
	
	@Test
	public void test14() {
		String result = null;
		JSONObject json = JsonUtils.toJSON(result);
		System.out.println(json);
	}
	
	@Test
	public void test15() {
		String s1 = new StringBuilder().append("String").append("Test").toString();
        System.out.println(s1.intern() == s1);

        String s2 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(s2.intern() == s2);
	}

	/**
	 * 然而封装类并不会改变值 
	 * 因为传递的参数是重新复制一份内存地址
	 * @time 2016年11月11日 下午2:56:49
	 * @author volong
	 */
	@Test
	public void test16() {
		Long i = null; 
		check(i);
		System.out.println(i);
	}
	
	public void check(Long i) {
		i = 1L;
	}
	
	/**
	 * 即使是空字符串进行分割
	 * 分割后字符串数组的长度也是1
	 * @time 2016年11月14日 下午1:23:06
	 * @author volong
	 */
	@Test
	public void test17() {
		String[] split = {};
		split = "".split(",");
		int length = split.length;
		System.out.println(Arrays.toString(split).length()>2);
		System.out.println("length:" + length);
		if (split.length <= 0) {
			System.out.println("split:" + split);
		}
	}
	
	@Test
	public void test19() {
		String str = "http://www.wwwwwwhttp:wwwwwwwww";
		str = str.replaceFirst("(http:)|(https:)", "");
		System.out.println(str);
	}

	@Test
	public void test20() {
		Long l = 20000L;
		Long ll = 13000L;
		System.out.println(l>ll);
	}
	
	@Test
	public void test21() {
        long start = System.currentTimeMillis();
        String sentence = "内膜好薄啊";
        int i=1;
        List<Word> words = WordSegmenter.seg(sentence, SegmentationAlgorithm.BidirectionalMaximumMatching);
        
        System.out.println((i++)+"、切分句子: "+sentence);
        System.out.println("    切分结果："+words);
        long cost = System.currentTimeMillis() - start;
        System.out.println("耗时: "+cost+" 毫秒");
    
	}
	
	@Test
	public void test22() {

		for(int i = 1; i < 4;) {
			System.out.println(i);
			i = i << 1;
		}
	}
	
	@Test
	public void test23() {
		String text = "就掉的水。\n每次宝宝发烧";
		if(!Character.isWhitespace(text.charAt(6))){
            //不是空白字符，保留
			System.out.println("text:" + text.charAt(6));
        }
	}
	
	@Test
	public void test24() {
		Field[] fields = User.class.getDeclaredFields();
		String name = fields[0].getName();
		Class<?> name3 = fields[0].getType();
		String name2 = fields[1].getName();
		Class<?> name4 = fields[1].getType();
		
		if (name3 == String.class)
			System.out.println(name + ":" + name3);
		if (name4 == int.class)
			System.out.println(name2 + ":" + name4);
		
	}
	
	@Test
	public void test25() {
		String fieldName = "我的了\n\r个去的\n\r";
		String replaceAll = fieldName.replaceAll("\r|\n", "11");
		System.out.println(replaceAll);
	}
	
	@Test
	public void test26() {
		String entity = "demo01.User";
		try {
			Class<?> forName = Class.forName(entity);
			Field declaredField = forName.getDeclaredField("name");
			String name = declaredField.getName();
			System.out.println(name);
			try {
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test27() {
		Map<String, String> map = new HashMap<>();
		map.put(null, null);
		map.put(null, "bbb");
		System.out.println(map.toString());
	}
	
	@Test
	public void test28() {
		User u = new User();
		u.setName("111");
		try {
			Field field = u.getClass().getDeclaredField("name");
			field.setAccessible(true);
			Object object = field.get(u);
			System.out.println(object);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public static void main(String[] args) {
		demo d = new demo<>();
		d.tVoid();
    }

    private void tVoid() {
    	List<T> tL = new ArrayList<>();
		
		change(tL);
		System.out.println(JsonUtils.toString(tL));
    }
	@SuppressWarnings("unchecked")
	private void change(List<T> tL) {
		List<String> strL = new ArrayList<>();
		strL.add("aaa");
		strL.add("bbb");
		strL.add("ccc");
		tL = (List<T>) strL;
		System.out.println("tl:" + JsonUtils.toString(tL));
	}

}
