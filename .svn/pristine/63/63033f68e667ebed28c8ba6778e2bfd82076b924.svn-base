package com.samsbeauty.warehouse.model.converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.samsbeauty.warehouse.model.WarehouseLevel;

@Converter
public class WarehouseLevelStatusConverter implements AttributeConverter<String, String>{
	public static final Map<String, String> levelStatusValue;
	public static final Map<String, String> levelStatusKey;
	
	static {
		levelStatusValue = new ConcurrentHashMap<String, String>();
		levelStatusValue.put(WarehouseLevel.LevelStatus.IN_STOCK_CODE, WarehouseLevel.LevelStatus.IN_STOCK);
		levelStatusValue.put(WarehouseLevel.LevelStatus.EMPTY_CODE, WarehouseLevel.LevelStatus.EMPTY);
		levelStatusValue.put(WarehouseLevel.LevelStatus.RESERVED_CODE, WarehouseLevel.LevelStatus.RESERVED);
		
		levelStatusKey = new ConcurrentHashMap<String, String>();
		levelStatusKey.put(WarehouseLevel.LevelStatus.IN_STOCK, WarehouseLevel.LevelStatus.IN_STOCK_CODE);
		levelStatusKey.put(WarehouseLevel.LevelStatus.EMPTY, WarehouseLevel.LevelStatus.EMPTY_CODE);
		levelStatusKey.put(WarehouseLevel.LevelStatus.RESERVED, WarehouseLevel.LevelStatus.RESERVED_CODE);
	}
	
	public String convertToDatabaseColumn(String attribute) {
		return levelStatusKey.get(attribute);
	}

	public String convertToEntityAttribute(String dbData) {
		
		return levelStatusValue.get(dbData);
	}
}
