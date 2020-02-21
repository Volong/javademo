package github.io.longo.demo01;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.junit.Test;

public class HalfUp {
	
	/**
	 * 在这里使用BigDecimal ，并且采用setScale方法来设置精确度
	 * 同时使用RoundingMode.HALF_UP表示使用最近数字舍入法则来近似计算。
	 */
	@Test
	public void test01(){
		double f = 111231.5585;
		BigDecimal b = new BigDecimal(f);
		double f1 = b.setScale(2,RoundingMode.HALF_UP).doubleValue();
		System.out.println(f1);//111231.56
	}

	/**
	 * #.00 表示两位小数 #.0000四位小数 以此类推… 
	 */
	@Test
	public void test02(){
		double f = 111231.5585;
		DecimalFormat d = new DecimalFormat("#.00");
		String s = d.format(f);
		System.out.println(s);//111231.56
	}
	
	/**
	 * %.2f  %.表示 小数点前任意位数   2 表示两位小数 格式后的结果为f 表示浮点型。
	 */
	@Test
	public void test03(){
		double d = 3.1415926;
		String result = String.format("%.2f",d);
		System.out.println(result);//3.14
	}
}
