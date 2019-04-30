package com.samsbeauty.old.model;

import java.util.List;

public class OrderItemList extends ListModel {
	private List<OrderItem> list;
	public List<OrderItem> getList() {
		return list;
	}
	public void setList(List<OrderItem> list) {
		this.list = list;
	}
}
