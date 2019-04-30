package com.samsbeauty.old.model;

public class PendingQuantity {
	private String orderNo;
	private WarehouseInventoryHistoryItemBoxBean warehouseItemBox;
	private String locationCode;
	private Integer quantity;
	
	public PendingQuantity(String orderNo, Integer quantity) {
		this.orderNo = orderNo;
		this.quantity = quantity;
	}	
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public WarehouseInventoryHistoryItemBoxBean getWarehouseItemBox() {
		return warehouseItemBox;
	}

	public void setWarehouseItemBox(WarehouseInventoryHistoryItemBoxBean warehouseItemBox) {
		this.warehouseItemBox = warehouseItemBox;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("orderNo = ");
		sb.append(orderNo);
		sb.append(System.lineSeparator());
		sb.append("quantity = ");
		sb.append(quantity);
		sb.append(System.lineSeparator());
		if(warehouseItemBox != null) {
			sb.append("[warehouseItemBox]");
			sb.append(warehouseItemBox.toString());
			sb.append(System.lineSeparator());
		}
		sb.append("locationCode = ");
		sb.append(locationCode);
		sb.append(System.lineSeparator());
		return sb.toString();
	}
}
