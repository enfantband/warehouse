package com.samsbeauty.old.model;

import java.util.ArrayList;
import java.util.List;

public class PendingOrderItemBean {
	private Long pendingItemId;
	private String orderNo;
	private String generatedBarcode;
	private Integer pendingOrderId;
	private Integer reqQuantity;
	private String useFlag;
	private String pendingItemStatus;
	private String pendingItemStatusName;
	private String riDate;
	private String riEiName;
	private String riEiGid;
	private String miDate;
	private String miEiName;
	private String miEiGid;

	private Integer numOfWaiting;
	private OrderItemBean orderItemBean;
	private Inventory inventoryBean;
	
	private List<PendingQuantity> itemFrom;
	private List<PendingQuantity> itemTo;
	
	public Long getPendingItemId() {
		return pendingItemId;
	}
	public void setPendingItemId(Long pendingItemId) {
		this.pendingItemId = pendingItemId;
	}
	public String getGeneratedBarcode() {
		return generatedBarcode;
	}
	public void setGeneratedBarcode(String generatedBarcode) {
		this.generatedBarcode = generatedBarcode;
	}
	public Integer getPendingOrderId() {
		return pendingOrderId;
	}
	public void setPendingOrderId(Integer pendingOrderId) {
		this.pendingOrderId = pendingOrderId;
	}
	public Integer getReqQuantity() {
		return reqQuantity;
	}
	public void setReqQuantity(Integer reqQuantity) {
		this.reqQuantity = reqQuantity;
	}
	public String getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
	public OrderItemBean getOrderItemBean() {
		return orderItemBean;
	}
	public void setOrderItemBean(OrderItemBean orderItemBean) {
		this.orderItemBean = orderItemBean;
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
	public String getPendingItemStatus() {
		return pendingItemStatus;
	}
	public void setPendingItemStatus(String pendingItemStatus) {
		this.pendingItemStatus = pendingItemStatus;
	}
	public String getPendingItemStatusName() {
		return pendingItemStatusName;
	}
	public void setPendingItemStatusName(String pendingItemStatusName) {
		this.pendingItemStatusName = pendingItemStatusName;
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
	public Inventory getInventoryBean() {
		return inventoryBean;
	}
	public void setInventoryBean(Inventory inventoryBean) {
		this.inventoryBean = inventoryBean;
	}
	public List<PendingQuantity> getItemFrom() {
		if(itemFrom == null)
			itemFrom = new ArrayList<PendingQuantity>();
		return itemFrom;
	}
	public void setItemFrom(List<PendingQuantity> itemFrom) {
		this.itemFrom = itemFrom;
	}
	public List<PendingQuantity> getItemTo() {
		if(itemTo == null) 
			itemTo = new ArrayList<PendingQuantity>();
		return itemTo;
	}
	public void setItemTo(List<PendingQuantity> itemTo) {
		this.itemTo = itemTo;
	}
	public Integer getNumOfWaiting() {
		return numOfWaiting;
	}
	public void setNumOfWaiting(Integer numOfWaiting) {
		this.numOfWaiting = numOfWaiting;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}