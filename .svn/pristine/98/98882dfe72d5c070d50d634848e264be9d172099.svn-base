package com.samsbeauty.warehouse.picking.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.old.model.Inventory;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.exception.rest.InvalidProductBarcodeException;
import com.samsbeauty.warehouse.exception.rest.LocationNotFoundException;
import com.samsbeauty.warehouse.model.WarehouseItem;
import com.samsbeauty.warehouse.model.WarehouseItemBox;
import com.samsbeauty.warehouse.model.WarehouseLevel;
import com.samsbeauty.warehouse.picking.model.PickingItem;
import com.samsbeauty.warehouse.picking.model.PickingItemInfo;
import com.samsbeauty.warehouse.picking.model.PickingJob;
import com.samsbeauty.warehouse.picking.model.PickingJobTimeline;

@PreAuthorize("isAuthenticated()")
public interface PickingItemService {
	/**
	 * Returns all picking items (picked, missed or saved) with paging option
	 * 
	 * @param pageNumber an number that current page
	 * @param pageSize how many items per page
	 * @param sort how sort?
	 * @return all picking items
	 */
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	Page<PickingItem> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	
	/**
	 * Get one picking item by id
	 * 
	 * @param pickingItemId id of the picking item
	 * @return pickingItem
	 */
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	PickingItem getOne(Long pickingItemId);
	
	/**
	 * Create or update the pickingItem
	 * 
	 * @param pickingItem
	 * @return pickingItem
	 */
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	PickingItem save(PickingItem pickingItem);
	
	/**
	 * Create or update multiple pickingItems
	 * 
	 * @param pickingItems
	 * @return
	 */
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	List<PickingItem> saveItems(List<PickingItem> pickingItems);
	
	/**
	 * It is to pick item. The picking item may have its location information. If it doesn't have the location, then it will report automatically.
	 * 
	 * @param warehouseLevel location info
	 * @param pickingJob picking job for picking item
	 * @param warehouseItemBox if picking item is in the box, then store box information.
	 * @param warehouseItem which item is picked in the warehouse.
	 * @param picker who picked.
	 * @param timeline which timeline it is...
	 * @param quantity how many item is picked
	 * @param barcode what barcode picker scanned
	 * @param reason this is for that if a picker picked item without scanning the product's barcode then there should be a reason for the action. If item doesn't have any product barcode, then picker could skip the scanning process.
	 * @return PickingItem that newly created
	 * @throws InvalidProductBarcodeException
	 */
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	PickingItem pick(
			WarehouseLevel warehouseLevel, 
			PickingJob pickingJob, 
			WarehouseItemBox warehouseItemBox, 
			WarehouseItem warehouseItem, 
			WarehouseEmployee picker, 
			PickingJobTimeline timeline, 
			Integer quantity,
			String barcode, 
			String reason) throws InvalidProductBarcodeException;
	/**
	 * If is to miss item. If the picking item does not exist in the location then it should be reported. 
	 * Also, if item does not have location information or is in the bulk section, it also needs to be reported.
	 * 
	 * @param warehouseLevel location info
	 * @param pickingJob picking job for picking item
	 * @param picker who missed
	 * @param generatedBarcode sku because picker cannot scan a product's barcode 
	 * @param pickingJobTimeline which timeline
	 * @param quantity how many item you want to skip
	 * @return
	 * @throws LocationNotFoundException
	 */
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	PickingItem miss(WarehouseLevel warehouseLevel, PickingJob pickingJob, WarehouseEmployee picker, String generatedBarcode, PickingJobTimeline pickingJobTimeline, Integer quantity) throws LocationNotFoundException;
	/**
	 * This is an added function on 12/13/2016. When a picker goes to pick an item at the first, there is a change that the item does not exist. 
	 * And, if the picker knew that the item is in bulk section, he/she will save it for later picking all together in the bulk section.
	 * 
	 * @param pickingJob
	 * @param picker
	 * @param inventory
	 * @param currentTimeline
	 * @param quantity
	 * @return
	 */
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	PickingItem saveForLater(PickingJob pickingJob, WarehouseEmployee picker, Inventory inventory, PickingJobTimeline currentTimeline, Integer quantity);

	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	void delete(Long pickingItemId);
}