package com.samsbeauty.warehouse.woonseok.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.picking.model.PickingItem;

@Entity
@Table(name="woonseok")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Woonseok {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="request_id")
	private Long requestId;
	
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="picking_job_id")
	private PickingItem pickingItem;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="request_id")
	private List<WoonseokChild> woonseokChild;

	public List<WoonseokChild> getWoonseokChild() {
		return woonseokChild;
	}

	public void setWoonseokChild(List<WoonseokChild> woonseokChild) {
		this.woonseokChild = woonseokChild;
	}

	@Column(name="group")
	private String group;
	
	
	@Column(name="status")
	private String status;
	

	@Column(name="reg_date")
	private Date regDate;
	
	@Column(name="mod_date")
	private Date modDate;
	
	@Column(name="active")
	private String active;
	
	@Column(name="emp")
	private String emp;
	
	public Long getRequestId() {
		return requestId;
	}
	
	public String getGroup() {
		return group;
	}
	
	public String getStatus() {
		return status;
	}
	
	
	public Date getRegDate() {
		return regDate;
	}
	
	public Date getModDate() {
		return modDate;
	}

	public String getActive(){
		return active;
	}
	
	public String getEmp(){
		return emp;
	}
	
	public void delete(WarehouseEmployee modBy, Date modDate) {
		
		
		this.modDate = modDate;
	}

	public Woonseok() {
		
	}
	
	public Woonseok(Long requestId, PickingItem pickingItem, String group,
			String status, WarehouseEmployee regBy, WarehouseEmployee modBy, Date regDate, Date modDate,String active,String emp) {
		super();
		this.requestId = requestId;
		this.group = group;
		this.status = status;
		this.active = active;
		this.regDate = regDate;
		this.modDate = modDate;
		this.emp = emp;
	}
	
	public static WoonseokBuilder builder() {
		return new WoonseokBuilder();
	}
	
	public static class WoonseokBuilder {
		private Long requestId;
		private String group;
		private String status;
		private String active;
		private PickingItem pickingItem;
		private WarehouseEmployee regBy;		
		private WarehouseEmployee modBy;	
		private WoonseokChild woonseokChild;
		private Date regDate;		
		private Date modDate;
		private String emp;
		public WoonseokBuilder copyOf(Woonseok ar) {
			this.requestId = ar.getRequestId();
			this.group = ar.getGroup();
			this.status = ar.getStatus();
			this.regDate = ar.getRegDate();
			this.modDate = ar.getModDate();
			this.active = ar.getActive();
			this.emp   = ar.getEmp();
			return this;
		}
		
		public WoonseokBuilder() {
			
		}
		
		public WoonseokBuilder setRequestId(Long requestId) {
			this.requestId = requestId;
			return this;
		}
		
		public WoonseokBuilder setGroup(String group) {
			this.group = group;
			return this;
		}
		public WoonseokBuilder setStatus(String status) {
			this.status = status;
			return this;
		}
		public WoonseokBuilder setActive(String active){
			this.active = active;
			return this;
		}
		public WoonseokBuilder setRegBy(WarehouseEmployee regBy) {
			this.regBy = regBy;
			return this;
		}
		public WoonseokBuilder setModBy(WarehouseEmployee modBy) {
			this.modBy = modBy;
			return this;
		}
		public WoonseokBuilder setRegDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}
		public WoonseokBuilder setModDate(Date modDate) {
			this.modDate = modDate;
			return this;
		}
		
		public WoonseokBuilder setEmp(String emp){
			this.emp = emp;
			return this;
		}
		public WoonseokBuilder setWoonseokChild(WoonseokChild woonseokChild){
			this.woonseokChild = woonseokChild;
			return this;
		}
		
		public Woonseok createAdjustmentRequest() {
			return new Woonseok(requestId, pickingItem, group, status, regBy, modBy, regDate, modDate,active,emp);
		}
	}
}
