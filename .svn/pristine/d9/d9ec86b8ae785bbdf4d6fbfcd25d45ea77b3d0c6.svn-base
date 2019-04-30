package com.samsbeauty.warehouse.adjustment.model.converter;

import static com.samsbeauty.warehouse.adjustment.model.AdjustmentRequest.Status.COMPLETED;
import static com.samsbeauty.warehouse.adjustment.model.AdjustmentRequest.Status.COMPLETED_CODE;
import static com.samsbeauty.warehouse.adjustment.model.AdjustmentRequest.Status.CREATED;
import static com.samsbeauty.warehouse.adjustment.model.AdjustmentRequest.Status.CREATED_CODE;
import static com.samsbeauty.warehouse.adjustment.model.AdjustmentRequest.Status.PROCESSED;
import static com.samsbeauty.warehouse.adjustment.model.AdjustmentRequest.Status.PROCESSED_CODE;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.AttributeConverter;

public class AdjustmentStatusConverter implements AttributeConverter<String, Integer> {
	public static final Map<Integer, String> statusValue;
	public static final Map<String, Integer> statusKey;
	
	static {
		statusValue = new ConcurrentHashMap<>();
		statusValue.put(COMPLETED_CODE, COMPLETED);
		statusValue.put(CREATED_CODE, CREATED);
		statusValue.put(PROCESSED_CODE, PROCESSED);
		
		statusKey = new ConcurrentHashMap<>();
		statusKey.put(COMPLETED, COMPLETED_CODE);
		statusKey.put(CREATED, CREATED_CODE);
		statusKey.put(PROCESSED, PROCESSED_CODE);
	}
	
	public Integer convertToDatabaseColumn(String attribute) {
		return statusKey.get(attribute);
	}
	
	public String convertToEntityAttribute(Integer dbData) {
		return statusValue.get(dbData);
	}
}
