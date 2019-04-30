package com.samsbeauty.warehouse.old.helper;

import com.samsbeauty.old.model.InventoryBean;
import com.samsbeauty.old.model.JSONReturnModel;
import com.samsbeauty.old.model.LocationItemInput;
import com.samsbeauty.old.model.LocationItemTransferInput;
import com.samsbeauty.old.model.ReturnMessageWithList;
import com.samsbeauty.old.model.WarehouseItemBean;

public interface WarehouseRestHelper {
	JSONReturnModel<String> addLocation(LocationItemInput<InventoryBean> itemInput) throws Exception;
	ReturnMessageWithList<WarehouseItemBean> getWarehouseItemList(String locationBarcode, String boxBarcode, String gid) throws Exception;
	JSONReturnModel<String> deleteItemInLocation(String itemId, String gid) throws Exception;
	JSONReturnModel<String> deleteItemsInLocation(LocationItemInput<WarehouseItemBean> itemInput) throws Exception;
	JSONReturnModel<String> updateToMakeItemsEmpty(LocationItemInput<WarehouseItemBean> itemInput) throws Exception;
	JSONReturnModel<String> transferItemsToLocation(LocationItemTransferInput itemInput) throws Exception;
}
