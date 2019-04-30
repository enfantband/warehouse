package com.samsbeauty.warehouse.adjustment.model.converter;

import static com.samsbeauty.warehouse.adjustment.model.AdjustmentRequest.Reason.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.AttributeConverter;

public class AdjustmentReasonConverter implements AttributeConverter<String, Integer> {
	public static final Map<Integer, String> adjustmentReasonValue;
	public static final Map<String, Integer> adjustmentReasonKey;
	
	static {
		adjustmentReasonValue = new ConcurrentHashMap<>();
		adjustmentReasonValue.put(WRONG_BARCODE_CODE, WRONG_BARCODE);
		adjustmentReasonValue.put(MISSING_BARCODE_CODE, MISSING_BARCODE);
		adjustmentReasonValue.put(NO_ITEM_CODE, NO_ITEM);
		adjustmentReasonValue.put(NO_ITEM_SECOND_CODE, NO_ITEM_SECOND);
		
		adjustmentReasonKey = new ConcurrentHashMap<>();
		adjustmentReasonKey.put(WRONG_BARCODE, WRONG_BARCODE_CODE);
		adjustmentReasonKey.put(MISSING_BARCODE, MISSING_BARCODE_CODE);
		adjustmentReasonKey.put(NO_ITEM, NO_ITEM_CODE);
		adjustmentReasonKey.put(NO_ITEM_SECOND, NO_ITEM_SECOND_CODE);
	}
	
	public Integer convertToDatabaseColumn(String attribute) {
		return adjustmentReasonKey.get(attribute);
	}
	public String convertToEntityAttribute(Integer dbData) {
		return adjustmentReasonValue.get(dbData);
	}
}
