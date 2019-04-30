package com.samsbeauty.old.model;

import java.math.BigDecimal;
import java.util.List;

public class OrderInfoForPicking {
	public static class HOST {
		public final static String SAMSBEAUTY = "SB";
		public final static String AMAZON_HAIRSTYLE21 = "AMZHS";
		public final static String AMAZON_EBEUATY = "AMZEB";
	}
	private String orderNo;
	private String host;
	private String riDate;
	private String shippingMethod;
	private String shippingMethodName;
	private String firstname;
	private String middlename;
	private String lastname;
	private String email;
	private String shippingAddress1;
	private String shippingAddress2;
	private String shippingCity;
	private String shippingState;
	private String shippingZipcode;
	private String shippingCountry;
	private String promotionCode;
	private String logoImagePath;
	private String orderMemo;
	private BigDecimal shippingPrice;
	private BigDecimal shippingDiscPrice;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("orderNo = " + orderNo);
		sb.append(System.lineSeparator());
		sb.append("host = " + host);
		sb.append(System.lineSeparator());
		sb.append("riDate = " + riDate);
		sb.append(System.lineSeparator());
		sb.append("shippingMethod = " + shippingMethod);
		sb.append(System.lineSeparator());
		sb.append("shippingMethodName = " + shippingMethodName);
		sb.append(System.lineSeparator());
		sb.append("firstname = " + firstname);
		sb.append(System.lineSeparator());
		sb.append("middlename = " + middlename);
		sb.append(System.lineSeparator());
		sb.append("lastname = " + lastname);
		sb.append(System.lineSeparator());
		sb.append("email = " + email);
		sb.append(System.lineSeparator());
		sb.append("shippingAddress1 = " + shippingAddress1);
		sb.append(System.lineSeparator());
		sb.append("shippingAddress2 = " + shippingAddress2);
		sb.append(System.lineSeparator());
		sb.append("shippingCity = " + shippingCity);
		sb.append(System.lineSeparator());
		sb.append("shippingState = " + shippingState);
		sb.append(System.lineSeparator());
		sb.append("shippingZipcode = " + shippingZipcode);
		sb.append(System.lineSeparator());
		sb.append("shippingCountry = " + shippingCountry);
		sb.append(System.lineSeparator());
		sb.append("promotionCode = " + promotionCode);
		sb.append(System.lineSeparator());
		sb.append("logoImagePath = " + logoImagePath);
		sb.append(System.lineSeparator());
		sb.append("shippingPrice = " + shippingPrice);
		sb.append(System.lineSeparator());
		sb.append("shippingDiscPrice = " + shippingDiscPrice);
		return sb.toString();
	}
	
	private List<OrderItem> orderItems;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getRiDate() {
		return riDate;
	}
	public void setRiDate(String riDate) {
		this.riDate = riDate;
	}
	public String getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	public String getShippingMethodName() {
		return shippingMethodName;
	}
	public void setShippingMethodName(String shippingMethodName) {
		this.shippingMethodName = shippingMethodName;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getShippingAddress1() {
		return shippingAddress1;
	}
	public void setShippingAddress1(String shippingAddress1) {
		this.shippingAddress1 = shippingAddress1;
	}
	public String getShippingAddress2() {
		return shippingAddress2;
	}
	public void setShippingAddress2(String shippingAddress2) {
		this.shippingAddress2 = shippingAddress2;
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
	public String getShippingCountry() {
		return shippingCountry;
	}
	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}
	public String getPromotionCode() {
		return promotionCode;
	}
	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getLogoImagePath() {
		return logoImagePath;
	}
	public void setLogoImagePath(String logoImagePath) {
		this.logoImagePath = logoImagePath;
	}
	
	public String getOrderMemo() {
		return orderMemo;
	}
	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}
	public BigDecimal getShippingPrice() {
		return shippingPrice;
	}
	public void setShippingPrice(BigDecimal shippingPrice) {
		this.shippingPrice = shippingPrice;
	}
	public BigDecimal getShippingDiscPrice() {
		return shippingDiscPrice;
	}
	public void setShippingDiscPrice(BigDecimal shippingDiscPrice) {
		this.shippingDiscPrice = shippingDiscPrice;
	}
	public OrderInfoForPicking(String orderNo, String host, String riDate, String shippingMethod,
			String shippingMethodName, String firstname, String middlename, String lastname, String email,
			String shippingAddress1, String shippingAddress2, String shippingCity, String shippingState,
			String shippingZipcode, String shippingCountry, String promotionCode, String logoImagePath, String orderMemo, BigDecimal shippingPrice, BigDecimal shippingDiscPrice, List<OrderItem> orderItems) {
		super();
		this.orderNo = orderNo;
		this.host = host;
		this.riDate = riDate;
		this.shippingMethod = shippingMethod;
		this.shippingMethodName = shippingMethodName;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.email = email;
		this.shippingAddress1 = shippingAddress1;
		this.shippingAddress2 = shippingAddress2;
		this.shippingCity = shippingCity;
		this.shippingState = shippingState;
		this.shippingZipcode = shippingZipcode;
		this.shippingCountry = shippingCountry;
		this.promotionCode = promotionCode;
		this.logoImagePath = logoImagePath;
		this.orderMemo = orderMemo;
		this.shippingPrice = shippingPrice;
		this.shippingDiscPrice = shippingDiscPrice;
		this.orderItems = orderItems;
	}

	public static OrderInfoForPickingBuilder builder() {
		return new OrderInfoForPickingBuilder();
	}

	public static class OrderInfoForPickingBuilder {
		private String orderNo;
		private String host;
		private String riDate;
		private String shippingMethod;
		private String shippingMethodName;
		private String firstname;
		private String middlename;
		private String lastname;
		private String email;
		private String shippingAddress1;
		private String shippingAddress2;
		private String shippingCity;
		private String shippingState;
		private String shippingZipcode;
		private String shippingCountry;
		private String promotionCode;
		private String orderMemo;
		private BigDecimal shippingPrice;
		private BigDecimal shippingDiscPrice;
		private List<OrderItem> orderItems;
		private String logoImagePath;
		
		public OrderInfoForPickingBuilder setOrderNo(String orderNo) {
			this.orderNo = orderNo;
			return this;
		}
		
		public OrderInfoForPickingBuilder setHost(String host) {
			this.host = host;
			return this;
		}
		
		public OrderInfoForPickingBuilder setRiDate(String riDate) {
			this.riDate = riDate;
			return this;
		}
		public OrderInfoForPickingBuilder setShippingMethod(String shippingMethod) {
			this.shippingMethod = shippingMethod;
			return this;
		}
		public OrderInfoForPickingBuilder setShippingMethodName(String shippingMethodName) {
			this.shippingMethodName = shippingMethodName;
			return this;
		}
		public OrderInfoForPickingBuilder setFirstname(String firstname) {
			this.firstname = firstname;
			return this;
		}
		public OrderInfoForPickingBuilder setMiddlename(String middlename) {
			this.middlename = middlename;
			return this;
		}

		public OrderInfoForPickingBuilder setLastname(String lastname) {
			this.lastname = lastname;
			return this;
		}

		public OrderInfoForPickingBuilder setEmail(String email) {
			this.email = email;
			return this;
		}

		public OrderInfoForPickingBuilder setShippingAddress1(String shippingAddress1) {
			this.shippingAddress1 = shippingAddress1;
			return this;
		}

		public OrderInfoForPickingBuilder setShippingAddress2(String shippingAddress2) {
			this.shippingAddress2 = shippingAddress2;
			return this;
		}

		public OrderInfoForPickingBuilder setShippingCity(String shippingCity) {
			this.shippingCity = shippingCity;
			return this;
		}

		public OrderInfoForPickingBuilder setShippingState(String shippingState) {
			this.shippingState = shippingState;
			return this;
		}

		public OrderInfoForPickingBuilder setShippingZipcode(String shippingZipcode) {
			this.shippingZipcode = shippingZipcode;
			return this;
		}

		public OrderInfoForPickingBuilder setShippingCountry(String shippingCountry) {
			this.shippingCountry = shippingCountry;
			return this;
		}

		public OrderInfoForPickingBuilder setPromotionCode(String promotionCode) {
			this.promotionCode = promotionCode;
			return this;
		}
		public OrderInfoForPickingBuilder setLogoImagePath(String logoImagePath) {
			this.logoImagePath = logoImagePath;
			return this;
		}
		
		public OrderInfoForPickingBuilder setOrderMemo(String orderMemo) {
			this.orderMemo = orderMemo;
			return this;
		}
		
		public OrderInfoForPickingBuilder setShippingPrice(BigDecimal shippingPrice) {
			this.shippingPrice = shippingPrice;
			return this;
		}
		
		public OrderInfoForPickingBuilder setShippingDiscPrice(BigDecimal shippingDiscPrice) {
			this.shippingDiscPrice = shippingDiscPrice;
			return this;
		}
		
		public OrderInfoForPicking createOrderInfoForPicking() {
			return new OrderInfoForPicking(
					orderNo, 
					host,
					riDate,
					shippingMethod,
					shippingMethodName,
					firstname,
					middlename,
					lastname,
					email,
					shippingAddress1,
					shippingAddress2,
					shippingCity,
					shippingState,
					shippingZipcode,
					shippingCountry,
					promotionCode,
					logoImagePath,
					orderMemo,
					shippingPrice,
					shippingDiscPrice,
					orderItems
					);
		}
	}
}
