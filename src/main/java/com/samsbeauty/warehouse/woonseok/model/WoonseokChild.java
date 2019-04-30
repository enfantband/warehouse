package com.samsbeauty.warehouse.woonseok.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="woonseok_child")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class WoonseokChild {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="child_id")
	private long childId;
	
	@Column(name="request_id")
	private int requestId;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="address")
	private String address;
	
	@Column(name="zip_code")
	private String zipCode;
	
	@Column(name="name")
	private String name;
	
	@Column(name="sex")
	private String sex;
	
	
	public long getChildId() {
		return childId;
	}

	public void setChildId(long childId) {
		this.childId = childId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	
	public WoonseokChild(){		
	}
	
	public WoonseokChild(Long childId,int requestId,String phone,String address, String zipCode,String name,String sex){
		
		this.childId = childId;
		this.requestId = requestId;
		this.phone = phone;
		this.address = address;
		this.zipCode = zipCode;
		this.name = name;
		this.sex = sex;
		
	}
	
	public static WoonseokChildBuilder builder(){
		return new WoonseokChildBuilder();
	}
	
	public static class WoonseokChildBuilder{
		private Long childId;
		private int requestId;
		private String phone;
		private String address;
		private String zipCode;
		private String name;
		private String sex;
		
		
			public WoonseokChildBuilder copyOf(WoonseokChild ar){
				this.childId = ar.getChildId();
				this.requestId = ar.getRequestId();
				this.phone = ar.getPhone();
				this.address = ar.getAddress();
				this.zipCode = ar.getZipCode();
				this.name = ar.getName();
				this.sex = ar.getSex();
				return this;
			}
		
		public WoonseokChildBuilder(){}
		
		
		public WoonseokChildBuilder setChildId(Long childId){
			this.childId = childId;
			return this;
		}
		
		public WoonseokChildBuilder setReqestId(int requestId){
			this.requestId = requestId;
			return this;
		}
		
		public WoonseokChildBuilder setPhone(String phone){
			this.phone = phone;
			return this;
		}
		
		public WoonseokChildBuilder setAddress(String address){
			this.address = address;
			return this;
		}
		
		public WoonseokChildBuilder setZipCode(String zipCode){
			this.zipCode = zipCode;
			return this;
		}
		
		public WoonseokChildBuilder setName(String name){
			this.name = name;
			return this;
		}
		
		public WoonseokChildBuilder setSex(String sex){
			this.sex = sex;
			return this;
		}
		
		public WoonseokChild createWoonseokChild(){
			return new WoonseokChild(childId,requestId,phone,address,zipCode,name,sex);
		}
			
	}
	

}
