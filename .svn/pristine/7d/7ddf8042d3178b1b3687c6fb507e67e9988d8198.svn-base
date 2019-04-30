package com.samsbeauty.warehouse.util;

public class LocationUtil {
	private static final String[] regexps = {
		"loc_[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]",
		"LOC_[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]",
		"l_[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]",
		"L_[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]",
		"[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"
	};
	public static boolean IsLocationBarcode(String locationBarcode) {
		if(locationBarcode == null || locationBarcode.equals("")) return false;
		for(String regex : regexps) {
			if(locationBarcode.matches(regex)) {
				return true;
			}
		}
		return false;
	}
	public static String getIncreaseCode(Integer code, Integer digit) {
		try {
			++code;
			char[] digitStr = new char[digit];
			for(int i=digit-1; i>=0; i--){
				Integer num = 0;
				Integer pow = (int) Math.pow(10, i);
				num = code/pow;
				digitStr[digit - i - 1] = num.toString().charAt(0);
				if(num > 0){
					code = code%pow;	
				}
			}
			return new String(digitStr);
		} catch (NumberFormatException e) {
		}
		return "";
	}
}
