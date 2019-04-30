package com.samsbeauty.warehouse.model;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.samsbeauty.warehouse.tag.model.WarehouseTag;

@Entity
@Table(name="warehouse_aisle")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WarehouseAisle {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="aisle_id")
	private Long aisleId;
	
	@Column(name="aisle_code", columnDefinition="CHAR(3)")
	private String aisleCode;
	
	@Enumerated(EnumType.STRING)
	private UseFlag useFlag;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="warehouse_id")
	@Where(clause="use_flag='Y'")
	@JsonIgnore
	private Warehouse warehouse;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tag_id")
	private WarehouseTag warehouseTag;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="geoinfo_id")
	private WarehouseGeoInfo warehouseGeoInfo;
	
	@ManyToMany(mappedBy="warehouseAisles")
	private List<WarehouseGroup> warehouseGroups;
	

	public Long getAisleId() {
		return aisleId;
	}
	
	public String getAisleCode() {
		return aisleCode;
	}

	public UseFlag getUseFlag() {
		return useFlag;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	public WarehouseTag getWarehouseTag() {
		return warehouseTag;
	}

	public WarehouseGeoInfo getWarehouseGeoInfo() {
		return warehouseGeoInfo;
	}
	
	public void deactivate() {
		this.useFlag = UseFlag.N;		
	}
	
	public WarehouseAisle() {
		
	}

	public WarehouseAisle(String aisleCode, Warehouse warehouse, WarehouseTag warehouseTag,
			WarehouseGeoInfo warehouseGeoInfo) {
		super();
		this.aisleCode = aisleCode;
		this.warehouse = warehouse;
		this.warehouseTag = warehouseTag;
		this.warehouseGeoInfo = warehouseGeoInfo;
		this.useFlag = UseFlag.Y;
	}
	
	public static WarehouseAisleBuilder builder(WarehouseTag warehouseTag, WarehouseGeoInfo warehouseGeoInfo) {
		return new WarehouseAisleBuilder(warehouseTag, warehouseGeoInfo);
	}
	
	public static class WarehouseAisleBuilder {
		private String aisleCode;
		private Warehouse warehouse;
		private WarehouseTag warehouseTag;
		private WarehouseGeoInfo warehouseGeoInfo;
		public WarehouseAisleBuilder(WarehouseTag warehouseTag, WarehouseGeoInfo warehouseGeoInfo) {
			this.warehouseTag = warehouseTag;
			this.warehouseGeoInfo = warehouseGeoInfo;
		}
		
		public WarehouseAisleBuilder setWarehouse(Warehouse warehouse) {
			this.warehouse = warehouse;
			return this;
		}
		
		public WarehouseAisleBuilder setAisleCode(String aisleCode) {
			this.aisleCode = aisleCode;
			return this;
		}
		
		public WarehouseAisle createWarehouseAisle() {
			return new WarehouseAisle(aisleCode, warehouse, warehouseTag, warehouseGeoInfo);
		}
	}
}
