package com.samsbeauty.old.model;

import java.util.List;

public class WarehousePendingOrderLevelBean extends WarehouseLevelBean {
	/* This is for only pending order location */
	private Integer pendingOrderId;
	private List<WarehouseInventoryHistoryItemBoxBean> historyItemBoxes;

	public Integer getPendingOrderId() {
		return pendingOrderId;
	}

	public void setPendingOrderId(Integer pendingOrderId) {
		this.pendingOrderId = pendingOrderId;
	}

	public List<WarehouseInventoryHistoryItemBoxBean> getHistoryItemBoxes() {
		return historyItemBoxes;
	}

	public void setHistoryItemBoxes(List<WarehouseInventoryHistoryItemBoxBean> historyItemBoxes) {
		this.historyItemBoxes = historyItemBoxes;
	}
}
