package demo01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.analysis.CosineTextSimilarity;
import org.apdplat.word.analysis.TextSimilarity;
import org.apdplat.word.recognition.Punctuation;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import json.JsonUtils;
import utils.DateUtils;

public class Demo {

	
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
//		String html = "<p>测试苏菲的世界了（主题滑雪泡温泉）</p><p><img src='http://7xqwe2.com2.z0.glb.qiniucdn.com/2de697365ed2ff3f95ae79d224d1a2cb.jpg'/><br/></p><p>2131313131313</p><p><br/></p><p><img src='http://7xqwe2.com2.z0.glb.qiniucdn.com/9d1698ce8b0fc0757c08d3cd6c067b9b.jpg'></p><p>sfsfsfsf fsfsf</p><p><img src='http://7xqwe2.com2.z0.glb.qiniucdn.com/15e73ddc3fa3462b1505437d21eb96aa.jpg'/><br/></p><p><br/></p><p><br/></p><p>da陆军司令副教授李副教授刘教授李副教授李副教授范龙介绍</p><p><img src='http://7xqwe2.com2.z0.glb.qiniucdn.com/21dac3b8bba1edbe2605cc4028e9f992.jpg'/></p><p><br/></p><p>适当放松放松放松法</p><p><br/></p><p><img src='http://7xqwe2.com2.z0.glb.qiniucdn.com/ebb00915ae80a9072e87ebbf53866bcb.jpg'/></p><p>放松放松放松孤鸿寡鹄格瑞特台湾</p><p><img src='http://7xqwe2.com2.z0.glb.qiniucdn.com/c4a92fb53d3f6bcb593006963504437a.jpg'/></p>";
		String html = "<font>sss<em>测试苏菲的世界\\t <br/>了（主题滑雪泡温泉）</em>zzzzz</fon";
		Document doc = Jsoup.parse(html);
		String text = doc.text();
		System.out.println(text);
//		Elements es = doc.getAllElements();
//		for (Element e : es) {
//			e.removeAttr("<p>");
//			System.out.println(e);
//		}
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
	
	/**
	 * word分词器的切分效果
	 * 
	 * @time 2017年2月7日 上午11:58:05
	 * @author volong
	 */
	@Test
	public void test21() {
        long start = System.currentTimeMillis();
        String sentence = "淘气贝贝母婴用品出售全新纸尿裤如妈咪宝贝妈咪宝贝男婴用瞬吸干爽纸尿裤(M90片)：48元妈咪宝贝洁净呵护NB30（中包装新生儿-5kg）：28元妈咪宝贝瞬吸干爽-女婴用L44片(9-14kg)35元妈咪宝贝精华本—养育专家信箱：12元妈咪宝贝精华本—孕产专家信箱：12元妈咪宝贝精华本—早教专家信箱：12元妈咪宝贝精华本—儿科专家信箱：60元妈咪宝贝瞬吸干爽M男162片：12元妈咪宝贝小内裤（女）M26p箱装（8包入）120：60元妈咪宝贝纸尿裤经济型均吸干爽S15片装4-8公斤：12元妈咪宝贝纸尿裤经济型均吸干爽M12片装7-11公斤：12元妈咪宝贝纸尿裤经济型均吸干爽L10片装9-14公斤：12元妈咪宝贝纸尿裤经济型均吸干爽XL812公斤以上：12元妈咪宝贝均吸干爽纸尿裤S号30片：18元妈咪宝贝均吸干爽纸尿裤L号20片：18元妈咪宝贝瞬吸干爽XL男16片：20元妈咪宝贝均吸干爽纸尿裤XL号16片：18元妈咪宝贝纸尿裤大码20片（女婴）：20元妈咪宝贝纸尿裤中码24片（女婴：20元妈咪宝贝纸尿裤S286片（女婴）：20元妈咪宝贝均吸干爽纸尿裤M号24片：18元妈咪宝贝小内裤（男）L23p箱装（8包入）：120元妈咪宝贝小内裤（男）XL19p箱装（6包入）：96元妈咪宝贝瞬吸干爽XL女16片：20元妈咪宝贝瞬吸干爽L女20片：20元妈咪宝贝瞬吸干爽L男20片：20元妈咪宝贝瞬吸干爽M女24片：20元妈咪宝贝瞬吸干爽M男24片：20元妈咪宝贝瞬吸干爽女婴用nb66片：35元妈咪宝贝瞬吸干爽女婴用M546片(6-11kg)：35元妈咪宝贝瞬吸干爽S60片（女）：35元妈咪宝贝瞬吸干爽S60片（男）：35元妈咪宝贝瞬吸干爽男婴用M54片(6-11kg)：35元妈咪宝贝瞬吸干爽纸尿裤男婴用L44片(7-14kg)：35元妈咪宝贝瞬吸干爽纸尿裤(新生儿用)NB30片：20元妈咪宝贝纸尿片男宝宝（M）54片28元好奇好奇金装超薄柔软透气超值装NB70片：52元好奇干爽舒适纸尿裤中号便携装M12片：16元好奇干爽舒适超长超强吸收体纸尿裤M52：35元好奇斑点狗—野生动物的小秘密：3元好奇斑点狗—身边动物的小秘密：3元好奇斑点狗—四季的小秘密：3元好奇斑点狗—植物的小秘密：3元好奇斑点狗—自然的小秘密：3元好奇斑点狗—街头的小秘密：3元好奇斑点狗—蔬菜水果的小秘密：3元好奇干爽舒适纸尿裤中号特大超值装M80片：46元好奇干爽舒适超长超强吸收体纸尿裤L42超值：35元好奇金装超薄柔软透气超值装XL32片：48元好奇干爽舒适超长超强吸收体纸尿裤XL34：33元好奇干爽舒适纸尿裤初生号超值装NB66：33元好奇干爽舒适纸尿裤大号特大超值装L64片：46元好奇成长裤男宝宝M21：23元好奇成长裤女宝宝L23：30元好奇成长裤男宝宝XL20：30元好奇成长裤女宝宝XL20(155)片：30元好奇金装超薄柔软透气超值装L40片：51元好奇金装超薄柔软透气超值装M50片：51元韩版好奇纸尿裤金装2段60片装(女婴)：65元好奇金装超薄柔软透气超值特惠装L78片：83元好奇金装超薄柔软透气超值特惠装M96片：83元好奇金装超薄柔软透气特大超值装XL50片：66元好奇金装超薄柔软透气特大超值装L60片：66元好奇金装超薄柔软透气特大超值装M72片：66元好奇干爽舒适超长超强吸收体纸尿裤S60：37元韩版好奇纸尿裤新生儿70片装4.5kg以下男女共用：66元好奇金装超薄柔软透气纸尿裤箱装L100L6：104元韩版好奇纸尿裤金装4段60片装10～14kg男孩用：65元韩版好奇纸尿裤金装5段50片装12kg以上男孩用：65元韩版好奇纸尿裤金装3段70片装7～11kg男孩用";
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
		String fieldName = "[align=left]\r\n[/align][align=left][color=#00B050][font=宋体][size=10.5pt][b]宝宝的小肠胃特别脆弱，之前宝宝小的时候大便经常是不成形的，当然腹泻原因很多，宝宝一般只是轻微的腹泻，一岁后宝宝各种辅食添加，慢慢过度要以吃饭为主后，正常宝宝便便是条状的，而宝宝出现稀便腹泻的情况就显示肠胃出现了状况，这个冬天我们从内外保暖上给宝宝预防腹泻，从内来说，就是饮食上，一定不能吃生冷的食物，一般水果比较凉，我们都要放在室内一会儿，稍微温一些再给宝宝吃，多给宝宝喝温开水，平时吃饭绝不给宝宝从冰箱中直接取出的食物，都是加热后的，尤其奶粉也用适宜温度冲好立即给宝宝喝。外部保暖呢，就是我们的房间有暖气但是保证温度也不要太高，平时给宝宝穿好保暖的内衣裤，外加一个小马甲，护住宝宝的前后心的位置，平时都要穿好小袜子和鞋子。[/b][/size][/font][/color][/align]";
		String replaceAll = fieldName.replaceAll("\r|\n", "xxxooo");
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

	/** 原子自增 */
	@Test
	public void test29() {
		AtomicInteger a = new AtomicInteger(1);
		/*for (int i = 0; i < 3; i++) {
			a.incrementAndGet();
		}*/
		int andAdd = a.getAndAdd(100);
//		int addAndGet = a.addAndGet(100);
		System.out.println(andAdd);
	}
	
	/**
	 * 将GMT格式转换为本地时间
	 * 
	 * @time 2017年1月5日 下午7:44:52
	 * @author volong
	 */
	@Test
	public void test30() {
		 SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);  
		 String time="Fri Dec 30 16:47:10 GMT+08:00 2016";
		 Date date;
		try {
			date = sdf.parse(time);
			String formatDate = DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			System.out.println(formatDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test31() {
		String str = "zzzzz~~~~";
				
		String escapeQueryChars = ClientUtils.escapeQueryChars(str);
		System.out.println(escapeQueryChars);
	}
	
	/**
	 * 解决读取.properties乱码
	 * 
	 * @time 2017年1月15日 下午5:09:20
	 * @author volong
	 */
	@Test
	public void test32() {
		try {
			Properties p = new Properties();
			InputStream resourceAsStream = Demo.class.getClassLoader().getResourceAsStream("xxx.properties");
			InputStreamReader inputStreamReader;
			inputStreamReader = new InputStreamReader(resourceAsStream, "UTF-8");
			p.load(inputStreamReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 一个转义字符长度只增加一位
	 * 
	 * @time 2017年2月4日 下午4:00:40
	 * @author volong
	 */
	@Test
	public void test33() {
		String str = "12\\+3";
		System.out.println(str.length());
	}
	
	@Test
	public void test34() {
		String str = "q=pj_title:两个月宝宝鼻塞严重。急！宝宝刚满两个月，（十月底生的）从出生第二天起，由于受凉鼻塞至今，当时问医生说是由于宝宝鼻腔狭小，毛细血管丰富，受冷鼻黏膜肿胀造成的，很多冬季出生的孩子都这样，别管他自然会好的。后来宝宝的鼻子时好时坏，天热时稍好一些，但从来没痊愈过。有时看这他鼻子塞的透不过气来，那个心痛真是没法形容，试着用各种办法帮他清除过鼻屎，当时通气了，但没过多久又塞住了，而且喝奶老呛，嘴巴老吐泡泡，为此我没少流眼泪。上次又严重了，还在鼻孔边缘看到一点点清涕，上医院听听气管是好的，医生说怕严重来，配了点药（头孢克洛分散片，牛磺酸颗粒）也没什么效果。但当地的医生都说宝宝三个月内最好别用药。这次宝宝洗了一次澡，情况好象更严重了，鼻腔里听起来好象有很多鼻涕，但又不见流出，帮他清除鼻屎后，气是通了，但还是扑呲扑呲的{晚上到早晨就更明显），没一会儿，鼻腔深处又有粘粘的鼻屎，拉出来摸上去还是黏糊糊的。晚上睡觉时，老听宝宝在擤鼻子，似乎想把鼻涕吹出来，可鼻涕没出来，倒听有东西咽到喉咙里，咳嗽了起来，把他抱起来又好多了。（以前鼻子虽塞，也听严重的，但从来没听到这么大的鼻涕声）请问，宝宝这是不是感冒了，要用药吗？还是孩子鼻腔狭小，受冷分泌物增多，天热了就会好的，别管他？急！急！急！^5ORpj_description:两个月宝宝鼻塞严重。急！宝宝刚满两个月，（十月底生的）从出生第二天起，由于受凉鼻塞至今，当时问医生说是由于宝宝鼻腔狭小，毛细血管丰富，受冷鼻黏膜肿胀造成的，很多冬季出生的孩子都这样，别管他自然会好的。后来宝宝的鼻子时好时坏，天热时稍好一些，但从来没痊愈过。有时看这他鼻子塞的透不过气来，那个心痛真是没法形容，试着用各种办法帮他清除过鼻屎，当时通气了，但没过多久又塞住了，而且喝奶老呛，嘴巴老吐泡泡，为此我没少流眼泪。上次又严重了，还在鼻孔边缘看到一点点清涕，上医院听听气管是好的，医生说怕严重来，配了点药（头孢克洛分散片，牛磺酸颗粒）也没什么效果。但当地的医生都说宝宝三个月内最好别用药。这次宝宝洗了一次澡，情况好象更严重了，鼻腔里听起来好象有很多鼻涕，但又不见流出，帮他清除鼻屎后，气是通了，但还是扑呲扑呲的{晚上到早晨就更明显），没一会儿，鼻腔深处又有粘粘的鼻屎，拉出来摸上去还是黏糊糊的。晚上睡觉时，老听宝宝在擤鼻子，似乎想把鼻涕吹出来，可鼻涕没出来，倒听有东西咽到喉咙里，咳嗽了起来，把他抱起来又好多了。（以前鼻子虽塞，也听严重的，但从来没听到这么大的鼻涕声）请问，宝宝这是不是感冒了，要用药吗？还是孩子鼻腔狭小，受冷分泌物增多，天热了就会好的，别管他？急！急！急！&rows=20&start=0&hl=true&hl.fragsize=200&hl.simple.pre=<em>&hl.simple.post=</em>&hl.fl=pj_title,pj_description&q.op=AND&fl=pj_title,pj_description,text_title,id,pj_id,article_type,dateline,intro,days,id&defType=edismax&bf=sum(map(len_description,0,81,-50,100),map(dateline,1454184750,1485720750,50,0),map(pj_source,42,42,100,0))&collection=ptknowledge,baikeart";
		try {
			System.out.println(str.getBytes("utf-8").length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test35() {
		String str = "[i=s] 本帖最后由 易[tag=http://www.mama.cn/baby/]宝宝[/tag]妈 于 2015-9-20 21:17 编辑 [/i]\n\n[size=3]旅程归来刚好开学季";
		String replaceAll = str.replaceAll("\r|\n", "");
		System.out.println(replaceAll);
	}

	@Test
	public void test36() {
		boolean equals = "baike,baikeart".contentEquals("baikeart");
		System.out.println(equals);
	}
	
	@Test
	public void test37() {
		String html = "一起来学习吧。</div><div><br /><div style=\"text-align:center;\"><img src=\"cms_pc_65381\" title=\"宝宝发烧天然去烧法\" alt=\"宝宝发烧天然去烧法\" border=\"0\" /></div></div><h4>　　宝宝发烧天然去烧偏方</h4><div><br /></div><div>　　在宝宝生病发烧的时候，轻易";
		 Map<String,String> bbMap = new HashMap<String , String>();
		 for (Map.Entry<String,String> entry: bbMap.entrySet()) {
	            html = html.replaceAll(entry.getKey().toString(), entry.getValue().toString());
	     }
		 System.err.println(html);

	}
	
	@Test
	public void test38() {

        String text= "淘气贝贝母婴用品出售全新纸尿裤如你要点会员中心注册后点立即购买才行，就是会员价！妈咪宝贝妈咪宝贝男婴用瞬吸干爽纸尿裤(M90片)：48元妈咪宝贝洁净呵护NB30（中包装新生儿-5kg）：28元妈咪宝贝瞬吸干爽-女婴用L44片(9-14kg)35元妈咪宝贝精华本—养育专家信箱：12元妈咪宝贝精华本—孕产专家信箱：12元妈咪宝贝精华本—早教专家信箱：12元妈咪宝贝精华本—儿科专家信箱：60元妈咪宝贝瞬吸干爽M男162片：12元妈咪宝贝小内裤（女）M26p箱装（8包入）120：60元妈咪宝贝纸尿裤经济型均吸干爽S15片装4-8公斤：12元妈咪宝贝纸尿裤经济型均吸干爽M12片装7-11公斤：12元妈咪宝贝纸尿裤经济型均吸干爽L10片装9-14公斤：12元妈咪宝贝纸尿裤经济型均吸干爽XL812公斤以上：12元妈咪宝贝均吸干爽纸尿裤S号30片：18元妈咪宝贝均吸干爽纸尿裤L号20片：18元妈咪宝贝瞬吸干爽XL男16片：20元妈咪宝贝均吸干爽纸尿裤XL号16片：18元妈咪宝贝纸尿裤大码20片（女婴）：20元妈咪宝贝纸尿裤中码24片（女婴：20元妈咪宝贝纸尿裤S286片（女婴）：20元妈咪宝贝均吸干爽纸尿裤M号24片：18元妈咪宝贝小内裤（男）L23p箱装（8包入）：120元妈咪宝贝小内裤（男）XL19p箱装（6包入）：96元妈咪宝贝瞬吸干爽XL女16片：20元妈咪宝贝瞬吸干爽L女20片：20元妈咪宝贝瞬吸干爽L男20片：20元妈咪宝贝瞬吸干爽M女24片：20元妈咪宝贝瞬吸干爽M男24片：20元妈咪宝贝瞬吸干爽女婴用nb66片：35元妈咪宝贝瞬吸干爽女婴用M546片(6-11kg)：35元妈咪宝贝瞬吸干爽S60片（女）：35元妈咪宝贝瞬吸干爽S60片（男）：35元妈咪宝贝瞬吸干爽男婴用M54片(6-11kg)：35元妈咪宝贝瞬吸干爽纸尿裤男婴用L44片(7-14kg)：35元妈咪宝贝瞬吸干爽纸尿裤(新生儿用)NB30片：20元妈咪宝贝纸尿片男宝宝（M）54片28元好奇好奇金装超薄柔软透气超值装NB70片：52元好奇干爽舒适纸尿裤中号便携装M12片：16元好奇干爽舒适超长超强吸收体纸尿裤M52：35元好奇斑点狗—野生动物的小秘密：3元好奇斑点狗—身边动物的小秘密：3元好奇斑点狗—四季的小秘密：3元好奇斑点狗—植物的小秘密：3元好奇斑点狗—自然的小秘密：3元好奇斑点狗—街头的小秘密：3元好奇斑点狗—蔬菜水果的小秘密：3元好奇干爽舒适纸尿裤中号特大超值装M80片：46元好奇干爽舒适超长超强吸收体纸尿裤L42超值：35元好奇金装超薄柔软透气超值装XL32片：48元好奇干爽舒适超长超强吸收体纸尿裤XL34：33元好奇干爽舒适纸尿裤初生号超值装NB66：33元好奇干爽舒适纸尿裤大号特大超值装L64片：46元好奇成长裤男宝宝M21：23元好奇成长裤女宝宝L23：30元好奇成长裤男宝宝XL20：30元好奇成长裤女宝宝XL20(155)片：30元好奇金装超薄柔软透气超值装L40片：51元好奇金装超薄柔软透气超值装M50片：51元韩版好奇纸尿裤金装2段60片装(女婴)：65元好奇金装超薄柔软透气超值特惠装L78片：83元好奇金装超薄柔软透气超值特惠装M96片：83元好奇金装超薄柔软透气特大超值装XL50片：66元好奇金装超薄柔软透气特大超值装L60片：66元好奇金装超薄柔软透气特大超值装M72片：66元好奇干爽舒适超长超强吸收体纸尿裤S60：37元韩版好奇纸尿裤新生儿70片装4.5kg以下男女共用：66元好奇金装超薄柔软透气纸尿裤箱装L100L6：104元韩版好奇纸尿裤金装4段60片装10～14kg男孩用：65元韩版好奇纸尿裤金装5段50片装12kg以上男孩用：6";
        for(String s : Punctuation.seg(text, true)){
            System.err.println(s);
        }
	}
	
	@Test
	public void test39() {
		String text = "淘气贝贝母婴用品出售全新纸尿裤如你要点会员中心注册后点立即购买才行，就是会员价！妈咪宝贝妈咪宝贝男婴用瞬吸干爽纸尿裤(M90片)：48元妈咪宝贝洁净呵护NB30（中包装新生儿-5kg）：28元妈咪宝贝瞬吸干爽-女婴用L44片(9-14kg)35元妈咪宝贝精华本—养育专家信箱：12元妈咪宝贝精华本—孕产专家信箱：12元妈咪宝贝精华本—早教专家信箱：12元妈咪宝贝精华本—儿科专家信箱：60元妈咪宝贝瞬吸干爽M男162片：12元妈咪宝贝小内裤（女）M26p箱装（8包入）120：60元妈咪宝贝纸尿裤经济型均吸干爽S15片装4-8公斤：12元妈咪宝贝纸尿裤经济型均吸干爽M12片装7-11公斤：12元妈咪宝贝纸尿裤经济型均吸干爽L10片装9-14公斤：12元妈咪宝贝纸尿裤经济型均吸干爽XL812公斤以上：12元妈咪宝贝均吸干爽纸尿裤S号30片：18元妈咪宝贝均吸干爽纸尿裤L号20片：18元妈咪宝贝瞬吸干爽XL男16片：20元妈咪宝贝均吸干爽纸尿裤XL号16片：18元妈咪宝贝纸尿裤大码20片（女婴）：20元妈咪宝贝纸尿裤中码24片（女婴：20元妈咪宝贝纸尿裤S286片（女婴）：20元妈咪宝贝均吸干爽纸尿裤M号24片：18元妈咪宝贝小内裤（男）L23p箱装（8包入）：120元妈咪宝贝小内裤（男）XL19p箱装（6包入）：96元妈咪宝贝瞬吸干爽XL女16片：20元妈咪宝贝瞬吸干爽L女20片：20元妈咪宝贝瞬吸干爽L男20片：20元妈咪宝贝瞬吸干爽M女24片：20元妈咪宝贝瞬吸干爽M男24片：20元妈咪宝贝瞬吸干爽女婴用nb66片：35元妈咪宝贝瞬吸干爽女婴用M546片(6-11kg)：35元妈咪宝贝瞬吸干爽S60片（女）：35元妈咪宝贝瞬吸干爽S60片（男）：35元妈咪宝贝瞬吸干爽男婴用M54片(6-11kg)：35元妈咪宝贝瞬吸干爽纸尿裤男婴用L44片(7-14kg)：35元妈咪宝贝瞬吸干爽纸尿裤(新生儿用)NB30片：20元妈咪宝贝纸尿片男宝宝（M）54片28元好奇好奇金装超薄柔软透气超值装NB70片：52元好奇干爽舒适纸尿裤中号便携装M12片：16元好奇干爽舒适超长超强吸收体纸尿裤M52：35元好奇斑点狗—野生动物的小秘密：3元好奇斑点狗—身边动物的小秘密：3元好奇斑点狗—四季的小秘密：3元好奇斑点狗—植物的小秘密：3元好奇斑点狗—自然的小秘密：3元好奇斑点狗—街头的小秘密：3元好奇斑点狗—蔬菜水果的小秘密：3元好奇干爽舒适纸尿裤中号特大超值装M80片：46元好奇干爽舒适超长超强吸收体纸尿裤L42超值：35元好奇金装超薄柔软透气超值装XL32片：48元好奇干爽舒适超长超强吸收体纸尿裤XL34：33元好奇干爽舒适纸尿裤初生号超值装NB66：33元好奇干爽舒适纸尿裤大号特大超值装L64片：46元好奇成长裤男宝宝M21：23元好奇成长裤女宝宝L23：30元好奇成长裤男宝宝XL20：30元好奇成长裤女宝宝XL20(155)片：30元好奇金装超薄柔软透气超值装L40片：51元好奇金装超薄柔软透气超值装M50片：51元韩版好奇纸尿裤金装2段60片装(女婴)：65元好奇金装超薄柔软透气超值特惠装L78片：83元好奇金装超薄柔软透气超值特惠装M96片：83元好奇金装超薄柔软透气特大超值装XL50片：66元好奇金装超薄柔软透气特大超值装L60片：66元好奇金装超薄柔软透气特大超值装M72片：66元好奇干爽舒适超长超强吸收体纸尿裤S60：37元韩版好奇纸尿裤新生儿70片装4.5kg以下男女共用：66元好奇金装超薄柔软透气纸尿裤箱装L100L6：104元韩版好奇纸尿裤金装4段60片装10～14kg男孩用：65元韩版好奇纸尿裤金装5段50片装12kg以上男孩用：6";
		List<Word> segWithStopWords = WordSegmenter.seg(text);
		System.err.println(segWithStopWords);
	}
	
	@Test
	public void test40() {
		AtomicInteger i = new AtomicInteger(10);
//		i.getAndDecrement();
		i.decrementAndGet();
		System.out.println(i);
	}
	
	/**
	 * idf
	 * 
	 */
	@Test
	public void test41() {
		int numDocs = 10795062;
		int docFreq = 33;
		double i = Math.log((numDocs + 1)/(double)( docFreq + 1)) + 1.0;
		System.err.println(i);
	}
	
	/**
	 * queryNorm
	 * 计算每个查询条目的方差和，此值并不影响排序，而仅仅使得不同的query之间的分数可以比较
	 */
	@Test
	public void test42() {
//		0.4375 = (float) (1.0 / Math.sqrt(2))
		System.err.println(1 / 0.625); 
		System.err.println(Math.pow((1 / 0.625), 2)); 
		System.err.println(Math.sqrt(2));
		System.err.println((float) (1.0 / Math.sqrt(2)));
		System.err.println(0.707 / 0.625 );
	}
	
	@Test
	public void test43() {
		int numTermOccurrencesInDocument = 1;
		double tf_tInD = Math.sqrt(numTermOccurrencesInDocument);
		System.err.println(tf_tInD);
		
		int numDocs = 11806226;
		int docFreq = 32005;
		double idf_t = Math.log(numDocs/(double)(docFreq+1)) + 1.0;
		System.err.println(idf_t);
		
		double numTermsInDocumentFromQuery = 1.0;
		double numTermsInQuery = 2;
		double coord_q_d = numTermsInDocumentFromQuery / numTermsInQuery;
		System.err.println(coord_q_d);
		
		double i = 1/Math.sqrt(2);
		System.err.println(i);

	}
	
	@Test
	public void test44() {
		BufferedReader reader = null;
		try {
			File f = new File("E:\\import.2017-06-22_0.log");
			reader = new BufferedReader(new FileReader(f));
			String str = "";
			
			String regex = "提交\\d+条数据到";
			
			String numberRegex = "\\d+";
			
			Pattern compile = Pattern.compile(regex);
			Pattern numberCompile = Pattern.compile(numberRegex);
			
			while ((str = reader.readLine()) != null) {
				Matcher matcher = compile.matcher(str);
				if (matcher.find()) {
					String group = matcher.group(0);
					Matcher numberMatcher = numberCompile.matcher(group);
					if (numberMatcher.find()) {
						String num = numberMatcher.group(0);
						System.out.println(num);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * 获取系统默认字符集以及语言
	 * @time 2017年4月27日 上午10:03:13
	 * @author volong
	 */
	@Test
	public void test45() {
	    String encoding = System.getProperty("file.encoding");
	    System.err.println(encoding);
	    
	    Locale locale = Locale.getDefault();
	    System.out.println(locale);

	    Locale l = new Locale("en");
	    ResourceBundle bundle = ResourceBundle.getBundle("demo01/translations", l);
	    String usedMemory = bundle.getString("usedMemory");
	    System.err.println(usedMemory);
	}
	
	@Test
	public void test46() {
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] allFonts = ge.getAvailableFontFamilyNames();
		
		for (String f : allFonts) {
			System.out.println(f);
		}
		
		Font font1 = new JLabel().getFont();
		System.out.println(font1.getName());
		ResourceBundle bundle = ResourceBundle.getBundle("demo01/translations", Locale.getDefault());
		String usedMemory = bundle.getString("usedMemory");
		    
		Color color = Color.WHITE;
		BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
	
		Graphics2D gd = image.createGraphics();
		Font font = new Font(Font.MONOSPACED, 30 ,30);
		gd.setFont(font);
		gd.setPaint(color);
		gd.drawString(usedMemory, 300, 50);

		System.out.println(gd.getFont().getFontName());
		System.out.println(gd.getFontMetrics().getFont().getName());
		try {
			ImageIO.write(image, "png", new File("D:\\font.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void test47() {
		String datePattern = "10/May/2017:15:28:51 +0800";
		SimpleDateFormat format = new SimpleDateFormat("dd/MMMMM/yyyy:hh:mm:ss", Locale.US);
		
		try {
			Date parse = format.parse(datePattern);
			String dateToStr = DateUtils.dateToStr(parse, "yyyy-MM-dd HH:mm:ss");
			System.out.println(dateToStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test48() {
		
		float accurate = 0.7864f;
//		String f1 = String.format("%.1f", accurate);
		
		System.out.println((int)(accurate * 100) + "%");
		
//		DecimalFormat formater = new DecimalFormat("#0.##");
//		formater.setRoundingMode(RoundingMode.DOWN);
//		
//		String f1 = formater.format(accurate);
//		
//		System.out.println(f1);
//		 
//		System.out.println(f1);
//		float parseFloat = Float.parseFloat(f1);
//		System.out.println(parseFloat);
		
//		DecimalFormat f2 = new DecimalFormat("00%");
//		String format = f2.format(parseFloat);
		
//		DecimalFormat formater = new DecimalFormat("#0.0#%");
//		String format = formater.format(parseFloat);
		
//		NumberFormat nf = NumberFormat.getPercentInstance();
//        nf.setMaximumFractionDigits(2);
//        
//        String format = nf.format(parseFloat);
//		System.out.println(format);
	}
	
	@Test
	public void test49() {
		BufferedReader reader = null;
		try {
			File f = new File("C:\\Users\\Volong\\Desktop\\sql.txt");
			
			reader = new BufferedReader(new FileReader(f));
			String str = "";
			
			int i = 10;
			
			int num = 0;
			// 提交8442条数据到 collection2
			Pattern compile = Pattern.compile("(.*)&q=(.*)&(.*)");
			
			while ((str = reader.readLine()) != null) {
				
				if (str.contains("查询参数为")) {
					String[] split = str.split("\\|");
					String msg = split[6];
//					System.out.println("msg:" + msg);
					Matcher matcher = compile.matcher(msg);
					if(matcher.find()) {
						String q = matcher.group(2);
						String keyword = q.split("&")[0];
						if (keyword.length() > 60) {
							System.out.println(keyword);
						}
					}
				}
			}
			
			System.out.println("num:" + num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		String str = "十个月<em>宝宝</em><em>发烧</em>就<em>发烧</em>了，<em>宝宝</em><em>发烧</em>时候还一直哭，只<em>发烧</em>没有其他症状是怎么回事？";
		
		System.err.println("有<em>的长度:" + str.length());
		System.err.println("无<em>的长度:" + str.replace("<em>", "").replace("</em>", "").length());
		
		// 1. 以 </em> 分隔
		String[] ems = str.split("</em>");
		StringBuilder finalStr = new StringBuilder(35);
		int finalStrLength = 0;
		System.out.println(Arrays.toString(ems));
		
		for (int i = 0; i < ems.length - 1; i++) {
			
			String em = ems[i];
			int length = em.length();
			
			if ((length - 4) <= (35 - finalStrLength)) {
				finalStr.append(em).append("</em>");
			} else {
				String lastEm = em.substring(0, 35 - finalStrLength);
				finalStr.append(em, 0, (35 - finalStrLength));
				break;
			}
			
			finalStrLength += (length - 4);
		}
		
		if (finalStrLength > 35) {
			finalStr.append(ems[ems.length - 1], 0, (35 - finalStrLength));
		}
		
		System.out.println("finalStr:" + finalStr);
		String replace = finalStr.toString().replace("<em>", "").replace("</em>", "");
		System.out.println("replace:" + replace);
		System.out.println("finalStr无<em>的长度:" + replace.length());
		
	}
	
	@Test
	public void testCosine() throws Exception {
		
		long beginTime = System.currentTimeMillis();
		
		FileReader in = new FileReader(new File("C:\\Users\\Volong\\Desktop\\1.txt"));
		BufferedReader reader = new BufferedReader(in);
		
		FileWriter out = new FileWriter(new File("C:\\Users\\Volong\\Desktop\\2.txt"));
		BufferedWriter writer = new BufferedWriter(out);
		
		TextSimilarity textSimilarity = new CosineTextSimilarity();
		String str = "";
		
		List<SimilarInfo> infos = new ArrayList<>();
		
		boolean flag = true;
		int i = 0;
		while((str = reader.readLine()) != null) {
			
			i++;
			double similarScore = textSimilarity.similarScore("孕妇什么最补血的食物", str);
			
			if (infos.size() > 20) {
				
				for (SimilarInfo s : infos) {
					if (similarScore > s.getScore()) {
						infos.remove(s);
						flag = true;
						break;
					}
					flag = false;
				}
			}
			
			if (flag) {
				SimilarInfo info = new SimilarInfo();
				info.setKeyword(str);
				info.setScore(similarScore);
				infos.add(info);
				flag = true;
			}
			
			if (i == 100) {
				break;
			}
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("耗时:" + (endTime - beginTime));
		System.out.println(JsonUtils.toString(infos));
	}
	
	@Test
	public void test50() {
		
		String str = "轻度产后抑郁症的症状";
		TextSimilarity textSimilarity = new CosineTextSimilarity();
		textSimilarity.isSimilar("孕妇什么最补血的食物", str);
	}
	
	@Test
	public void test51() {
		System.out.println("4个月的宝宝这两天有点干咳，咳的时候有点干呕的声音，咳得也没规律有时候一个小时咳一次有时候三个小时咳一次，".length());
	}
	
}


