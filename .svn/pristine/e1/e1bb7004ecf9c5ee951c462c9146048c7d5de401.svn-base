package com.samsbeauty.warehouse.util;

public class WarehouseLocationParser {
	private String warehouseCode;
	private String aisleCode;
	private String groupCode;
	private String subgroupCode;
	private String levelCode;
	
	
	public WarehouseLocationParser(String warehouseLocation) {
		try {
			this.parse(warehouseLocation);
		} catch(Exception e) {
			warehouseCode = "";
			aisleCode = "";
			groupCode = "";
			subgroupCode = "";
			levelCode = "";
		}
	}
	
	private void parse(String warehouseLocation) throws Exception {
		warehouseLocation = warehouseLocation.replaceFirst("l_", "");
		warehouseLocation = warehouseLocation.replaceFirst("loc_", "");
		if(warehouseLocation.length() != 13) {
			throw new Exception("Invalid Location Code");
		}
		warehouseCode = warehouseLocation.substring(0,2);
		aisleCode = warehouseLocation.substring(2,5);
		groupCode = warehouseLocation.substring(5,8);
		subgroupCode = warehouseLocation.substring(8, 11);
		levelCode = warehouseLocation.substring(11,13);
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public String getAisleCode() {
		return aisleCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public String getSubgroupCode() {
		return subgroupCode;
	}

	public String getLevelCode() {
		return levelCode;
	}
}
