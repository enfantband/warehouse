package com.samsbeauty.warehouse.picking.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samsbeauty.old.model.Inventory;
import com.samsbeauty.warehouse.adjustment.model.AdjustmentRequest;
import com.samsbeauty.warehouse.adjustment.service.AdjustmentRequestService;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.exception.rest.InvalidProductBarcodeException;
import com.samsbeauty.warehouse.exception.rest.LocationNotFoundException;
import com.samsbeauty.warehouse.model.WarehouseItem;
import com.samsbeauty.warehouse.model.WarehouseItemBox;
import com.samsbeauty.warehouse.model.WarehouseLevel;
import com.samsbeauty.warehouse.old.helper.InventoryRestHelper;
import com.samsbeauty.warehouse.picking.model.PickingItem;
import com.samsbeauty.warehouse.picking.model.PickingItemInfo;
import com.samsbeauty.warehouse.picking.model.PickingJob;
import com.samsbeauty.warehouse.picking.model.PickingJob.PickingJobStatus;
import com.samsbeauty.warehouse.picking.model.PickingJobGroup;
import com.samsbeauty.warehouse.picking.model.PickingJobTimeline;
import com.samsbeauty.warehouse.picking.repository.PickingItemInfoRepository;
import com.samsbeauty.warehouse.picking.repository.PickingItemRepository;
import com.samsbeauty.warehouse.picking.repository.PickingJobGroupRepository;
import com.samsbeauty.warehouse.picking.repository.PickingJobRepository;
import com.samsbeauty.warehouse.picking.repository.PickingJobTimelineRepository;
import com.samsbeauty.warehouse.service.WarehouseItemService;

@Service
@Transactional
public class PickingItemServiceImpl implements PickingItemService {
	@Autowired private PickingJobRepository pickingJobRepository;
	@Autowired private InventoryRestHelper inventoryRestHelper;
	@Autowired private PickingItemRepository pickingItemRepository;
	@Autowired private PickingItemInfoRepository pickingItemInfoRepository;
	@Autowired private PickingJobGroupRepository pickingJobGroupRepository;
	@Autowired private PickingJobTimelineRepository pickingJobTimelineRepository;
	
	@Autowired private AdjustmentRequestService adjustmentRequestService;
	@Autowired private WarehouseItemService warehouseItemService;

	public Page<PickingItem> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return pickingItemRepository.findAllByActivated(true, new PageRequest(pageNumber-1, pageSize, sort));
	}
	public PickingItem getOne(Long pickingItemId) {
		return pickingItemRepository.getOne(pickingItemId);
	}
	public PickingItem save(PickingItem pickingItem) {
		return pickingItemRepository.save(pickingItem);
	}
	public List<PickingItem> saveItems(List<PickingItem> pickingItems) {
		// This is for manual input so the reason of missing is unknown.
		// If you want to use this function for specific reason such as missing, wrong location, picked, or etc then should use pick or skip function
		
		// You should manually outbound this amount of quantity.
		// In the future, we don't use manual picking anymore.
		List<PickingItem> returnList = new ArrayList<>();
		for(PickingItem pickingItem : pickingItems) {
			returnList.add(save(pickingItem));
		}
		return returnList;
	}
	public PickingItem pick(
			WarehouseLevel warehouseLevel, 
			PickingJob pickingJob, 
			WarehouseItemBox warehouseItemBox, 
			WarehouseItem warehouseItem, 
			WarehouseEmployee picker, 
			PickingJobTimeline timeline, 
			final Integer quantity,
			String barcode, 
			String reason) throws InvalidProductBarcodeException {
		if(warehouseItem == null) {
			// if item does not exist in the location then it should add the item to the location
			List<Inventory> inventoryList = inventoryRestHelper.getInventoryList(Arrays.asList(barcode));
			if(inventoryList == null || inventoryList.size() == 0) {
				throw new InvalidProductBarcodeException("Cannot find a product with the barcode you provided");
			}
			Inventory inventory = inventoryList.get(0);
			
			WarehouseItem item = WarehouseItem.builder()
					.setGeneratedBarcode(inventory.getGeneratedBarcode())
					.setModBy(picker)
					.setModDate(new Date())
					.setRegBy(picker)
					.setRegDate(new Date())
					.setProductId(Long.valueOf(inventory.getProductId()))
					.setQuantity(0)
					.setWarehouseLevel(warehouseLevel)
					.setWarehouseItemBox(warehouseItemBox)
					.createWarehouseItem();
			warehouseItemService.save(item);

			warehouseItem = item;
		}
		warehouseItemService.outbound(warehouseItem, quantity, picker);
		
		PickingItem pickingItem = PickingItem.builder(timeline, warehouseLevel)
				.setGeneratedBarcode(warehouseItem.getGeneratedBarcode())
				.setPickingJob(pickingJob)
				.setQuantity(quantity)
				.setRegDate(new Date())
				.setStatus(barcode.equals(warehouseItem.getGeneratedBarcode()) ? PickingItem.PickingItemStatus.PICKED_WITHOUT_SCAN : PickingItem.PickingItemStatus.PICKED)
				.setPicker(picker)
				.createPickingItem();
		
		pickingItemRepository.save(pickingItem);
		
		
		if(!StringUtils.isEmpty(reason)) {
			if(reason.equals(AdjustmentRequest.Reason.MISSING_BARCODE) || reason.equals(AdjustmentRequest.Reason.WRONG_BARCODE)) {
				AdjustmentRequest ar = AdjustmentRequest.builder(pickingItem)
						.setReason(reason)
						.setRegBy(picker)
						.setRegDate(new Date())
						.setStatus(AdjustmentRequest.Status.CREATED)
						.createAdjustmentRequest();
				adjustmentRequestService.save(ar);
			}
		}
		return  pickingItem;
	}
	public PickingItem saveForLater(PickingJob pickingJob, WarehouseEmployee picker, Inventory inventory, PickingJobTimeline currentTimeline, Integer quantity) {
		// Search if there already has a picking job for later picking.
		PickingJobGroup pickingJobGroup = pickingJobGroupRepository.getOneByPickingJobId(pickingJob.getPickingJobId());
		Long count = pickingJobRepository.countByPickingJobGroupAndSaved(pickingJobGroup.getPickingJobGroupId(), pickingJob.getPickingGroup().getPickingGroupId());
		
		PickingJob newJob = null;
		if(count > 0) {
			newJob = pickingJobRepository.getByPickingJobGroupAndSaved(pickingJobGroup.getPickingJobGroupId(), pickingJob.getPickingGroup().getPickingGroupId());
		} else {
			newJob = PickingJob.builder(pickingJobGroup, pickingJob.getPickingGroup())
					.setSaved(Boolean.TRUE)
					.setPickingStatus(PickingJob.PickingJobStatus.ASSIGNED)
					.createPickingJob();
			pickingJobRepository.save(newJob);
			
			// Create timeline to assign
			PickingJobTimeline timeline = PickingJobTimeline.builder(newJob)
					.setEventTime(new Date())
					.setPickingJobStatusFrom(PickingJobStatus.READY)
					.setPickingJobStatusTo(PickingJobStatus.ASSIGNED)
					.setWarehouseEmployee(picker)
					.createPickingJobTimeline();
			pickingJobTimelineRepository.save(timeline);
		}
		
		// Just create a picking item for `save for later` status
		PickingItem pi = PickingItem.builder(currentTimeline, null)
				.setGeneratedBarcode(inventory.getGeneratedBarcode())
				.setPicker(picker)
				.setPickingJob(pickingJob)
				.setQuantity(quantity)
				.setRegDate(new Date())
				.setStatus(PickingItem.PickingItemStatus.SAVE)
				.createPickingItem();
		pickingItemRepository.save(pi);
		
		PickingItemInfo pii = PickingItemInfo.builder(newJob)
				.setGeneratedBarcode(inventory.getGeneratedBarcode())
				.setImageUrl(inventory.getProductImage())
				.setOrderNo("")
				.setOrderQuantity(quantity)
				.setProductBarcode(inventory.getProductBarcode())
				.setProductGroup(inventory.getProductGroup())
				.setTitle(inventory.getTitle())
				.createPickingItemInfo();
		
		pickingItemInfoRepository.save(pii);
		return pi;
	}
	
	public PickingItem miss(WarehouseLevel warehouseLevel, PickingJob pickingJob, WarehouseEmployee picker, String generatedBarcode, PickingJobTimeline pickingJobTimeline, Integer quantity) throws LocationNotFoundException {		
		String status = PickingItem.PickingItemStatus.MISSED;
		if(warehouseLevel != null) {
			status = PickingItem.PickingItemStatus.WRONG_LOCATION;
		}
		
		PickingItem pickingItem = PickingItem.builder(pickingJobTimeline, warehouseLevel)
				.setGeneratedBarcode(generatedBarcode)
				.setPickingJob(pickingJob)
				.setRegDate(new Date())
				.setStatus(status)
				.setPicker(picker)
				.setQuantity(quantity)
				.createPickingItem();
		pickingItemRepository.save(pickingItem);
		
		AdjustmentRequest ar = adjustmentRequestService.getOneByPickingItemId(pickingItem.getPickingItemId());
		if(ar == null) {
			ar = AdjustmentRequest.builder(pickingItem)
					.setReason(AdjustmentRequest.Reason.NO_ITEM)
					.setRegBy(picker)
					.setRegDate(new Date())
					.setStatus(AdjustmentRequest.Status.CREATED)
					.createAdjustmentRequest();
			adjustmentRequestService.save(ar);
		} else {
			// If its reason is NO_ITEM already, then it should be NO_ITEM_SECOND 
			if(ar.getReason().equals(AdjustmentRequest.Reason.NO_ITEM)) {
				AdjustmentRequest upAr = AdjustmentRequest.builder(null).copyOf(ar)
						.setReason(AdjustmentRequest.Reason.NO_ITEM_SECOND)
						.createAdjustmentRequest();

				adjustmentRequestService.save(upAr);
			} // No else case can be happened
		}
		
		return pickingItem;
	}
	public void delete(Long pickingItemId) {
		pickingItemRepository.delete(pickingItemId);
	}
}