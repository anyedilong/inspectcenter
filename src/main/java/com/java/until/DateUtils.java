package com.java.until;


import com.fasterxml.jackson.databind.util.ISO8601Utils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Administrator
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
			"yyyy.MM.dd HH:mm", "yyyy.MM","yyyy-MM-dd HH:mm:ss.SSS" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		if(date == null){
			return "";
		}
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		if (date == null)
			return 0;
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 * 
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static long getDistanceOfTwoDateTime(Date before, Date after) {
		if(null == before || null == after ){
			return 1;
		}
		
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime);
	}

	/**
	 * 格转为ISO8601格式
	 * 
	 * @param date
	 * @param millis
	 *            是否包含毫秒数
	 * @return
	 */
	public static String formatISO8601Date(Date date, boolean millis) {
		return ISO8601Utils.format(date, millis, TimeZone.getDefault());
	}

	/**
	 * 格转为ISO8601格式
	 * 
	 * @param date
	 * @return
	 */
	public static String formatISO8601Date(Date date) {
		return ISO8601Utils.format(date, false, TimeZone.getDefault());
	}

	/**
	 * 格转为ISO8601格式
	 * 
	 * @param date
	 * @return
	 */
	public static String formatISO8601Date(long timeMillis) {
		return formatISO8601Date(new Date(timeMillis));
	}

	public static Date parseISO8601Date(String date) {
		try {
			return ISO8601Utils.parse(date, new ParsePosition(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String utc2Local(String utcTime, String utcTimePatten, String localTimePatten) {
		SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
		utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date gpsUTCDate = null;
		try {
			gpsUTCDate = utcFormater.parse(utcTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat localFormater = new SimpleDateFormat(localTimePatten);
		localFormater.setTimeZone(TimeZone.getDefault());
		String localTime = localFormater.format(gpsUTCDate.getTime());
		return localTime;
	}
	
	/**
	 * utc格式返回本地时间
	 * @param utcTimeStr
	 * @return
	 * @throws ParseException
	 */
	public static String utc2local(String utcTimeStr) throws ParseException{
		utcTimeStr = utcTimeStr.replace("Z", " UTC");//注意是空格+UTC
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS Z");//注意格式化的表达式
		Date d = format.parse(utcTimeStr);
		return formatISO8601Date(d);
	}
	
	/**
	 * 返回当前日期的前一个时间日期，amount为正数 当前时间后的时间 为负数 当前时间前的时间 默认日期格式yyyy-MM-dd
	 * 
	 * @param field
	 *            日历字段 y 年 M 月 d 日 H 时 m 分 s 秒
	 * @param amount
	 *            数量
	 * @param formatStr
	 *            返回时间的格式化字符串 例如：yyyy-MM-dd
	 * @return 一个日期 的 字符串
	 */
	public static String getPreDate(String field, int amount, String formatStr) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if (field != null && !field.equals("")) {
			if (field.equals("y")) {
				calendar.add(Calendar.YEAR, amount);
			} else if (field.equals("M")) {
				calendar.add(Calendar.MONTH, amount);
			} else if (field.equals("d")) {
				calendar.add(Calendar.DAY_OF_MONTH, amount);
			} else if (field.equals("H")) {
				calendar.add(Calendar.HOUR, amount);
			} else if (field.equals("m")) {
				calendar.add(Calendar.MINUTE, amount);
			}
		} else {
			return null;
		}
		return formatDate(calendar.getTime(), formatStr);
	}

	/**
	 *
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
	public static int getAge(Date birthDay) {
		Calendar cal = Calendar.getInstance();
		//出生日期晚于当前时间，无法计算
		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		//当前年份
		int yearNow = cal.get(Calendar.YEAR);
		//当前月份
		int monthNow = cal.get(Calendar.MONTH);
		//当前日期
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		//计算整岁数
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				//当前日期在生日之前，年龄减一
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			}else{
				age--;//当前月份在生日之前，年龄减一
			} }
		return age;
	}

	/**
	 * @Description:
	 * @param param
	 * @param format
	 * @return
	 * @throws Exception
	 * @return Date 返回类型
	 */
	public static Date string2date(String param, String format) {
		Date date;
		try {
			DateFormat df = new SimpleDateFormat(format);
			date = df.parse(param);
		} catch (Exception e) {
			return null;
		}
		return date;
	}

	//获取指定时间的n年后时间
	public static Date getYearAfter (Date date, int n) {
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);//设置起时间
		cal.add(Calendar.YEAR, n);//增加n年
		//cd.add(Calendar.DATE, n);//增加一天  
		//cd.add(Calendar.DATE, -10);//减10天  
		//cd.add(Calendar.MONTH, n);//增加一个月   
		return cal.getTime();
	}
	
    /** 
     * 获取未来 第 past 天的日期  / 过去第 -past 天的日期
     * @param past 
     * @return 
     */  
    public static String getDateCalDay(int past) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);  
        Date today = calendar.getTime();  
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");  
        String result = format.format(today);  
        return result;  
    }
	
    /** 
     * 获取未来 past月或者过去-past月
     * @param past 
     * @return 
     */  
    public static String getDateCalMonth(int past) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + past);  
        Date today = calendar.getTime();  
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");  
        String result = format.format(today);  
        return result;  
    }

    //日期比较方法
    public static int compareDate(Date first, Date second){
    	return first.compareTo(second);
    }
}
