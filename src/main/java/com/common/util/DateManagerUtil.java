package com.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期操作
 * 
 * @author zjl
 * 
 */
public class DateManagerUtil {

	private static final  Logger logger = LoggerFactory.getLogger(DateManagerUtil.class);
	
	public static String getSolrSearchTime(Date date){
		if(date==null){
			return "";
		}
		SimpleDateFormat fordate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		fordate.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
		return fordate.format(date);
	}
	
	 /**
     * 比较两日期
     * @param date1
     * @param date2
     * @param pattern
     * @return  0 一样，> 0 :data1 > data2,< 0 :data1 < data2
     */
    public static int compareDate(Date date1,Date date2,String pattern){
        if(date1 == null || date2 == null)return -1;
        String d1 = formatDate(date1,pattern);
        String d2 = formatDate(date2,pattern);
        return d1.compareTo(d2);
    }
    
	/**
	 * 把字符串代表的日期转化为Date类型 
	 * @param  value  待转化的字符串日期类型 ，只能是：yyyy-MM-dd 的格式
	 * @return   转化后date日期类型
	 */
	public static Date formateDateByString(String value, String pattern){
		Date date=null;
		try {
		SimpleDateFormat fordate = new SimpleDateFormat(pattern);
			date = fordate.parse(value);
		} catch (ParseException e) {
			logger.error("格式化日期出现错误");
		}
		return date;
	}
	
	
	public static Date formateDateByString(String value){
		Date date=null;
		String formateDateStyle = "yyyy-MM-dd HH:mm:ss";
		
//		String formateDateStyle = "yyyy-MM-dd";
		SimpleDateFormat fordate = new SimpleDateFormat(formateDateStyle);
		try {
			date = fordate.parse(value);
		} catch (ParseException e) {
			logger.error("格式化日期出现错误");
		}
		return date;
	}
	public static String getDefaultSearchTime() {
		Calendar calendar = Calendar.getInstance();
		// int beforeDay = 7;
		// calendar.set(Calendar.DAY_OF_MONTH,
		// calendar.get(Calendar.DAY_OF_MONTH) - beforeDay);

		int beforeYear = 1;
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - beforeYear);
		SimpleDateFormat fordate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String calendarStr = fordate.format(calendar.getTime());
		return calendarStr;
	}

	public static Date preMonthFirstDate(Date date) {
		int month = DateManagerUtil.getMonth(date);
		int year = DateManagerUtil.getYear(date);
		month--;
		if (month == 0) {
			month = 12;
			year--;
		}
		String strDate = year + "-" + month + "-" + "1";
		return getDate(strDate);
	}

	public static Date preMonthLastDate(Date date) {
		int month = DateManagerUtil.getMonth(date);
		int year = DateManagerUtil.getYear(date);
		month--;
		if (month == 0) {
			month = 12;
			year--;
		}
		int daysInMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		String strDate = year + "-" + month + "-" + daysInMonth[month];
		return getDate(strDate);
	}

	public static Date getDate(String value, String patten) {
		Date date = null;
		if (value == null) {
			return new Date();
		}
		SimpleDateFormat fordate = new SimpleDateFormat(patten);
		try {
			date = fordate.parse(value);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date;

	}

	public static Date getDate(String value) {
		Date date = null;
		if (StringUtils.isEmpty(value)) {
			return new Date();
		}
		SimpleDateFormat fordate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = fordate.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}

	public static String getNowTime() {
		Date date = new Date();
		SimpleDateFormat fordate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return fordate.format(date);
	}

	public static String getStrTimeByDate(Date date) {
		if (date == null) {
			return null;
		}
		try {
			SimpleDateFormat fordate = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			return fordate.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date preDate(Date date) {
		long time = date.getTime();
		time = time - 24 * 60 * 60 * 1000;
		Date ytd = new Date(time);
		return ytd;
	}

	public static Date yesterday() {
		Date now = new Date();
		long time = now.getTime();
		time = time - 24 * 60 * 60 * 1000;
		Date ytd = new Date(time);
		return ytd;

	}

	public static Date nextDate(Date date) {

		long time = date.getTime();
		time = time + 24 * 60 * 60 * 1000;
		Date tomorrow = new Date(time);
		return tomorrow;

	}

	public static Date tomorrow() {
		Date now = new Date();
		long time = now.getTime();
		time = time + 24 * 60 * 60 * 1000;
		Date tomorrow = new Date(time);
		return tomorrow;

	}

	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	public static String formatDate(Date date, String formater) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(formater);
		if (date == null) {
			return null;
		} else {
			String datestring = bartDateFormat.format(date);
			return datestring;
		}
	}

	public static String getYearMonth() {
		String dateStr = formatDate(new Date(), "yyyy.MM");
		return dateStr;
	}

	public static String getFormatDate(String format) {
		String dateStr = formatDate(new Date(), format);
		return dateStr;
	}

	/**
	 * 
	 */
	public static Date getDateFromMonth(String value) {
		Date date = null;
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
				java.util.Locale.CHINA);

		Date d = null;
		if (value == null) {
			return new Date();
		}
		try {
			d = df.parse(value);
		} catch (java.text.ParseException e) {
			try {
				d = df.parse("1900-1-1");
			} catch (java.text.ParseException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		date = new java.sql.Date(calendar.getTimeInMillis());
		return date;
	}

	public static String getYearStr(Date date) {
		String a = formatDate(date, "yyyy");
		return a;
	}

	public static int getYear(Date date) {
		String a = formatDate(date, "yyyy");
		int b = Integer.parseInt(a);
		return b;
	}

	public static String getDayStr(Date date) {
		String a = formatDate(date, "dd");
		return a;
	}

	public static int getDay(Date date) {
		String a = formatDate(date, "dd");
		int b = Integer.parseInt(a);
		return b;
	}

	public static int getHour(Date date) {
		String a = formatDate(date, "HH");
		int b = Integer.parseInt(a);
		return b;
	}

	public static String getMonthStr(Date date) {
		String a = formatDate(date, "MM");
		return a;
	}

	public static int getMonth(Date date) {
		String a = formatDate(date, "MM");
		int b = Integer.parseInt(a);
		return b;
	}

	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 取得当前时间
	 * 
	 * @return: yyyyMMdd HHmmss
	 */
	public static String getDateTimeForStr(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(date);
	}
	
	/**
	 * 取得当前时间
	 * 
	 * @return: yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTimeTostr(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	/**
	 * 取得当前时间
	 * 
	 * @return: yyyyMMdd
	 */
	public static String getDateTime(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}

	public static Integer between(Date start, Date end) {
		if (start.getTime() >= end.getTime()) {
			return 0;
		}
		int i = 1;
		start = DateManagerUtil.getDate(DateManagerUtil.formatDate(start));
		end = DateManagerUtil.getDate(DateManagerUtil.formatDate(end));
		while (start.getTime() < end.getTime()) {
			start.setTime(start.getTime() + 24 * 60 * 60 * 1000);
			i++;
		}
		return i;
	}
	
	/**
	 * @description 	某时间 的 多少分钟前 的时间
	 * @param date
	 * @param preMinute
	 * @return
	 * congge 2012-2-15下午05:34:57
	 */
	public static Date preDateMinute(Date date , int preMinute){
		long time  = date.getTime();
		time = time -60*1000*preMinute;
		 Date  preTime  = new Date(time);
		 return preTime;
	}
}