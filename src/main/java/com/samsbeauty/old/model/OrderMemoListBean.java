package com.samsbeauty.old.model;

public class OrderMemoListBean {
	private String memoId;
	private String orderNo;
	private String memo;
	private String category;
	private String msgType;
	private String msgTypeName;
	private String msgTypeColor;
	private String status;
	private String oriOrderStatus;
	private String oriOrderStatusName;
	private String chgOrderStatus;
	private String chgOrderStatusName;
	private String oriSubtotalPrice;
	private String chgSubtotalPrice;
	private String oriTaxPrice;
	private String chgTaxPrice;
	private String oriChangePrice;
	private String chgChangePrice;
	private String chgPriceType;
	private String writerType;
	private String writerTypeName;
	private String riEiGid;
	private String riEiName;
	private String dispRiDate;
	private String riDate;
	private String miEiGid;
	private String miEiName;
	private String miDate;
	private String dispMiDate;
	private String errCode;
	private String errMsg;
	
	public String getMemoId() {
		return memoId;
	}
	public void setMemoId(String memoId) {
		this.memoId = memoId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgTypeName() {
		return msgTypeName;
	}
	public void setMsgTypeName(String msgTypeName) {
		this.msgTypeName = msgTypeName;
	}
	public String getMsgTypeColor() {
		return msgTypeColor;
	}
	public void setMsgTypeColor(String msgTypeColor) {
		this.msgTypeColor = msgTypeColor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOriOrderStatus() {
		return oriOrderStatus;
	}
	public void setOriOrderStatus(String oriOrderStatus) {
		this.oriOrderStatus = oriOrderStatus;
	}
	public String getOriOrderStatusName() {
		return oriOrderStatusName;
	}
	public void setOriOrderStatusName(String oriOrderStatusName) {
		this.oriOrderStatusName = oriOrderStatusName;
	}
	public String getChgOrderStatus() {
		return chgOrderStatus;
	}
	public void setChgOrderStatus(String chgOrderStatus) {
		this.chgOrderStatus = chgOrderStatus;
	}
	public String getChgOrderStatusName() {
		return chgOrderStatusName;
	}
	public void setChgOrderStatusName(String chgOrderStatusName) {
		this.chgOrderStatusName = chgOrderStatusName;
	}
	public String getOriSubtotalPrice() {
		return oriSubtotalPrice;
	}
	public void setOriSubtotalPrice(String oriSubtotalPrice) {
		this.oriSubtotalPrice = oriSubtotalPrice;
	}
	public String getChgSubtotalPrice() {
		return chgSubtotalPrice;
	}
	public void setChgSubtotalPrice(String chgSubtotalPrice) {
		this.chgSubtotalPrice = chgSubtotalPrice;
	}
	public String getOriTaxPrice() {
		return oriTaxPrice;
	}
	public void setOriTaxPrice(String oriTaxPrice) {
		this.oriTaxPrice = oriTaxPrice;
	}
	public String getChgTaxPrice() {
		return chgTaxPrice;
	}
	public void setChgTaxPrice(String chgTaxPrice) {
		this.chgTaxPrice = chgTaxPrice;
	}
	public String getOriChangePrice() {
		return oriChangePrice;
	}
	public void setOriChangePrice(String oriChangePrice) {
		this.oriChangePrice = oriChangePrice;
	}
	public String getChgChangePrice() {
		return chgChangePrice;
	}
	public void setChgChangePrice(String chgChangePrice) {
		this.chgChangePrice = chgChangePrice;
	}
	public String getChgPriceType() {
		return chgPriceType;
	}
	public void setChgPriceType(String chgPriceType) {
		this.chgPriceType = chgPriceType;
	}
	public String getWriterType() {
		return writerType;
	}
	public void setWriterType(String writerType) {
		this.writerType = writerType;
		
		if(writerType != null && writerType.equals("A")){
			setWriterTypeName("System");
		}
	}
	public String getWriterTypeName() {
		return writerTypeName;
	}
	public void setWriterTypeName(String writerTypeName) {
		this.writerTypeName = writerTypeName;
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
	public String getRiDate() {
		return riDate;
	}
	public void setRiDate(String riDate) {
		this.riDate = riDate;
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
	public String getMiDate() {
		return miDate;
	}
	public void setMiDate(String miDate) {
		this.miDate = miDate;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
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
