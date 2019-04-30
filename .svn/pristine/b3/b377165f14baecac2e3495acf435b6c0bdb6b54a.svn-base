package com.samsbeauty.old.model;

import java.math.BigDecimal;
import java.util.List;

public class OrderItem {
	public static class ProductType {
		public static final String HAIR = "028001000";
		public static final String GM = "028002000";
		public static final String FREEGIFT = "028003000";
		public static final String DESIGN = "028004000";
		public static final String COLOR = "028005000";
		public static final String AC = "028006000";
	}
	public static class InventoryStatus {
		public static final String AVAILABLE = "002001000";
		public static final String DISCONTINUE = "002002000";
		public static final String INSTOCK = "002003000";
		public static final String FINAL_SALE = "002004000";
		public static final String ORDER = "002005000";
	}

	
	private String orderNo;
	private String generatedBarcode;
	private String productBarcode;
	private String company;
	private Integer quantity;
	private String dealGroup;
	private String productGroup;
	private String vendorItemCode;
	private String title;
	private String firstOption;
	private String imageUrl;
	private String productType;
	private Integer stock;
	private BigDecimal finalPrice;
	private Integer usedPoint;
	private String inventoryStatus;
	private String host;
	
	private List<String> locationNames;
	private List<String> locationCodes;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getGeneratedBarcode() {
		return generatedBarcode;
	}
	public void setGeneratedBarcode(String generatedBarcode) {
		this.generatedBarcode = generatedBarcode;
	}
	public String getProductBarcode() {
		return productBarcode;
	}
	public void setProductBarcode(String productBarcode) {
		this.productBarcode = productBarcode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getDealGroup() {
		return dealGroup;
	}
	public void setDealGroup(String dealGroup) {
		this.dealGroup = dealGroup;
	}
	public String getProductGroup() {
		return productGroup;
	}
	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}
	public String getVendorItemCode() {
		return this.vendorItemCode;
	}
	public void setVendorItemCode(String vendorItemCode) {
		this.vendorItemCode = vendorItemCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstOption() {
		return firstOption;
	}
	public void setFirstOption(String firstOption) {
		this.firstOption = firstOption;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}
	public Integer getUsedPoint() {
		return usedPoint;
	}
	public void setUsedPoint(Integer usedPoint) {
		this.usedPoint = usedPoint;
	}
	public String getInventoryStatus() {
		return inventoryStatus;
	}
	public void setInventoryStatus(String inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}
	public void setLocationNames(List<String> locationNames) {
		this.locationNames = locationNames;
	}
	public void setLocationCodes(List<String> locationCodes) {
		this.locationCodes = locationCodes;
	}
	public List<String> getLocationCodes() {
		return this.locationCodes;
	}
	
	public List<String> getLocationNames() {
		return this.locationNames;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getHost() {
		return host;
	}
	public OrderItem(String orderNo, String generatedBarcode, String productBarcode, String company, Integer quantity, String dealGroup,
			String vendorItemCode,
			String productGroup, String title, String firstOption, String imageUrl, String productType, Integer stock, BigDecimal finalPrice, Integer usedPoint,
			String inventoryStatus, String host, List<String> locationNames) {
		super();
		this.orderNo = orderNo;
		this.generatedBarcode = generatedBarcode;
		this.productBarcode = productBarcode;
		this.company = company;
		this.quantity = quantity;
		this.dealGroup = dealGroup;
		this.vendorItemCode = vendorItemCode;
		this.productGroup = productGroup;
		this.title = title;
		this.firstOption = firstOption;
		this.imageUrl = imageUrl;
		this.productType = productType;
		this.stock = stock;
		this.finalPrice = finalPrice;
		this.usedPoint = usedPoint;
		this.inventoryStatus = inventoryStatus;
		this.host = host;
		this.locationNames = locationNames;
	}

	public static OrderItemBuilder builder() {
		return new OrderItemBuilder();
	}

	public static class OrderItemBuilder {
		private String orderNo;
		private String generatedBarcode;
		private String productBarcode;
		private String company;
		private Integer quantity;
		private String dealGroup;
		private String productGroup;
		private String vendorItemCode;
		private String title;
		private String firstOption;
		private String imageUrl;
		private String productType;
		private Integer stock;
		private BigDecimal finalPrice;
		private Integer usedPoint;
		private String inventoryStatus;
		private String host;
		private List<String> locationNames;
		
		public OrderItemBuilder setOrderNo(String orderNo) {
			this.orderNo = orderNo;
			return this;
		}
		public OrderItemBuilder setGeneratedBarcode(String generatedBarcode) {
			this.generatedBarcode = generatedBarcode;
			return this;
		}
		public OrderItemBuilder setProductBarcode(String productBarcode) {
			this.productBarcode = productBarcode;
			return this;
		}
		public OrderItemBuilder setCompany(String company) {
			this.company = company;
			return this;
		}
		public OrderItemBuilder setQuantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}
		public OrderItemBuilder setDealGroup(String dealGroup) {
			this.dealGroup = dealGroup;
			return this;
		}
		public OrderItemBuilder setProductGroup(String productGroup) {
			this.productGroup = productGroup;
			return this;
		}
		public OrderItemBuilder setTitle(String title) {
			this.title = title;
			return this;
		}
		public OrderItemBuilder setFirstOption(String firstOption) {
			this.firstOption = firstOption;
			return this;
		}
		public OrderItemBuilder setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
			return this;
		}
		public OrderItemBuilder setProductType(String productType) {
			this.productType = productType;
			return this;
		}
		public OrderItemBuilder setStock(Integer stock) {
			this.stock = stock;
			return this;
		}
		public OrderItemBuilder setFinalPrice(BigDecimal finalPrice) {
			this.finalPrice = finalPrice;
			return this;
		}
		public OrderItemBuilder setUsedPoint(Integer usedPoint) {
			this.usedPoint = usedPoint;
			return this;
		}
		public OrderItemBuilder setVendorItemCode(String vendorItemCode) {
			this.vendorItemCode = vendorItemCode;
			return this;
		}
		public OrderItemBuilder setHost(String host) {
			this.host = host;
			return this;
		}
		public OrderItemBuilder setInventoryStatus(String inventoryStatus) {
			this.inventoryStatus = inventoryStatus;
			return this;
		}
		public OrderItemBuilder setLocationNames(List<String> locationNames) {
			this.locationNames = locationNames;
			return this;
		}
		
		public OrderItemBuilder copy(OrderItem orderItem) {
			this.orderNo = orderItem.getOrderNo();
			this.generatedBarcode = orderItem.getGeneratedBarcode();
			this.productBarcode = orderItem.getProductBarcode();
			this.company = orderItem.getCompany();
			this.quantity = orderItem.getQuantity();
			this.dealGroup = orderItem.getDealGroup();
			this.vendorItemCode = orderItem.getVendorItemCode();
			this.productGroup = orderItem.getProductGroup();
			this.title = orderItem.getTitle();
			this.firstOption = orderItem.getFirstOption();
			this.imageUrl = orderItem.getImageUrl();
			this.productType = orderItem.getProductType();
			this.stock = orderItem.getStock();
			this.finalPrice = orderItem.getFinalPrice();
			this.usedPoint = orderItem.getUsedPoint();
			this.inventoryStatus = orderItem.getInventoryStatus();
			this.locationNames = orderItem.getLocationNames();
			this.host = orderItem.getHost();
			return this;
		}
		
		public OrderItem createOrderItem() {
			return new OrderItem(
					orderNo,
					generatedBarcode, productBarcode, company, quantity, dealGroup,
					vendorItemCode,
					productGroup, title, firstOption, imageUrl, productType, stock,
					finalPrice, usedPoint,
					inventoryStatus,
					host,
					locationNames
					);
		}
		
	}
}
