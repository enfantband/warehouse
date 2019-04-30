package com.samsbeauty.warehouse.util;

public class UrgentShippingChecking {
	public static boolean checkUrgentShipping(String shippingMethod) {
		if(shippingMethod == null || shippingMethod.equals("")) return false;
		
		if(shippingMethod.toUpperCase().indexOf("UPS") != -1) {
			return true;
		} else if(shippingMethod.toUpperCase().indexOf("EXPRESS") != -1){
			return true;
		} else if(shippingMethod.indexOf("Pick Up") != -1) {
			return true;
		}
		return false;				
	}
}
