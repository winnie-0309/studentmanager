package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormater {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	public static Date toDate(String dateStr) {
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String toDateString(Date date) {
		if (date != null) {
			return sdf.format(date);
		}
		return null;
	}

	public static java.sql.Date toSqlDate(String dateStr) {
		Date date = toDate(dateStr);
		if (date != null) {
			return new java.sql.Date(date.getTime());
		}
		return null;
	}

	public static java.sql.Date toSqlDate(Date date) {
		if (date != null) {
			return new java.sql.Date(date.getTime());
		}
		return null;
	}
	
	public static String sqlDate2String(java.sql.Date sqlDate) {
		if (sqlDate != null) {
			Date date = new Date(sqlDate.getTime());
			return sdf.format(date);
		}
		return null;
	}

}
