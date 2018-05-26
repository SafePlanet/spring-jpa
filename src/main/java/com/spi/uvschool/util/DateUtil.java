/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * @author:
 */
public class DateUtil {

	private static final int SECOND = 1000;
	private static final int MINUTE = 60 * SECOND;
	private static final int HOUR = 60 * MINUTE;
	private static final int DAY = 24 * HOUR;

	private static final DateTimeFormatter ISO8061_FORMATTER = ISODateTimeFormat
			.dateTimeNoMillis();

	public static Date getDateFromIso8061DateString(String dateString) {
		return ISO8061_FORMATTER.parseDateTime(dateString).toDate();
	}

	public static String getCurrentDateAsIso8061String() {
		DateTime today = new DateTime();
		return ISO8061_FORMATTER.print(today);
	}

	public static String getDateDateAsIso8061String(DateTime date) {
		return ISO8061_FORMATTER.print(date);
	}

	public static long getDateTimeDiff(Date date1, Date date2) {
		long diff = date2.getTime() - date1.getTime();

		return diff;

	}

	public static long getDateTimeDiffInMinutes(Date date1, Date date2) {
		long diff = date2.getTime() - date1.getTime();

		return (diff / MINUTE);
	}

	public static String formatDate(Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		String strDate = dateFormat.format(date);

		return strDate;

	}

	public static String getFormattedStringTime(double ms) {
		StringBuffer text = new StringBuffer("");
		if (ms > DAY) {
			text.append((int) (ms / DAY)).append(" Days ");
			ms %= DAY;
		}
		if (ms > HOUR) {
			text.append((int) (ms / HOUR)).append(" Hours ");
			ms %= HOUR;
		} 
		if (ms > MINUTE) {
			text.append((int) (ms / MINUTE)).append(" Min ");
			ms %= MINUTE;
		}
		if (ms > SECOND) {
			text.append((int) (ms / SECOND)).append(" Sec ");
			ms %= SECOND;
		}
		
		if ("".equals(text.toString())) {
			text.append("0 sec");
		}

		return text.toString();
	}

	public static String getFormattedStringTimeWithSec(double ms) {
		StringBuffer text = new StringBuffer("");
		if (ms > DAY) {
			text.append((int) (ms / DAY)).append(" days ");
			ms %= DAY;
		}
		if (ms > HOUR) {
			text.append((int) (ms / HOUR)).append(" hrs ");
			ms %= HOUR;
		}
		if (ms > MINUTE) {
			text.append((int) (ms / MINUTE)).append(" min ");
			ms %= MINUTE;
		}
		if (ms > SECOND) {
			text.append((int) (ms / SECOND)).append(" sec ");
			ms %= SECOND;
		}

		// text.append(ms + " ms");

		if ("".equals(text.toString())) {
			text.append("0 sec");
		}

		return text.toString();
	}

	public static double getHoursFromMilliseconds(double ms) {
		double totalSecs = ms/1000;
		double hours = (totalSecs / 3600);
		return hours;
	}
	
	public static Date getTime(String time){
		int index = time.indexOf(":");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(time.substring(0, index))); // Your hour
		cal.set(Calendar.MINUTE, Integer.valueOf(time.substring(index+1, time.length()))); // Your Mintue
		cal.set(Calendar.SECOND, 0);
		
		return new Date(cal.getTime().getTime());
	}
	
	public static Date addMinutesToDate(Date beforeTime, int minutes ){
	    final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
	    long curTimeInMs = beforeTime.getTime();
	    Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
	    return afterAddingMins;
	}
	
	public static Date subtractMinutesToDate(Date beforeTime, int minutes ){
	    final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
	    long curTimeInMs = beforeTime.getTime();
	    Date afterAddingMins = new Date(curTimeInMs - (minutes * ONE_MINUTE_IN_MILLIS));
	    return afterAddingMins;
	}
	
	public static Date addSecondssToDate(Date beforeTime, int minutes){
	    final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
	    long curTimeInMs = beforeTime.getTime();
	    Date afterAddingMins = new Date(curTimeInMs - (minutes * ONE_MINUTE_IN_MILLIS));
	    return afterAddingMins;
	}
	
	public static Date addHoursToDate(Date beforeTime, int hours ){
	    final long ONE_HOUR_IN_MILLIS = 3600000;//millisecs
	    long curTimeInMs = beforeTime.getTime();
	    Date afterAddingMins = new Date(curTimeInMs + (hours * ONE_HOUR_IN_MILLIS));
	    return afterAddingMins;
	}
	
	public static Date addDaysToDate(Date beforeTime, int days ){
	    final long ONE_DAY_IN_MILLIS = 86400000;//millisecs
	    long curTimeInMs = beforeTime.getTime();
	    Date afterAddingDays = new Date(curTimeInMs + (days * ONE_DAY_IN_MILLIS));
	    return afterAddingDays;
	}
	
	public static Date subtractHoursToDate(Date beforeTime, int hours ){
	    final long ONE_HOUR_IN_MILLIS = 3600000;//millisecs
	    long curTimeInMs = beforeTime.getTime();
	    Date afterAddingMins = new Date(curTimeInMs - (hours * ONE_HOUR_IN_MILLIS));
	    return afterAddingMins;
	}
	
	public static boolean before(Date d1, Date d2){
	    int     t1;
	    int     t2;

	    t1 = (int) (d1.getTime() % (24*60*60*1000L));
	    t2 = (int) (d2.getTime() % (24*60*60*1000L));
	    return (t1 - t2) <= 0;
	}
	
	public static java.sql.Date convertUtilDateToSQLDate(Date utilDate){
		if(utilDate == null) return null;
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		return sqlDate;
	}
	
	public static Date getDateOnly(Date date){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date todayWithZeroTime = null;
		try {
			todayWithZeroTime = formatter.parse(formatter.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return todayWithZeroTime;
	}
	
	public static Date getTimeOnly(Date date){
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

		Date timeOnly = null;
		try {
			timeOnly = formatter.parse(formatter.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timeOnly;
	}
}
