package com.samsbeauty.warehouse.util;


public class BarcodeUtil {
	public static String getBarcode(String barcode) {
		String matching = "[a-zA-Z0-9][0-9][0-9][a-zA-Z0-9][0-9]{8}[tT]";
		String matching2 = "[a-zA-Z0-9][0-9][0-9][a-zA-Z0-9][0-9]{8}[pP]";
		if(barcode.matches(matching)){
			barcode = barcode.substring(0, barcode.length()-1);
		} else if(barcode.matches(matching2)) {
			barcode = barcode.substring(0, barcode.length()-1);
		}
		
		return barcode;
	}
	
	public static boolean isGeneratedBarcode(String barcode) {
		String matching = "[a-zA-Z][0-9][0-9][a-zA-Z][0-9]{8}";
		return barcode.matches(matching);
	}
	
	public static boolean isBoxCode(String barcode) {
		return barcode.toUpperCase().startsWith("BOX_") && barcode.length() == 9;
	}
	
	public static String getScanType(String barcode) {
		String matching = "[a-zA-Z0-9][0-9][0-9][a-zA-Z0-9][0-9]{8}[tT]";
		String matching2 = "[a-zA-Z0-9][0-9][0-9][a-zA-Z0-9][0-9]{8}[pP]";
		
		String scanType = "";
		if(barcode.matches(matching)){
			scanType = barcode.substring(barcode.length()-1, barcode.length());
		} else if(barcode.matches(matching2)) {
			scanType = barcode.substring(barcode.length()-1, barcode.length());
		} else {
			scanType = "M";
		}
		
		return scanType;
	}
	
	public static String getBoxCode(String boxBarcode) {
		return boxBarcode.replace("BOX_", "");
	}
	
	public static String getLocationCode(String locationBarcode) {
		return locationBarcode.replace("L_", "");
	}
}
