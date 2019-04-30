package com.samsbeauty.old.model;

public class WarehouseItemBean {
	private Long itemId;
	private Long productId;
	private String generatedBarcode;
	private String boxBarcode;
	private Inventory inventoryBean;
	private Integer quantity;
	private Integer itemBoxId;
	private String riDate;
	private String riEiName;
	private String riEiGid;
	private String miDate;
	private String miEiGid;
	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getGeneratedBarcode() {
		return generatedBarcode;
	}
	public void setGeneratedBarcode(String generatedBarcode) {
		this.generatedBarcode = generatedBarcode;
	}
	public String getBoxBarcode() {
		return boxBarcode;
	}
	public void setBoxBarcode(String boxBarcode) {
		this.boxBarcode = boxBarcode;
	}
	public void setInventoryBean(Inventory inventoryBean) {
		this.inventoryBean = inventoryBean;
	}
	
	public Inventory getInventoryBean() {
		return inventoryBean;
	}
	public String getRiDate() {
		return riDate;
	}
	public void setRiDate(String riDate) {
		this.riDate = riDate;
	}
	public String getRiEiName() {
		return riEiName;
	}
	public void setRiEiName(String riEiName) {
		this.riEiName = riEiName;
	}
	public String getRiEiGid() {
		return riEiGid;
	}
	public void setRiEiGid(String riEiGid) {
		this.riEiGid = riEiGid;
	}
	public String getMiDate() {
		return miDate;
	}
	public void setMiDate(String miDate) {
		this.miDate = miDate;
	}
	public String getMiEiGid() {
		return miEiGid;
	}
	public void setMiEiGid(String miEiGid) {
		this.miEiGid = miEiGid;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getItemBoxId() {
		return itemBoxId;
	}
	public void setItemBoxId(Integer itemBoxId) {
		this.itemBoxId = itemBoxId;
	}
	
	public boolean isInBox() {
		return boxBarcode != null && !boxBarcode.equals("");
	}

}