package com.samsbeauty.warehouse.picking.model.converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.AttributeConverter;

import com.samsbeauty.warehouse.picking.model.PickingItem.PickingItemStatus;

public class PickingItemStatusConverter implements AttributeConverter<String, Integer > {
	public static final Map<Integer, String> pickingItemStatusValue;
	public static final Map<String, Integer> pickingItemStatusKey;
	
	static {
		pickingItemStatusValue = new ConcurrentHashMap<Integer, String>();
		pickingItemStatusValue.put(PickingItemStatus.READY_CODE, PickingItemStatus.READY);
		pickingItemStatusValue.put(PickingItemStatus.PICKED_CODE, PickingItemStatus.PICKED);
		pickingItemStatusValue.put(PickingItemStatus.PICKED_WITHOUT_SCAN_CODE, PickingItemStatus.PICKED_WITHOUT_SCAN);
		pickingItemStatusValue.put(PickingItemStatus.MISSED_CODE, PickingItemStatus.MISSED);
		pickingItemStatusValue.put(PickingItemStatus.WRONG_LOCATION_CODE, PickingItemStatus.WRONG_LOCATION);
		pickingItemStatusValue.put(PickingItemStatus.SAVE_CODE, PickingItemStatus.SAVE);
		
		pickingItemStatusKey = new ConcurrentHashMap<String, Integer>();
		pickingItemStatusKey.put(PickingItemStatus.READY, PickingItemStatus.READY_CODE);
		pickingItemStatusKey.put(PickingItemStatus.PICKED, PickingItemStatus.PICKED_CODE);
		pickingItemStatusKey.put(PickingItemStatus.PICKED_WITHOUT_SCAN, PickingItemStatus.PICKED_WITHOUT_SCAN_CODE);
		pickingItemStatusKey.put(PickingItemStatus.MISSED, PickingItemStatus.MISSED_CODE);
		pickingItemStatusKey.put(PickingItemStatus.WRONG_LOCATION, PickingItemStatus.WRONG_LOCATION_CODE);
		pickingItemStatusKey.put(PickingItemStatus.SAVE, PickingItemStatus.SAVE_CODE);
	}
	public Integer convertToDatabaseColumn(String attribute) {
		return pickingItemStatusKey.get(attribute);
	}
	public String convertToEntityAttribute(Integer dbData) {
		
		return pickingItemStatusValue.get(dbData);
	}	
}
