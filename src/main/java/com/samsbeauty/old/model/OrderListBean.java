package com.samsbeauty.old.model;

public class OrderListBean {

	private String listType = null;
	private String orderListNo = null;
	private String orderNo = null;	
	private String minOrderNo = null;	
	private String maxOrderNo = null;	
	private String host = null;	
	private String hostTitle = null;
	private String parentId = null;
	private String memberGid = null;	
	private String memberEmail = null;	
	private String memberCashPoint = null;	
	private String membershipId = null;
	private String email = null;
	private String phone = null;	
	private String billingName = null;
	private String billingFirstName = null;	
	private String billingMiddleName = null;
	private String billingLastName = null;
	private String billingCountry = null;	
	private String billingCountryName = null;
	private String billingAddress = null;
	private String billingAddress2 = null;
	private String billingCity = null;
	private String billingState = null;
	private String billingZipcode = null;	
	private String shippingName = null;
	private String shippingFirstName = null;	
	private String shippingMiddleName = null;
	private String shippingLastName = null;
	private String shippingCountry = null;
	private String shippingCountryName = null;
	private String shippingAddress = null;
	private String shippingAddress2 = null;
	private String shippingCity = null;
	private String shippingState = null;
	private String shippingZipcode = null;
	private String shippingMethod = null;
	private String shippingMethodCode = null;	
	private String shippingCarrier = null;	
	private String shippginUpsSignature = null;	
	private String shippingExpressFlag = "";
	private String riDate = null;	
	private String riDispDate = null;
	private String viDate = null;
	private String subTotalPrice = null;
	private String taxPrice = null;
	private String shippingPrice = null;	
	private String discountShippingPrice = null;
	private String couponName = null;
	private String couponPrice = null;	
	private String creditName = null;	
	private String creditPrice = null;
	private String usedPoint = null;
	private String trackingNumber = null;
	private String ipAddr = null;
	private String promoCode = null;
	private String orderStatus = null;	
	private String orderStatusName = null;
	private String memo = null;	
	private String memoColor = null;
	private String payMethod = null;
	private String employeeGid = null;	
	private String employeeName = null;
	private String actualShippingPrice = null;
	private String weight = null;	
	private String calWeight = null;
	private String printStatus = null;	
	private String printColor = null;	
	private String seeMemo = null;	
	private String finalCheck = "F";
	private String paymentFlag = null;	
	private String creditCardLast4Digit = null;	
	private String orderedBy = null;	
	private String verifiedAddress;	
	private String containingGM = null;

	private String errCode = null;
	private String errMsg = null;

	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getOrderListNo() {
		return orderListNo;
	}
	public void setOrderListNo(String orderListNo) {
		this.orderListNo = orderListNo;
	}

	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMinOrderNo() {
		return minOrderNo;
	}
	public void setMinOrderNo(String minOrderNo) {
		this.minOrderNo = minOrderNo;
	}
	public String getMaxOrderNo() {
		return maxOrderNo;
	}
	public void setMaxOrderNo(String maxOrderNo) {
		this.maxOrderNo = maxOrderNo;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getHostTitle() {
		return hostTitle;
	}
	public void setHostTitle(String hostTitle) {
		this.hostTitle = hostTitle;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMemberGid() {
		return memberGid;
	}
	public void setMemberGid(String memberGid) {
		this.memberGid = memberGid;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBillingFirstName() {
		return billingFirstName;
	}
	public void setBillingFirstName(String billingFirstName) {
		this.billingFirstName = billingFirstName;
	}

	public String getBillingLastName() {
		return billingLastName;
	}
	public void setBillingLastName(String billingLastName) {
		this.billingLastName = billingLastName;
	}

	public String getBillingCountry() {
		return billingCountry;
	}
	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}

	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getBillingCity() {
		return billingCity;
	}
	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingState() {
		return billingState;
	}
	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	public String getBillingZipcode() {
		return billingZipcode;
	}
	public void setBillingZipcode(String billingZipcode) {
		this.billingZipcode = billingZipcode;
	}

	public String getShippingFirstName() {
		return shippingFirstName;
	}
	public void setShippingFirstName(String shippingFirstName) {
		this.shippingFirstName = shippingFirstName;
	}

	public String getShippingLastName() {
		return shippingLastName;
	}
	public void setShippingLastName(String shippingLastName) {
		this.shippingLastName = shippingLastName;
	}

	public String getShippingCountry() {
		return shippingCountry;
	}
	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingCity() {
		return shippingCity;
	}
	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingState() {
		return shippingState;
	}
	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}

	public String getShippingZipcode() {
		return shippingZipcode;
	}
	public void setShippingZipcode(String shippingZipcode) {
		this.shippingZipcode = shippingZipcode;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public String getShippingMethodCode() {
		return shippingMethodCode;
	}
	public void setShippingMethodCode(String shippingMethodCode) {
		this.shippingMethodCode = shippingMethodCode;
	}

	public String getShippingCarrier() {
		return shippingCarrier;
	}
	public void setShippingCarrier(String shippingCarrier) {
		this.shippingCarrier = shippingCarrier;
	}
	public String getShippginUpsSignature() {
		return shippginUpsSignature;
	}
	public void setShippginUpsSignature(String shippginUpsSignature) {
		this.shippginUpsSignature = shippginUpsSignature;
	}
	public String getShippingExpressFlag() {
		
		if(shippingMethod != null){
			if(shippingMethod.toUpperCase().indexOf("UPS") != -1){
				setShippingExpressFlag("UPS");
			}else if(shippingMethod.toUpperCase().indexOf("EXPRESS") != -1){
				setShippingExpressFlag("USPS");
			}
		}
		
		return shippingExpressFlag;
	}
	public void setShippingExpressFlag(String shippingExpressFlag) {
		this.shippingExpressFlag = shippingExpressFlag;
	}
	public String getRiDate() {
		return riDate;
	}
	public void setRiDate(String riDate) {
		this.riDate = riDate;
	}

	public String getRiDispDate() {
		return riDispDate;
	}
	public void setRiDispDate(String riDispDate) {
		this.riDispDate = riDispDate;
	}
	public String getViDate() {
		return viDate;
	}
	public void setViDate(String viDate) {
		this.viDate = viDate;
	}

	public String getSubTotalPrice() {
		return subTotalPrice;
	}
	public void setSubTotalPrice(String subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
	}

	public String getTaxPrice() {
		return taxPrice;
	}
	public void setTaxPrice(String taxPrice) {
		this.taxPrice = taxPrice;
	}

	public String getShippingPrice() {
		return shippingPrice;
	}
	public void setShippingPrice(String shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public String getDiscountShippingPrice() {
		return discountShippingPrice;
	}
	public void setDiscountShippingPrice(String discountShippingPrice) {
		this.discountShippingPrice = discountShippingPrice;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponPrice() {
		return couponPrice;
	}
	public void setCouponPrice(String couponPrice) {
		this.couponPrice = couponPrice;
	}

	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	public String getCreditPrice() {
		return creditPrice;
	}
	public void setCreditPrice(String creditPrice) {
		this.creditPrice = creditPrice;
	}
	public String getUsedPoint() {
		return usedPoint;
	}
	public void setUsedPoint(String usedPoint) {
		this.usedPoint = usedPoint;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}
	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public void setMemoColor(String memoColor) {
		this.memoColor = memoColor;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getEmployeeGid() {
		return employeeGid;
	}
	public void setEmployeeGid(String employeeGid) {
		this.employeeGid = employeeGid;
	}

	public String getActualShippingPrice() {
		return actualShippingPrice;
	}
	public void setActualShippingPrice(String actualShippingPrice) {
		this.actualShippingPrice = actualShippingPrice;
	}

	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getCalWeight() {
		return calWeight;
	}
	public void setCalWeight(String calWeight) {
		this.calWeight = calWeight;
	}
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}

	public String getPrintColor() {
		return printColor;
	}
	public void setPrintColor(String printColor) {
		this.printColor = printColor;
	}
	public String getPaymentFlag() {
		return paymentFlag;
	}
	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}

	public String getCreditCardLast4Digit() {
		return creditCardLast4Digit;
	}
	public void setCreditCardLast4Digit(String creditCardLast4Digit) {
		this.creditCardLast4Digit = creditCardLast4Digit;
	}
	public String getOrderedBy() {
		return orderedBy;
	}
	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
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
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberCashPoint() {
		return memberCashPoint;
	}
	public void setMemberCashPoint(String memberCashPoint) {
		this.memberCashPoint = memberCashPoint;
	}
	public String getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getBillingName() {
		return billingName;
	}
	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getBillingCountryName() {
		return billingCountryName;
	}
	public void setBillingCountryName(String billingCountryName) {
		this.billingCountryName = billingCountryName;
	}
	public String getShippingCountryName() {
		return shippingCountryName;
	}
	public void setShippingCountryName(String shippingCountryName) {
		this.shippingCountryName = shippingCountryName;
	}
	public String getSeeMemo() {
		return seeMemo;
	}
	public void setSeeMemo(String seeMemo) {
		this.seeMemo = seeMemo;
	}
	public String getFinalCheck() {
		return finalCheck;
	}
	public void setFinalCheck(String finalCheck) {
		this.finalCheck = finalCheck;
	}
	public String getBillingMiddleName() {
		return billingMiddleName;
	}
	public void setBillingMiddleName(String billingMiddleName) {
		this.billingMiddleName = billingMiddleName;
	}
	public String getShippingMiddleName() {
		return shippingMiddleName;
	}
	public void setShippingMiddleName(String shippingMiddleName) {
		this.shippingMiddleName = shippingMiddleName;
	}
	public String getBillingAddress2() {
		return billingAddress2;
	}
	public void setBillingAddress2(String billingAddress2) {
		this.billingAddress2 = billingAddress2;
	}
	public String getShippingAddress2() {
		return shippingAddress2;
	}
	public void setShippingAddress2(String shippingAddress2) {
		this.shippingAddress2 = shippingAddress2;
	}
	public String getContainingGM() {
		return containingGM;
	}
	public void setContainingGM(String containingGM) {
		this.containingGM = containingGM;
	}
	public String getVerifiedAddress() {
		return verifiedAddress;
	}
	public void setVerifiedAddress(String verifiedAddress) {
		this.verifiedAddress = verifiedAddress;
	}
}