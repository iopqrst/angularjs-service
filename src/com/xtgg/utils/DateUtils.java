package com.xtgg.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtils {

	/**
	 * yyyy-MM-dd
	 */
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String LONG_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy年M月dd日 H点m分s秒
	 */
	public static final String CNLONG_DATE_PATTERN = "yyyy年M月dd日 H点m分s秒";

	/**
	 * yyyy年M月dd日
	 */
	public static final String CNSHORT_DATE_PATTERN = "yyyy年M月dd日";

	/**
	 * yyyyMMdd
	 */
	public static final String DATE_PATTERN_PLAIN = "yyyyMMdd";

	public static final String YEAR_PATTERN_PLAIN = "yyyy";
	/** 1:春 ，2:夏 ，3：秋 ，4：冬天 */
	public static final int SEASON_SPRING = 1;
	/** 1:春 ，2:夏 ，3：秋 ，4：冬天 */
	public static final int SEASON_SUMMER = 2;
	/** 1:春 ，2:夏 ，3：秋 ，4：冬天 */
	public static final int SEASON_AUTUMN = 3;
	/** 1:春 ，2:夏 ，3：秋 ，4：冬天 */
	public static final int SEASON_WINTER = 4;

	/**
	 * yyyyMMddHHmmss
	 */
	public static final String LONG_DATE_PATTERN_PLAIN = "yyyyMMddHHmmss";

	private static final java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
			DATE_PATTERN);
	private static final java.text.SimpleDateFormat longDateFormat = new java.text.SimpleDateFormat(
			LONG_DATE_PATTERN);
	private static final java.text.SimpleDateFormat cnLongDateFormat = new java.text.SimpleDateFormat(
			CNLONG_DATE_PATTERN);

	/**
	 * 格式：yyyy-MM-dd
	 */
	public static String format(Object v) {
		if (v == null)
			return null;
		String ret = "";
		try {
			ret = dateFormat.format(v);
		} catch (java.lang.IllegalArgumentException e) {

		}
		return ret;
	}

	/**
	 * 格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String longDate(Object v) {
		if (v == null)
			return null;
		String ret = "";
		try {
			ret = longDateFormat.format(v);
		} catch (java.lang.IllegalArgumentException e) {

		}
		return ret;
	}

	/**
	 * 格式：yyyy年MM月dd日 H点m分s秒
	 */
	public static String cnLongDate(Object v) {
		if (v == null)
			return null;
		String ret = "";
		try {
			ret = cnLongDateFormat.format(v);
		} catch (java.lang.IllegalArgumentException e) {

		}
		return ret;
	}

	/**
	 * 将时间转化成指定格式
	 * 
	 * @param format
	 * @param v
	 * @return
	 */
	public static String formatDate(String format, Object v) {
		if (v == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(v);
	}

	/**
	 * 时间字符串转日期
	 * 
	 * @param dateTime
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String dateTime, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断日期是否为月末
	 * 
	 * @param currDate
	 * @return true月末,false不是月末
	 */
	public static boolean isEndOfMonth(Date currDate) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(currDate);
		if (calendar.get(Calendar.DATE) == calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取前一天
	 */
	public static Date getYesTaday() {
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.MONTH) == 0 && cal.get(Calendar.DAY_OF_YEAR) == 1) {
			cal.roll(Calendar.YEAR, -1);
		}
		cal.roll(Calendar.DAY_OF_YEAR, -1);
		return cal.getTime();
	}

	/**
	 * 得到指定 增加或者减去 天数 Date类型
	 */
	public static Date getAppointDate(Date date, int daysum) {
		java.util.Calendar calstart = java.util.Calendar.getInstance();
		calstart.setTime(date);
		calstart.add(java.util.Calendar.DAY_OF_WEEK, daysum);
		return calstart.getTime();
	}

	/**
	 * 得到指定 增加或者减去 天数 String类型
	 */
	public static String getAppointDateString(Date date, int daysum) {
		return longDate(getAppointDate(date, daysum));
	}

	/***
	 * 得到两个日期相差的天数 *
	 ****/
	public static long getQuot(Date date1, Date date2) {
		long quot = 0;
		quot = date1.getTime() - date2.getTime();
		quot = quot / 1000 / 60 / 60 / 24;
		return Math.abs(quot);
	}

	/**
	 * 得到两个日期相差分钟数
	 * 
	 * @author mayi
	 * @version 2014-12-17 下午05:54:46
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getQuotMinute(Date date1, Date date2) {
		long quot = 0;
		quot = date1.getTime() - date2.getTime();
		quot = quot / 1000 / 60;
		return Math.abs(quot);
	}

	/**
	 * 传入Date类型 获得指定格式的 Date数据 yyyy-MM-dd
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date getDateByType(Date date, String format) {
		return DateUtils.parseDate(DateUtils.longDate(date), format);
	}

	/**
	 * 获取当前日期所在周指定的一天 GXY Oct 19, 2010 11:04:35 AM
	 * 
	 * @param dayNo
	 *            日期所在的序号从1到7
	 * @param flag
	 *            true: 星期天为本周的开始时间 false: 星期一为本周的开始时间
	 */
	public static Date getThisWeekDay(int dayNo, boolean flag) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(flag ? Calendar.SUNDAY : Calendar.MONDAY);
		c.add(Calendar.DATE, -(c.get(Calendar.DAY_OF_WEEK)
				- c.getFirstDayOfWeek() + 1)
				+ dayNo);
		return c.getTime();
	}

	/**
	 * 根据用户生日计算年龄
	 */
	public static Integer getAgeByBirthday(Date birthday) {
		Calendar cal = Calendar.getInstance();

		if (null == birthday) {
			return null;
		}

		if (cal.before(birthday)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		return age;
	}
	
	
	public static Integer getAgeByDate(Date birthday, Date date) {
		Calendar cal = Calendar.getInstance();
		String thisDate = format(date);
		cal.setTime(DateUtils.parseDate(thisDate, DateUtils.DATE_PATTERN));

		if (null == birthday || date == null) {
			return null;
		}

		if (cal.before(birthday)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		return age;
	}

	
	

	/**
	 * 得到时间数组里面的最大时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMaxDate(ArrayList<Date> dates) {
		if (dates.isEmpty()) {
			return null;
		}
		Date dateFirst = null;
		for (int i = 0; i < dates.size(); i++) {
			if (dates.get(i) != null) {
				dateFirst = dates.get(i);
				break;
			}
		}

		ArrayList<Date> deleteDate = new ArrayList<Date>();
		for (int i = 1; i < dates.size(); i++) {
			if (dates.get(i) == null) {
				deleteDate.add(null);
			} else if (dates.get(i).before(dateFirst)) {
				deleteDate.add(dates.get(i));
			}
		}
		if (deleteDate.isEmpty()) {
			if (dates.size() == 1) {
				return dates.get(0);
			}
			dates.remove(dateFirst);
		} else {
			dates.removeAll(deleteDate);
		}
		return getMaxDate(dates);
	}

	/**
	 * 获取传入日期之前的月日期
	 * 
	 * @param date
	 *            传入日期
	 * @param before
	 *            前几个月
	 * @return 返回第前几个月的日期值
	 */
	public static Date getBeforeMonth(String date, int before) {
		Date d = null;
		if (0 == before) {
			d = DateUtils.parseDate(date, DateUtils.DATE_PATTERN);
		} else {
			Calendar c = Calendar.getInstance();
			c.setTime(DateUtils.parseDate(date, DateUtils.DATE_PATTERN));
			c.add(Calendar.MONTH, before);
			d = c.getTime();
		}
		return d;
	}

	/**
	 * 获取传入日期之前的月日期
	 * 
	 * @param date
	 *            传入日期
	 * @param before
	 *            前几个天
	 * @return 返回第前几个天的日期值
	 */
	public static Date getBeforeDay(String date, int before) {
		Date d = null;
		if (0 == before) {
			d = DateUtils.parseDate(date, DateUtils.DATE_PATTERN);
		} else {
			d = getBeforeDay(DateUtils.parseDate(date, DateUtils.DATE_PATTERN),
					before);
		}
		return d;
	}

	public static Date getBeforeDay(Date date, int before) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, before);
		return c.getTime();
	}

	/**
	 * 季节：春夏秋冬
	 * 
	 * @param args
	 */
	public static int getSeasonInt(Date date) {
		String nowYear = DateUtils.formatDate("yyyy", date);

		Date date1 = DateUtils.parseDate(nowYear + "-02-04", "yyyy-MM-dd");
		Date date2 = DateUtils.parseDate(nowYear + "-05-05", "yyyy-MM-dd");
		Date date3 = DateUtils.parseDate(nowYear + "-08-07", "yyyy-MM-dd");
		Date date4 = DateUtils.parseDate(nowYear + "-11-07", "yyyy-MM-dd");

		if (date.before(date1)) {
			return SEASON_WINTER;// 冬
		} else if (date.before(date2)) {
			return SEASON_SPRING;// 春
		} else if (date.before(date3)) {
			return SEASON_SUMMER;// 夏
		} else if (date.before(date4)) {
			return SEASON_AUTUMN;// 秋
		} else {
			return SEASON_WINTER;// 冬
		}
	}

	/**
	 * @param date可以使当前日期
	 *            ,也可以是别的日期
	 * @return 返回数字对应0:星期天 ，1：星期一， 2：星期二， 3：星期三， 4：星期四， 5：星期五， 6：星期六
	 */
	public static int getWeekOfDate(Date date) {
		int[] weekDays = { 0, 1, 2, 3, 4, 5, 6 };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 判断时间格式
	 * 
	 * @param str
	 */
	public static boolean isValidDate(String str, String format) {
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat date = new SimpleDateFormat(format);
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			date.setLenient(false);
			date.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * 判断输入的时间是否在当前时间之后，
	 * 
	 * @param time
	 *            时间 格式支持 yyyy-MM-dd HH:mm:ss ， yyyy-MM-dd ， HH:mm:ss
	 * @return 输入时间在当前时间之后返回true ，否则返回 false
	 */
	public static boolean isOutOtherTime(String time, Date otherTime) {
		boolean boll = false;
		if (isValidDate(time, "HH:mm:ss")) {
			Date nowDate = DateUtils.parseDate(DateUtils.formatDate("HH:mm:ss",
					otherTime), "HH:mm:ss");
			if (parseDate(time, "HH:mm:ss").after(nowDate))
				boll = true;
		}
		if (isValidDate(time, "yyyy-MM-dd HH:mm:ss")) {
			Date nowDate = DateUtils.parseDate(DateUtils.formatDate(
					"yyyy-MM-dd HH:mm:ss", otherTime), "yyyy-MM-dd HH:mm:ss");
			if (parseDate(time, "yyyy-MM-dd HH:mm:ss").after(nowDate))
				boll = true;
		}
		if (isValidDate(time, "yyyy-MM-dd")) {
			Date nowDate = DateUtils.parseDate(DateUtils.formatDate(
					"yyyy-MM-dd", otherTime), "yyyy-MM-dd");
			if (parseDate(time, "yyyy-MM-dd").after(nowDate))
				boll = true;
		}
		return boll;
	}

	/**
	 * 获得本天的开始时间，即2014-10-21 00:00:00
	 * 
	 * @return
	 */
	public static Date getCurrentDayStartTime(Date date) {
		Date current = null;
		if (null == date) {
			current = new Date();
		} else {
			current = date;
		}
		return DateUtils.parseDate(DateUtils.format(current) + " 00:00:00",
				LONG_DATE_PATTERN);
	}

	/**
	 * 获得本天的结束时间，即2014-10-21 23:59:59
	 * 
	 * @return
	 */
	public static Date getCurrentDayEndTime(Date date) {
		Date current = null;
		if (null == date) {
			current = new Date();
		} else {
			current = date;
		}
		return DateUtils.parseDate(DateUtils.format(current) + " 23:59:59",
				LONG_DATE_PATTERN);
	}

	/**
	 * 获取当前月第一天：
	 * 
	 * @author mayi
	 * @version 2014-9-3 上午10:16:54
	 * @return String
	 * @throws ParseException
	 */
	public static Date getFirstMonthDay() throws ParseException {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String first = formatDate(DATE_PATTERN, c.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat(LONG_DATE_PATTERN);
		Date date = sdf.parse(first + " 00:00:00");
		return date;
	}

	/**
	 * 获取当前月第一天：
	 * 
	 * @author mayi
	 * @version 2014-9-3 上午10:16:54
	 * @return String
	 * @throws ParseException
	 */
	public static Date getFirstMonthDay(Date date) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String first = formatDate(DATE_PATTERN, c.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat(LONG_DATE_PATTERN);
		return sdf.parse(first + " 00:00:00");
	}

	/**
	 * 获取当前月最后一天
	 * 
	 * @author mayi
	 * @version 2014-9-3 上午10:22:14
	 * @return String
	 * @throws ParseException
	 */

	public static Date getLastMonthDay() throws ParseException {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = formatDate(DATE_PATTERN, ca.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat(LONG_DATE_PATTERN);
		Date date = sdf.parse(last + " 23:59:59");
		return date;
	}

	/**
	 * 获取当前月最后一天
	 * 
	 * @author mayi
	 * @version 2014-9-3 上午10:22:14
	 * @return String
	 * @throws ParseException
	 */
	public static Date getLastMonthDay(Date date) throws ParseException {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, ca
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = formatDate(DATE_PATTERN, ca.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat(LONG_DATE_PATTERN);
		return sdf.parse(last + " 23:59:59");
	}

	/**
	 * 获取一个月有多少天
	 * 
	 * @author sun
	 * @version 2014-12-29 下午01:55:21
	 * @param date
	 * @return
	 */
	public static int getActualDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取今天是这个月第几天(即今天多少号)
	 * 
	 * @author sun
	 * @version 2014-12-29 下午02:37:06
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	
	/**
	 * 判断输入的时间是否在另一个时间之后，
	 * 
	 * @param time
	 *            时间 格式支持 yyyy-MM-dd HH:mm:ss ， yyyy-MM-dd ， HH:mm:ss
	 * @return 输入时间在当前时间之后返回true ，否则返回 false
	 */
	public static boolean isOutNowTime(String time) {
		boolean boll = false;
		if (isValidDate(time, "HH:mm:ss")) {
			Date nowDate = DateUtils.parseDate(DateUtils.formatDate("HH:mm:ss",
					new Date()), "HH:mm:ss");
			if (parseDate(time, "HH:mm:ss").after(nowDate))
				boll = true;
		}
		if (isValidDate(time, "yyyy-MM-dd HH:mm:ss")) {
			Date nowDate = DateUtils.parseDate(DateUtils.formatDate(
					"yyyy-MM-dd HH:mm:ss", new Date()), "yyyy-MM-dd HH:mm:ss");
			if (parseDate(time, "yyyy-MM-dd HH:mm:ss").after(nowDate))
				boll = true;
		}
		if (isValidDate(time, "yyyy-MM-dd")) {
			Date nowDate = DateUtils.parseDate(DateUtils.formatDate(
					"yyyy-MM-dd", new Date()), "yyyy-MM-dd");
			if (parseDate(time, "yyyy-MM-dd").after(nowDate))
				boll = true;
		}
		return boll;
	}

	public static Date getExpiresDateTime(int calendarType, int amount) {
		return getExpiresDateTime(null, calendarType, amount, null) ;
	}
	
	public static Date getExpiresDateTime(int calendarType, int amount, String datePattern) {
		return getExpiresDateTime(null, calendarType, amount, datePattern) ;
	}
	
	public static Date getExpiresDateTime(Date startDate, int calendarType, int amount) {
		return getExpiresDateTime(startDate, calendarType, amount, null) ;
	}

	/**
	 * 
	 * @param startDate 开始计算的时间，不传默认为当前时间
	 * @param calendarType 
	 * Calendar.HOUR_OF_DAY/Calendar.DAY_OF_YEAR/
	 * Calendar.MONTH等参考Calendar定义的常量
	 * @param amount 增加/减少相应的数量（减少<0)
	 * @return
	 */
	public static Date getExpiresDateTime(Date startDate, int calendarType, 
			int amount, String datePattern) {
		
		Calendar calendar = Calendar.getInstance();
		
		if(null != startDate) {
			calendar.setTime(startDate);
		}
		
		if(StringUtils.isEmpty(datePattern)) {
			datePattern = LONG_DATE_PATTERN;
		}
		
		calendar.add(calendarType, amount);
		Date date = parseDate(formatDate(datePattern, calendar.getTime()),
				datePattern);
		return date;
	}
	

	public static void main(String[] args) {

		System.out.println(DateUtils.getAppointDate(DateUtils.parseDate(
				"2015-01-04", DATE_PATTERN), 100));

		System.out.println(DateUtils.isOutOtherTime("2015-07-19 12:23:21", new Date()));
		
		/***
		 * Calendar cal = Calendar.getInstance(); String birthday = "20030808";
		 * Date b = parseDate(birthday, DATE_PATTERN_PLAIN); cal.setTime(b);
		 * 
		 * Integer age = getAgeByBirthday(cal.getTime());
		 * System.out.println(age);
		 ****/

		// Date aa = DateUtils.parseDate("2013-02-03", "yyyy-MM-dd");
		// System.out.println(DateUtils.getSeasonInt(aa));
		//		
		// Date s = DateUtils.getBeforeDay(new Date(), 1);
		// System.out.println(s);
		// Calendar cal = Calendar.getInstance();
		// System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		
		//System.out.println(getExpiresDateTime(Calendar.MONTH, 2));
		//System.out.println(getExpiresDateTime(Calendar.DAY_OF_YEAR, 1, DATE_PATTERN));
		
		Date birthday = parseDate("1992-08-24", "yyyy-MM-dd");
		Date date = parseDate("2013-06-24", "yyyy-MM-dd");
		
		System.out.println(getAgeByDate(birthday, date));

	}
}
