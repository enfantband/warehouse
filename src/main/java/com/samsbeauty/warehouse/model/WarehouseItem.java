package com.samsbeauty.warehouse.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.samsbeauty.old.model.Inventory;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;

@Entity
@Table(name="warehouse_item")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WarehouseItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6087645202047873265L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="item_id")
	private Integer itemId;
	
	@Column(name="product_id")
	private Long productId;
	
	@Column(name="generated_barcode")
	private String generatedBarcode;
	
	@Column
	private Integer quantity;

	@Column(name="r_date")
	private Date regDate;
	@Column(name="m_date")
	private Date modDate;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="r_e_gid", referencedColumnName="gid")
	private WarehouseEmployee regBy;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="m_e_gid", referencedColumnName="gid")
	private WarehouseEmployee modBy;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="level_id")
	private WarehouseLevel warehouseLevel;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(
		name="warehouse_box_item_mapping",
		joinColumns = @JoinColumn(name="item_id"),
		inverseJoinColumns = @JoinColumn(name="item_box_id")
	)
	private WarehouseItemBox warehouseItemBox;
	
	@OneToMany
	private List<WarehouseOutbound> warehouseOutbounds;
	
	@OneToMany
	private List<WarehouseInbound> warehouseInbounds;
	
	@Enumerated(EnumType.STRING)
	private UseFlag useFlag;
	
	@Transient
	private Inventory inventory;
	
	public Integer getQuantity() {
		return quantity;
	}
	public Long getProductId() {
		return productId;
	}
	public WarehouseLevel getWarehouseLevel() {
		return warehouseLevel;
	}
	public WarehouseItemBox getWarehouseItemBox() {
		return warehouseItemBox;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public WarehouseEmployee getRegBy() {
		return regBy;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public WarehouseEmployee getModBy() {
		return modBy;
	}
	
	public String getGeneratedBarcode() {
		return generatedBarcode;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void delete(WarehouseEmployee delBy, Date delDate) {
		this.useFlag = UseFlag.N;
		update(delBy, modDate);
	}
	
	public void outbound(Integer quantity) {
		this.quantity -= quantity;
	}
	
	public void inbound(Integer quantity) {
		this.quantity += quantity;
	}
	
	public void update(WarehouseEmployee modBy, Date modDate) {
		this.modBy = modBy;
		this.modDate = modDate;
	}
	
	public WarehouseItem() {
		
	}
	
	public Integer getItemId(){
		return itemId;
	}
	public WarehouseItem(Long productId, String generatedBarcode, Integer quantity, Date regDate, Date modDate, WarehouseEmployee regBy, WarehouseEmployee modBy,
			WarehouseLevel warehouseLevel) {
		this.productId = productId;
		this.generatedBarcode = generatedBarcode;
		this.quantity = quantity;
		this.regDate = regDate;
		this.modDate = modDate;
		this.regBy = regBy;
		this.modBy = modBy;
		this.warehouseLevel = warehouseLevel;
		this.useFlag = UseFlag.Y;
	}
	public WarehouseItem(Long productId, String generatedBarcode, Integer quantity, Date regDate, Date modDate, WarehouseEmployee regBy, WarehouseEmployee modBy,
			WarehouseLevel warehouseLevel, WarehouseItemBox warehouseItemBox) {
		this.productId = productId;
		this.generatedBarcode = generatedBarcode;
		this.quantity = quantity;
		this.regDate = regDate;
		this.modDate = modDate;
		this.regBy = regBy;
		this.modBy = modBy;
		this.warehouseLevel = warehouseLevel;
		this.warehouseItemBox = warehouseItemBox;
		this.useFlag = UseFlag.Y;
	}
	
	public static WarehouseItemBuilder builder() {
		return new WarehouseItemBuilder();
	}
	public static class WarehouseItemBuilder {
		private Long productId;
		private String generatedBarcode;
		private Integer quantity;
		private Date regDate;
		private Date modDate; 
		private WarehouseEmployee regBy;
		private WarehouseEmployee modBy;
		private WarehouseLevel warehouseLevel;
		private WarehouseItemBox warehouseItemBox;
		
		public WarehouseItemBuilder() {
			
		}
		
		public WarehouseItemBuilder setProductId(Long productId) {
			this.productId = productId;
			return this;
		}
		
		public WarehouseItemBuilder setQuantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}
		
		public WarehouseItemBuilder setRegDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}
		
		public WarehouseItemBuilder setModDate(Date modDate) {
			this.modDate = modDate;
			return this;
		}
		
		public WarehouseItemBuilder setRegBy(WarehouseEmployee regBy) {
			this.regBy = regBy;
			return this;
		}
		
		public WarehouseItemBuilder setModBy(WarehouseEmployee modBy) {
			this.modBy = modBy;
			return this;
		}
		
		public WarehouseItemBuilder setGeneratedBarcode(String generatedBarcode) {
			this.generatedBarcode = generatedBarcode;
			return this;
		}
		public WarehouseItemBuilder setWarehouseLevel(WarehouseLevel warehouseLevel) {
			this.warehouseLevel = warehouseLevel;
			return this;
		}
		public WarehouseItemBuilder setWarehouseItemBox(WarehouseItemBox warehouseItemBox) {
			this.warehouseItemBox = warehouseItemBox;
			return this;
		}
		public WarehouseItem createWarehouseItem() {
			if(warehouseItemBox == null) {
				return new WarehouseItem(productId, generatedBarcode, quantity, regDate, modDate, regBy, modBy, warehouseLevel);	
			} else {
				return new WarehouseItem(productId, generatedBarcode, quantity, regDate, modDate, regBy, modBy, warehouseLevel, warehouseItemBox);
			}
		}
	}
}
