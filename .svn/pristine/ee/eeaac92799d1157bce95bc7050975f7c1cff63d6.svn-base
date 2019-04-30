package com.samsbeauty.warehouse.param;

import java.io.Serializable;

public class WarehouseItemParam implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1621031754641053052L;
	
	private Long levelId;
	private Integer itemBoxId;
	private String locationBarcode;
	private String productBarcode;
	private String generatedBarcode;
	private String searchKey;
	
	public WarehouseItemParam(Long levelId, Integer itemBoxId, String locationBarcode, String productBarcode, String generatedBarcode, String searchKey) {
		this.levelId = levelId;
		this.itemBoxId = itemBoxId;
		this.locationBarcode = locationBarcode;
		this.productBarcode = productBarcode;
		this.generatedBarcode = generatedBarcode;
		this.searchKey = searchKey;
	}
	
	public Long getLevelId() {
		return levelId;
	}
	
	public Integer getItemBoxId() {
		return itemBoxId;
	}
	
	public String getLocationBarocde() {
		return locationBarcode;
	}
	
	public String getProductBarcode() {
		return productBarcode;
	}
	
	public String getGeneratedBarcode() {
		return generatedBarcode;
	}
	
	public String getSearchKey() {
		return searchKey;
	}
	
	public static WarehouseItemParamBuilder builder() {
		return new WarehouseItemParamBuilder();
	}
	
	public static class WarehouseItemParamBuilder {

		private Long levelId;
		private Integer itemBoxId;
		private String locationBarcode;
		private String productBarcode;
		private String generatedBarcode;
		private String searchKey;
		
		public WarehouseItemParamBuilder() {
			
		}
		
		public WarehouseItemParamBuilder levelId(Long levelId) {
			this.levelId = levelId;
			return this;
		}
		
		public WarehouseItemParamBuilder itemBoxId(Integer itemBoxId) {
			this.itemBoxId = itemBoxId;
			return this;
		}
		
		public WarehouseItemParamBuilder locationBarcode(String locationBarcode) {
			this.locationBarcode = locationBarcode;
			return this;
		}
		
		public WarehouseItemParamBuilder productBarcode(String productBarcode) {
			this.productBarcode = productBarcode;
			return this;
		}
		
		public WarehouseItemParamBuilder generatedBarcode(String generatedBarcode) {
			this.generatedBarcode = generatedBarcode;
			return this;
		}
		
		public WarehouseItemParamBuilder searchKey(String searchKey) {
			this.searchKey = searchKey;
			return this;
		}
		
		public WarehouseItemParam createWarehouseItemParam() {
			return new WarehouseItemParam(levelId, itemBoxId, locationBarcode, productBarcode, generatedBarcode, searchKey);
		}
		
	}
}
