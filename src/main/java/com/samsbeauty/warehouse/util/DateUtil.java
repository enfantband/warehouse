package com.samsbeauty.warehouse.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	
	public static String getToday(String format) {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(today);
	}
	public static String getToday() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy H:m");
		return sdf.format(today);
	}
}
