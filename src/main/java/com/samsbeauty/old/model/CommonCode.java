package com.samsbeauty.old.model;

public class CommonCode {
	private String ccCode;
	private String ccParentCode;
	private String ccParentTitle;
	private String ccFirstCode;
	private String ccSecondCode;
	private String ccThirdCode;
	private String ccTitle;
	private String ccStringValue;
	private String ccTextValue;
	private String ccValueType;
	private String ccRegUserGid;
	private String ccRegDatetime;
	private String ccModUserGid;
	private String ccModDatetime;
	private String ccUseFlag;
	private String ccOrder;


	public String getCcCode() {
		return ccCode;
	}
	public void setCcCode(String ccCode) {
		this.ccCode = ccCode;
	}

	public String getCcParentCode() {
		return ccParentCode;
	}
	
	public String getCcParentTitle() {
		return ccParentTitle;
	}
	public void setCcParentTitle(String ccParentTitle) {
		this.ccParentTitle = ccParentTitle;
	}
	public void setCcParentCode(String ccParentCode) {
		this.ccParentCode = ccParentCode;
	}

	public String getCcFirstCode() {
		return ccFirstCode;
	}
	public void setCcFirstCode(String ccFirstCode) {
		this.ccFirstCode = ccFirstCode;
	}

	public String getCcSecondCode() {
		return ccSecondCode;
	}
	public void setCcSecondCode(String ccSecondCode) {
		this.ccSecondCode = ccSecondCode;
	}

	public String getCcThirdCode() {
		return ccThirdCode;
	}
	public void setCcThirdCode(String ccThirdCode) {
		this.ccThirdCode = ccThirdCode;
	}

	public String getCcTitle() {
		return ccTitle;
	}
	public void setCcTitle(String ccTitle) {
		this.ccTitle = ccTitle;
	}

	public String getCcStringValue() {
		
		if(ccStringValue == null){
			return ccTextValue;
		}
		
		return ccStringValue;
	}
	public void setCcStringValue(String ccStringValue) {
		this.ccStringValue = ccStringValue;
	}

	public String getCcTextValue() {
		return ccTextValue;
	}
	public void setCcTextValue(String ccTextValue) {
		this.ccTextValue = ccTextValue;
	}

	public String getCcValueType() {
		return ccValueType;
	}
	public void setCcValueType(String ccValueType) {
		this.ccValueType = ccValueType;
	}

	public String getCcRegUserGid() {
		return ccRegUserGid;
	}
	public void setCcRegUserGid(String ccRegUserGid) {
		this.ccRegUserGid = ccRegUserGid;
	}

	public String getCcRegDatetime() {
		return ccRegDatetime;
	}
	public void setCcRegDatetime(String ccRegDatetime) {
		this.ccRegDatetime = ccRegDatetime;
	}

	public String getCcModUserGid() {
		return ccModUserGid;
	}
	public void setCcModUserGid(String ccModUserGid) {
		this.ccModUserGid = ccModUserGid;
	}

	public String getCcModDatetime() {
		return ccModDatetime;
	}
	public void setCcModDatetime(String ccModDatetime) {
		this.ccModDatetime = ccModDatetime;
	}

	public String getCcUseFlag() {
		return ccUseFlag;
	}
	public void setCcUseFlag(String ccUseFlag) {
		this.ccUseFlag = ccUseFlag;
	}

	public String getCcOrder() {
		return ccOrder;
	}
	public void setCcOrder(String ccOrder) {
		this.ccOrder = ccOrder;
	}

	public String getCcValue(){
		if(this.ccValueType != null && this.ccValueType.equals("S")){
			return this.ccStringValue;
		}else{
			return this.ccTextValue;
		}
	}
}
