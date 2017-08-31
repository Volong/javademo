package utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * 常用的时间操作
 * nxl
 */
public class DateUtils {
	
	/**
	 * 获取当前时间（s）
	 */
	public static int currentTimestamp() {
		return (int)(new Date().getTime()/1000);
	}
	
	public static String formatDateInt(Integer second,String pattern){
		if(second==null || second==0){
			return "";
		}
		SimpleDateFormat comFormat = new SimpleDateFormat(pattern);
		Date date =  new Timestamp(second.longValue() *1000);
		return comFormat.format(date);
	}
	
	/**
	 * 获取指定时间加上一段时间后的时间
	 * @seconds - the current time in seconds
	 * @field - the calendar field.
	 * @n - the amount of date or time to be added to the field.
	 */
	public static Date add(int seconds, int field, int n){
		Date date = new Date(seconds * 1000L);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, n);
		return calendar.getTime();
	}
	
	/**
	 * 获取今天0点时间（s）
	 */
	public static int today0hour() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0); 
		calendar.set(Calendar.SECOND, 0); 
		calendar.set(Calendar.MINUTE, 0); 
		calendar.set(Calendar.MILLISECOND, 0); 
		return (int) (calendar.getTimeInMillis() / 1000); 
	}
	
	/**
	 * 获取明天0点时间（s）
	 */
	public static int tomorrow0hour() {
		return today0hour() + 86400; 
	}
	
	/**
	 * 计算两个日期之间的差距天数
	 */
	public static int cutTwoDateToDay(Date a, Date b) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int theday = 0;
		try {
			Date beginDate = format.parse(format.format(a));
			Date endDate = format.parse(format.format(b));
			
			long begin = beginDate.getTime();
			long end = endDate.getTime();

			theday = (int) ((end - begin) / (86400000));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return theday;
	}

	/**
	 * 取出两个时间出较大的时间
	 */
	public static Date MaxDate(Date a, Date b) {
		if (a.before(b)) {
			return b;
		} else {
			return a;
		}
	}

	/**
	 * 取出两个时间出较小的时间
	 */
	public static Date MinDate(Date a, Date b) {
		if (a.before(b)) {
			return a;
		} else {
			return b;
		}
	}

	/**
	 * 计算给定日期是星期几
	 */
	public static int getWeekOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(java.util.Calendar.DAY_OF_WEEK) - 1;
		if (w == 0){
			w = 7;
		}
		return w;
	}

	/**
	 * 格式化日期
	 */
	public static String formatDate(Date date, String pattern) {
		if(date == null){
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * 格式化日期
	 */
	public static String formatDate(int seconds, String pattern) {
		if(seconds == 0){
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(seconds * 1000L);
	}

	/**
	 * 格式化日期
	 * @throws ParseException 
	 */
	public static Date formatDate(String date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat( pattern );
		Date d = null;
		try {
			d = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * 根据给定日期字符串和日期格式创建日期
	 */
	public static Date strToDate(String dateString, String pattern){
		if(StringUtils.isEmpty(dateString)){
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 根据给定日期字符串和日期格式创建日期
	 */
	public static Integer strToSec(String dateString, String pattern) {
		Date date = DateUtils.strToDate(dateString, pattern);
		return DateUtils.dateToSecond(date);
	}
	
	/**
	 * 当前时间的前后n个月
	 * @param n 正数为后，负数为前
	 */
	public static Date nMonth(int n) {
		Calendar calendar = Calendar.getInstance();   
		calendar.add(Calendar.MONTH, n);    //得到前一个月
		return calendar.getTime(); 
	}
	
	/**
	 * 获取一个月有多少天
	 */
	public static int daysInDate(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DATE);
	}
 
	/**
	 * 给定的Date转为int秒 
	 */
	public static int dateToSecond(Date date){
		return (int)(date.getTime()/1000);
	}
	
	/**
	 * 返回今天至下个月最后一天的秒数列表
	 */
	public static List<Integer> todayToNextMonthLastDayTimestampList(){
		
		List<Integer> result = new ArrayList<Integer>();
		
		Calendar calendar = Calendar.getInstance();   
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		int today = (int)(calendar.getTime().getTime() / 1000);//今天
		
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		 
		int nextMonthLastDay = (int)(calendar.getTime().getTime() / 1000);//下月最后一天
		
		for(int i = today;i <= nextMonthLastDay; i += 86400){
			result.add(i); 
		}
		return result;
	}

	/**
	 * 返回指定时间（s）是一周中的哪一天（周几）
	 */
	public static String dayOfWeek(Integer goTime) {
		String[] days = new String[] {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(goTime * 1000L);
		return days[cal.get(Calendar.DAY_OF_WEEK) - 1];
	}

	public static String dateToStr(Date date, String pattern) {
		if(date == null){
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
}
