package com.samsbeauty.warehouse.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;

@Entity
@Table(name="warehouse_outbound")
public class WarehouseOutbound implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8289641129335445267L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="outbound_id")
	private Long outboundId;
	
	@Column
	private Integer quantity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="item_id")
	private WarehouseItem warehouseItem;

	@Column(name="r_date")
	private Date regDate;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="r_e_gid", referencedColumnName="gid")
	private WarehouseEmployee regBy;
	
	public WarehouseOutbound(Long outboundId, Integer quantity, WarehouseItem warehouseItem, Date regDate, WarehouseEmployee regBy) {
		super();
		this.outboundId = outboundId;
		this.quantity = quantity;
		this.warehouseItem = warehouseItem;
		this.regDate = regDate;
		this.regBy = regBy;
	}
	
	public static WarehouseOutboundBuilder builder() {
		return new WarehouseOutboundBuilder();
	}
	
	public static class WarehouseOutboundBuilder {
		private Long outboundId;
		private Integer quantity;
		private WarehouseItem warehouseItem;
		private Date regDate;
		private WarehouseEmployee regBy;
		
		public WarehouseOutboundBuilder() {
			
		}
		
		public WarehouseOutboundBuilder setOutboundId(Long outboundId) {
			this.outboundId = outboundId;
			return this;
		}
		
		public WarehouseOutboundBuilder setQuantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}
		
		public WarehouseOutboundBuilder setWarehouseItem(WarehouseItem warehouseItem) {
			this.warehouseItem = warehouseItem;
			return this;
		}
		
		public WarehouseOutboundBuilder setRegDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}
		
		public WarehouseOutboundBuilder setRegBy(WarehouseEmployee regBy) {
			this.regBy = regBy;
			return this;
		}
		
		public WarehouseOutbound createWarehouseOutbound() {
			return new WarehouseOutbound(outboundId, quantity, warehouseItem, regDate, regBy);
		}
	}
}
