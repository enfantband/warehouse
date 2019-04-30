package com.samsbeauty.old.model;

public class OutboundItem {
	private Integer scanned;
	private Integer quantity;
	private String status;
	private OrderItemBean orderItem;
	
	public Integer getScanned() {
		return scanned;
	}
	public void setScanned(Integer scanned) {
		this.scanned = scanned;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public OrderItemBean getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrderItemBean orderItem) {
		this.orderItem = orderItem;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
