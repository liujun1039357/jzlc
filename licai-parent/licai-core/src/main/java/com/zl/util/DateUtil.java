package com.zl.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间装换帮助类
 * 
 * @author Ivy
 *
 */
public class DateUtil {
	/**
	 * 把日期转换为 yyyy-MM-dd 00:00:00
	 * 
	 * @param date 被转换的日期
	 * @return
	 */
	public static Date startOfDate(Date date) {
		if (date != null) {
			// 使用Calendar (日历类)来进行转换
			// 1.得到Calendar的对象 调用getInstance()
			Calendar c = Calendar.getInstance();
			// 2.把java.util.Date 转换为java.util.Calendar类型 调用Calendar的setTime()
			c.setTime(date);
			// 3.把时分秒清零 set()
			c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
			// 4.把Calendar转换为java.util.Date,并返回
			return c.getTime();
		}
		return null;
	}

	/**
	 * 把日期转换为 yyyy-MM-dd 23:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date endOfDate(Date date) {
		if (date != null) {
			// 使用Calendar (日历类)来进行转换
			// 1.得到Calendar的对象 调用getInstance()
			Calendar c = Calendar.getInstance();
			// 2.把java.util.Date 转换为java.util.Calendar类型 调用Calendar的setTime()
			c.setTime(date);
			// 3.把时分秒清零 set()
			c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
			// 4.天数加1
			c.add(Calendar.DATE, 1);
			// 5.秒减1
			c.add(Calendar.SECOND, -1);
			// 6.把Calendar转换为java.util.Date,并返回
			return c.getTime();
		}
		return null;
	}

	/**
	 * 获取两个时间的相隔的秒
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long secondBetween(Date d1, Date d2) {
		return Math.abs((d1.getTime() - d2.getTime())) / 1000;
	}

	/**
	 * 获取两个时间的相隔的天
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long dayBetween(Date d1, Date d2) {
		return Math.abs((d1.getTime() - d2.getTime())) / (1000 * 3600 * 24);
	}

	/**
	 * 指定时间加指定天数后的时间
	 */
	public static Date addDays(Date date, int days) {
		long time = date.getTime(); // 得到指定日期的毫秒数
		days = days * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
		time += days; // 相加得到新的毫秒数
		return new Date(time); // 将毫秒数转换成日期
	}
	
	/**
	 * 获取一年前的日期
	 * @param date
	 * @return
	 */
	public static Date OneYearAgoDate(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
		return y;
	}
	
	/**
	 * 获取一个月前的日期
	 * @param date
	 * @return
	 */
	public static Date OneMonthAgoDate(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date y = c.getTime();
		return y;
	}
}
