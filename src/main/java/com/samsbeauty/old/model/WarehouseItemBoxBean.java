package com.samsbeauty.old.model;

import java.util.List;

public class WarehouseItemBoxBean {
	private Integer itemBoxId;
	private String boxPrefix;
	private String boxCode;
	private String riDate;
	private String dispRiDate;
	private String riEiName;
	private String riEiGid;
	private String miDate;
	private String dispMiDate;
	private String miEiGid;
	private String miEiName;
	
	private List<WarehouseItemBean> items;
	
	public Integer getItemBoxId() {
		return itemBoxId;
	}
	public void setItemBoxId(Integer itemBoxId) {
		this.itemBoxId = itemBoxId;
	}
	public String getBoxPrefix() {
		return boxPrefix;
	}
	public void setBoxPrefix(String boxPrefix) {
		this.boxPrefix = boxPrefix;
	}
	public String getBoxCode() {
		return boxCode;
	}
	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
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
	public String getRiEiName() {
		return riEiName;
	}
	public void setRiEiName(String riEiName) {
		this.riEiName = riEiName;
	}
	public String getMiEiName() {
		return miEiName;
	}
	public void setMiEiName(String miEiName) {
		this.miEiName = miEiName;
	}
	public List<WarehouseItemBean> getItems() {
		return items;
	}
	public void setItems(List<WarehouseItemBean> items) {
		this.items = items;
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
}