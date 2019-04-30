package com.samsbeauty.warehouse.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="warehouse_geoinfo")
public class WarehouseGeoInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long infoId;
	
	@Column(name="start_coord_x")
	private BigDecimal startCoordX;
	
	@Column(name="start_coord_y")
	private BigDecimal startCoordY;
	
	@Column(name="end_coord_x")
	private BigDecimal endCoordX;
	
	@Column(name="end_coord_y")
	private BigDecimal endCoordY;

	@Enumerated(EnumType.STRING)
	private Walkable walkable;
	
	@OneToOne(mappedBy="warehouseGeoInfo")
	private WarehouseGroup warehouseGroup;
	
	@OneToOne(mappedBy="warehouseGeoInfo")
	private WarehouseAisle warehouseAisle;
	
	@OneToOne(mappedBy="warehouseGeoInfo")
	private WarehouseSubgroup warehouseSubgroup;
		
	
	public static WarehouseGeoInfoBuilder builder() {
		return new WarehouseGeoInfoBuilder();
	}
	public WarehouseGeoInfo() {
		
 	}

	public WarehouseGeoInfo(BigDecimal startCoordX, BigDecimal startCoordY, BigDecimal endCoordX, BigDecimal endCoordY,
			Walkable walkable) {
		super();
		this.startCoordX = startCoordX;
		this.startCoordY = startCoordY;
		this.endCoordX = endCoordX;
		this.endCoordY = endCoordY;
		this.walkable = walkable;
	}
	
	public static class WarehouseGeoInfoBuilder {
		private BigDecimal startCoordX;
		private BigDecimal startCoordY;
		private BigDecimal endCoordX;
		private BigDecimal endCoordY;
		private Walkable walkable;
		
		public WarehouseGeoInfoBuilder setStartCoordX(BigDecimal startCoordX) {
			this.startCoordX = startCoordX;
			return this;
		}
		
		public WarehouseGeoInfoBuilder setStartCoordY(BigDecimal startCoordY) {
			this.startCoordY = startCoordY;
			return this;
		}
		
		public WarehouseGeoInfoBuilder setEndCoordX(BigDecimal endCoordX) {
			this.endCoordX = endCoordX;
			return this;
		}
		
		public WarehouseGeoInfoBuilder setEndCoordY(BigDecimal endCoordY) {
			this.endCoordY = endCoordY;
			return this;
		}
		
		public WarehouseGeoInfoBuilder setWalkable(Walkable walkable) {
			this.walkable = walkable;
			return this;
		}
		
		public WarehouseGeoInfo createWarehouseGeoInfo() {
			return new WarehouseGeoInfo(startCoordX, startCoordY, endCoordX, endCoordY, walkable);
		}
	}
	
}
