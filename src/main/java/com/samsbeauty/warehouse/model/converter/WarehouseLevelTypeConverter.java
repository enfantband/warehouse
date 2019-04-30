package com.samsbeauty.warehouse.model.converter;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.samsbeauty.warehouse.model.WarehouseLevel;

@Converter
public class WarehouseLevelTypeConverter implements AttributeConverter<String, String>{	
	public static final Map<String, String> levelTypeValue;
	public static final Map<String, String> levelTypeKey;
	
	static {
		levelTypeValue = new HashMap<String, String>();
		levelTypeValue.put(WarehouseLevel.LevelType.BULK_CODE, WarehouseLevel.LevelType.BULK);
		levelTypeValue.put(WarehouseLevel.LevelType.PICKING_CODE, WarehouseLevel.LevelType.PICKING);
		
		levelTypeKey = new HashMap<String, String>();
		levelTypeKey.put(WarehouseLevel.LevelType.BULK, WarehouseLevel.LevelType.BULK_CODE);
		levelTypeKey.put(WarehouseLevel.LevelType.PICKING, WarehouseLevel.LevelType.PICKING_CODE);
	}
	
	public String convertToDatabaseColumn(String attribute) {
		return levelTypeKey.get(attribute);
	}

	public String convertToEntityAttribute(String dbData) {
		
		return levelTypeValue.get(dbData);
	}
	
}
