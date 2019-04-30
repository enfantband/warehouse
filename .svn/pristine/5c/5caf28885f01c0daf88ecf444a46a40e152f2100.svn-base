package com.samsbeauty.warehouse.picking.export.model;

import java.util.ArrayList;
import java.util.List;

public class GroupPickingItemExport {
	private List<String> locations;
	private String description;
	private String option;
	private String generatedBarcode;
	private Integer quantity;
	private String productType;
	private Integer stock;
	
	public List<String> getLocations() {
		return locations;
	}
	public String getDescription() {
		return description;
	}
	public String getOption() {
		return option;
	}
	public String getGeneratedBarcode() {
		return generatedBarcode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public String getProductType() {
		return productType;
	}
	public Integer getStock() {
		return stock;
	}
	
	public void addQuantity(Integer quantity) {
		this.quantity += quantity;
	}



	public GroupPickingItemExport(List<String> locations, List<String> locationCodes, String description, String option, String generatedBarcode,
			Integer quantity, String productType, Integer stock) {
		super();
		this.locations = locations;
		this.description = description;
		this.option = option;
		this.generatedBarcode = generatedBarcode;
		this.quantity = quantity;
		this.productType = productType;
		this.stock = stock;
	}


	public static GroupPickingItemExportBuilder builder() {
		return new GroupPickingItemExportBuilder();
	}

	public static class GroupPickingItemExportBuilder {
		private List<String> locations;
		private List<String> locationCodes;
		private String description;
		private String option;
		private String generatedBarcode;
		private Integer quantity;
		private String productType;
		private Integer stock;
		
		public GroupPickingItemExportBuilder() {
			locations = new ArrayList<>();
		}
		
		public GroupPickingItemExportBuilder setLocationCodes(List<String> locationCodes) {
			this.locationCodes = locationCodes;
			return this;
		}
		
		public GroupPickingItemExportBuilder setLocations(List<String> locations) { 
			this.locations = locations;
			return this;
		}
		
		public GroupPickingItemExportBuilder setDescription(String description) {
			this.description = description;
			return this;
		}
		
		public GroupPickingItemExportBuilder setOption(String option) {
			this.option = option;
			return this;
		}
		
		public GroupPickingItemExportBuilder setGeneratedBarcode(String generatedBarcode) {
			this.generatedBarcode = generatedBarcode;
			return this;
		}
		
		public GroupPickingItemExportBuilder setQuantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}
		
		public GroupPickingItemExportBuilder setProductType(String productType) {
			this.productType = productType;
			return this;
		}
		
		public GroupPickingItemExportBuilder setStock(Integer stock) {
			this.stock = stock;
			return this;
		}
		
		public GroupPickingItemExport createOrderItemExport() {
			return new GroupPickingItemExport(locations, locationCodes, description, option, generatedBarcode, quantity, productType, stock);
		}
	}
}
