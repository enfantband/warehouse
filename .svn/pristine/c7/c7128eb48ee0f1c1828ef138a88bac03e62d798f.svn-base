package com.samsbeauty.warehouse.picking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.picking.model.PickingItem;
import com.samsbeauty.warehouse.picking.model.PickingItemInfo;
import com.samsbeauty.warehouse.picking.model.PickingJobTimeline;
import com.samsbeauty.warehouse.picking.repository.PickingItemInfoRepository;
import com.samsbeauty.warehouse.picking.repository.PickingJobTimelineRepository;
import com.samsbeauty.warehouse.util.PathUtil;

@Service
@Transactional
public class PickingItemInfoServiceImpl implements PickingItemInfoService {
	@Autowired private PickingItemInfoRepository pickingItemInfoRepository;
	@Autowired private PickingJobTimelineRepository pickingJobTimelineRepository;

	public Page<PickingItemInfo> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return pickingItemInfoRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));
	}
	public List<PickingItemInfo> getAllByPickingJobIdGroupByGeneratedBarcode(Long pickingJobId) {
		Map<String, Integer> itemPickedTable = new LinkedHashMap<>();
		
		// Count how many items are picked(all status) by using pickingItem data. 
		
		List<PickingJobTimeline> pickingJobTimelines = pickingJobTimelineRepository.findByPickingJobId(pickingJobId);
		for(PickingJobTimeline timeline : pickingJobTimelines) {			
			for(PickingItem pickingItem : timeline.getPickingItems()) {
				if(itemPickedTable.containsKey(pickingItem.getGeneratedBarcode())) {
					Integer picked = itemPickedTable.get(pickingItem.getGeneratedBarcode());
					itemPickedTable.put(pickingItem.getGeneratedBarcode(), picked + pickingItem.getQuantity());
				} else {
					itemPickedTable.put(pickingItem.getGeneratedBarcode(), pickingItem.getQuantity());
				}
			}
		}
		
		List<PickingItemInfo> pickingItemInfos = pickingItemInfoRepository.findByPickingJobId(pickingJobId);
		

		// Group all picking items by generated barcode
		Map<String, Integer> pickingItemInfoOrderQty = new HashMap<>();
		Map<String, PickingItemInfo> pickingItemInfoMap = new LinkedHashMap<>();
		for(PickingItemInfo pickingItemInfo : pickingItemInfos) {
			if(pickingItemInfoOrderQty.containsKey(pickingItemInfo.getGeneratedBarcode())) {
				Integer qty = pickingItemInfoOrderQty.get(pickingItemInfo.getGeneratedBarcode());
				qty = qty + pickingItemInfo.getOrderQuantity();
				pickingItemInfoOrderQty.put(pickingItemInfo.getGeneratedBarcode(), qty);
			} else {
				pickingItemInfoOrderQty.put(pickingItemInfo.getGeneratedBarcode(), pickingItemInfo.getOrderQuantity());
				pickingItemInfoMap.put(pickingItemInfo.getGeneratedBarcode(), pickingItemInfo);
			}
		}
		
		List<PickingItemInfo> calculatedList = new ArrayList<>();
		
		for(PickingItemInfo pickingItemInfo : pickingItemInfoMap.values()) {
			
			Integer pickedQty = 0;
			if(itemPickedTable.containsKey(pickingItemInfo.getGeneratedBarcode())) {
				pickedQty = itemPickedTable.get(pickingItemInfo.getGeneratedBarcode());
			}
			Integer orderQuantity = pickingItemInfoOrderQty.get(pickingItemInfo.getGeneratedBarcode());
			
			if(pickedQty >= orderQuantity) {
				pickedQty = pickedQty - orderQuantity;
			} else {
				PickingItemInfo pi = PickingItemInfo.builder(pickingItemInfo.getPickingJob())
						.setPickingItemInfoId(pickingItemInfo.getPickingItemInfoId())
						.setGeneratedBarcode(pickingItemInfo.getGeneratedBarcode())
						// Put image with url
						.setImageUrl(PathUtil.getProductImagePath(pickingItemInfo.getProductGroup(), pickingItemInfo.getImageUrl()))
						.setOrderNo("") // because it has been grouped by generated barcode, orderNo is not correct value so set orderNo to empty string.
						.setOrderQuantity(orderQuantity)
						.setPickedQuantity(pickedQty)
						.setMissedQuantity(0)
						.setProductBarcode(pickingItemInfo.getProductBarcode())
						.setProductGroup(pickingItemInfo.getProductGroup())
						.setTitle(pickingItemInfo.getTitle())
						.createPickingItemInfo();
				calculatedList.add(pi);
			}
		}
		return calculatedList;
	}
	public PickingItemInfo getOne(Long pickingItemInfoId) {
		return pickingItemInfoRepository.getOne(pickingItemInfoId);
	}
	public PickingItemInfo save(PickingItemInfo pickingItemInfo) {
		return pickingItemInfoRepository.save(pickingItemInfo);
	}
	public void delete(Long pickingItemInfoId) {
		pickingItemInfoRepository.delete(pickingItemInfoId);
	}
}