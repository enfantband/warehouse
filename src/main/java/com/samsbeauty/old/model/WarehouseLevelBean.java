package com.samsbeauty.old.model;

import java.util.List;

public class WarehouseLevelBean {
	private Integer levelId;
	private String levelCode;
	private String warehouseTag;
	private String aisleTag;
	private String groupTag;
	private String subgroupTag;
	private String levelStatus;
	private String levelType;
	
	private String riDate;
	private String riEiGid;
	private String riEiName;
	private String miDate;
	private String miEiGid;
	private String miEiName;
	
	private String dispRiDate;
	private String dispMiDate;
	private String bgColor;
	
	private Integer numberOfDiffItems;
	private Integer numberOfItems;
	private Integer itemCount;
	
	private Integer subgroupId;
	private WarehouseTagBean tag;
	private Integer tagId;
	private String barcode;
	private String barcodeTag;
	
	private List<WarehouseItemBoxBean> warehouseItemBoxes;

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getLevelStatus() {
		return levelStatus;
	}

	public void setLevelStatus(String levelStatus) {
		this.levelStatus = levelStatus;
	}

	public String getLevelType() {
		return levelType;
	}

	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}

	public String getRiDate() {
		return riDate;
	}

	public void setRiDate(String riDate) {
		this.riDate = riDate;
	}

	public String getRiEiGid() {
		return riEiGid;
	}

	public void setRiEiGid(String riEiGid) {
		this.riEiGid = riEiGid;
	}

	public String getRiEiName() {
		return riEiName;
	}

	public void setRiEiName(String riEiName) {
		this.riEiName = riEiName;
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

	public String getMiEiName() {
		return miEiName;
	}

	public void setMiEiName(String miEiName) {
		this.miEiName = miEiName;
	}

	public String getDispRiDate() {
		return dispRiDate;
	}

	public void setDispRiDate(String dispRiDate) {
		this.dispRiDate = dispRiDate;
	}

	public String getDispMiDate() {
		return dispMiDate;
	}

	public void setDispMiDate(String dispMiDate) {
		this.dispMiDate = dispMiDate;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public Integer getSubgroupId() {
		return subgroupId;
	}

	public void setSubgroupId(Integer subgroupId) {
		this.subgroupId = subgroupId;
	}

	public WarehouseTagBean getTag() {
		return tag;
	}

	public void setTag(WarehouseTagBean tag) {
		this.tag = tag;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getBarcode() {
		if(barcode.startsWith("L_")) {
			barcode = barcode.replace("L_", "");
		}
		return barcode;
	}

	public void setBarcode(String barcode) {
		if(barcode.startsWith("L_")) {
			barcode = barcode.replace("L_", "");
		}
		this.barcode = barcode;
	}

	public String getWarehouseTag() {
		return warehouseTag;
	}

	public void setWarehouseTag(String warehouseTag) {
		this.warehouseTag = warehouseTag;
	}

	public String getAisleTag() {
		return aisleTag;
	}

	public void setAisleTag(String aisleTag) {
		this.aisleTag = aisleTag;
	}

	public String getGroupTag() {
		return groupTag;
	}

	public void setGroupTag(String groupTag) {
		this.groupTag = groupTag;
	}

	public String getSubgroupTag() {
		return subgroupTag;
	}

	public void setSubgroupTag(String subgroupTag) {
		this.subgroupTag = subgroupTag;
	}

	public String getBarcodeTag() {
		return barcodeTag;
	}

	public void setBarcodeTag(String barcodeTag) {
		this.barcodeTag = barcodeTag;
	}

	public Integer getNumberOfDiffItems() {
		return numberOfDiffItems;
	}

	public void setNumberOfDiffItems(Integer numberOfDiffItems) {
		this.numberOfDiffItems = numberOfDiffItems;
	}

	public Integer getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(Integer numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public List<WarehouseItemBoxBean> getWarehouseItemBoxes() {
		return warehouseItemBoxes;
	}

	public void setWarehouseItemBoxes(List<WarehouseItemBoxBean> warehouseItemBoxes) {
		this.warehouseItemBoxes = warehouseItemBoxes;
	}
}
