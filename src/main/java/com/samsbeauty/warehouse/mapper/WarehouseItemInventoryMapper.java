package com.samsbeauty.warehouse.mapper;

import java.util.List;

import com.samsbeauty.warehouse.model.WarehouseItem;

public interface WarehouseItemInventoryMapper {
	void putInventoryData(List<WarehouseItem> wareseItemList);
	void putInventoryData(WarehouseItem warehouseItem);
}
