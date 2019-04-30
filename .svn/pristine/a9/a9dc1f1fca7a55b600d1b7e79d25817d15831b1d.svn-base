package com.samsbeauty.warehouse.util;

import java.util.Comparator;

import com.samsbeauty.old.model.OrderInfoForPicking;

public class PackingOrderComparator implements Comparator<OrderInfoForPicking>{
	public int compare(OrderInfoForPicking o1, OrderInfoForPicking o2) {
		if(o1.getOrderItems() == null || o1.getOrderItems().size() == 0) return -1;
		if(o2.getOrderItems() == null || o2.getOrderItems().size() == 0) return 1;
		if(o1.getOrderItems().get(0).getCompany().compareTo(o2.getOrderItems().get(0).getCompany()) > 0) {
			return 1;
		} else if(o1.getOrderItems().get(0).getCompany().compareTo(o2.getOrderItems().get(0).getCompany()) < 0) {
			return -1;
		}
		return 0;
	}
}
