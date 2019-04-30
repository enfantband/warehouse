package com.samsbeauty.warehouse;

public class DateUtil {
	private final static String DATE_FORMAT_REGEXES[] = {
			"[0-9][0-9]\\/[0-9][0-9]\\/[0-9][0-9][0-9][0-9]",
			"[0-9]\\/[0-9][0-9]\\/[0-9][0-9][0-9][0-9]",
			"[0-9]\\/[0-9]\\/[0-9][0-9][0-9][0-9]",
			"[0-9][0-9]\\/[0-9]\\/[0-9][0-9][0-9][0-9]"
	};
	public final static String DATE_FORMAT = "MM/dd/yyyy";
	public final static String DATE_FORMAT_REPORT = "MM/yyyy";
	
	public static boolean isValidDateFormat(String date) {
		if(date == null) return false;
		for(String regex : DATE_FORMAT_REGEXES) {
			if(date.matches(regex)) {
				return true;
			}	
		}
		return false;
	}
}
