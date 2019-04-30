package com.samsbeauty.warehouse.old.helper;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.old.model.Code;
import com.samsbeauty.old.model.Cost;
import com.samsbeauty.old.model.Inventory;
import com.samsbeauty.old.model.InventoryHistoryList;
import com.samsbeauty.old.model.InventoryList;

@PreAuthorize("isAuthenticated()")
public interface InventoryRestHelper {
	List<Inventory> getInventoryList(List<String> barcodes);
	List<Inventory> getInventoryList(String searchKey, String pageNo, Integer lpp, String searchType) throws IOException;
	List<Code> getInventoryReasonList();
	List<Code> getInventoryStatusList();
	void insertInOutStock(String orderNo, String quantity, String  status, String  barcode, String reason, String gid);
	void updateInventory(String productBarcode, String productId, String statusId, String gid);
	List<Cost> getCostList(String barcode);
	Cost getCost(String barcode) throws Exception;
	void insertCost(String barcode, String price, String salePrice, String gid);
	void updateCost(String costId, String price, String salePrice, String gid);
	void deleteCost(String costId, String gid);
	List<Code> getInventoryLocationList();
	void updateInventoryLocation(String barcode, String location1, String location2, String gid);
	InventoryHistoryList getInventoryHistoryList(String category, String barcode, String pageNo, Integer lpp, String orderBy, String orderDesc);
	void insertOrderToVendor(String productIds, String quantities, String svCode, String gid);
	void updateProductUnitStatus(String productGroup, String productCode, String unitTitle, String useFlag, String gid);
	void updateProductOptionStatus(String productGroup, String productCode, String optionTitle, String useFlag, String gid);
	InventoryList getItemInfoFromServerBySearchKey(String searchKey, String pageNo, Integer lpp, String searchType, String locationId);
}
