package com.samsbeauty.warehouse.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samsbeauty.old.model.Inventory;
import com.samsbeauty.warehouse.exception.rest.InvalidProductBarcodeException;
import com.samsbeauty.warehouse.model.WarehouseItem;
import com.samsbeauty.warehouse.old.helper.InventoryRestHelper;
import com.samsbeauty.warehouse.util.PathUtil;

@Service
public class WarehouseItemInventoryMapperImpl implements WarehouseItemInventoryMapper {
	
	@Autowired
	private InventoryRestHelper inventoryRestHelper;

	@Override
	public void putInventoryData(List<WarehouseItem> warehouseItemList) {
		List<String> barcodes = new ArrayList<>();
		for(WarehouseItem item : warehouseItemList) {
			barcodes.add(item.getGeneratedBarcode());
		}
		List<Inventory> inventoryList = inventoryRestHelper.getInventoryList(barcodes);
		Map<String, Inventory> inventoryTable = new HashMap<>();
		
		for(Inventory inventory : inventoryList) {
			if(!inventoryTable.containsKey(inventory.getGeneratedBarcode())) {
				inventoryTable.put(inventory.getGeneratedBarcode(), inventory);
			}
			inventory.setProductImage(PathUtil.getProductImagePath(inventory.getProductGroup(), inventory.getProductImage()));
			inventory.setProductBigImage(PathUtil.getProductImagePath(inventory.getProductGroup(), inventory.getProductBigImage()));
		}
		for(WarehouseItem item : warehouseItemList) {
			if(inventoryTable.containsKey(item.getGeneratedBarcode())) {
				item.setInventory(inventoryTable.get(item.getGeneratedBarcode()));
			}
		}
	}

	@Override
	public void putInventoryData(WarehouseItem warehouseItem) {
		List<Inventory> inventoryList = inventoryRestHelper.getInventoryList(Arrays.asList(warehouseItem.getGeneratedBarcode()));
		if(inventoryList.size() > 0) {
			warehouseItem.setInventory(inventoryList.get(0));
		}
	}
}
