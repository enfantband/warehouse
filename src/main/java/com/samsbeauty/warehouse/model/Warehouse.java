package com.samsbeauty.warehouse.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.tag.model.WarehouseTag;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Warehouse {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="warehouse_id")
	private Long warehouseId;
	@Column(name="warehouse_code", columnDefinition="CHAR(4)")
	private String warehouseCode;
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
	
	@OneToMany(mappedBy="warehouse")
	@Where(clause="use_flag='Y'")
	@JsonIgnore
	private List<WarehouseAisle> warehouseAisles;
	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tag_id")
	private WarehouseTag warehouseTag;

	@Enumerated(EnumType.STRING)
	private UseFlag useFlag;


	public Long getWarehouseId() {
		return warehouseId;
	}


	public String getWarehouseCode() {
		return warehouseCode;
	}
	

	public Date getRegDate() {
		return regDate;
	}


	public Date getModDate() {
		return modDate;
	}


	public WarehouseEmployee getRegBy() {
		return regBy;
	}


	public WarehouseEmployee getModBy() {
		return modBy;
	}

	public WarehouseTag getWarehouseTag() {
		return warehouseTag;
	}
	
	public void deactivate(WarehouseEmployee delBy) {
		this.modBy = delBy;
		this.useFlag = UseFlag.N;
	}
	
	public void update(WarehouseEmployee modBy) {
		this.modBy = modBy;
	}
	
	public Warehouse() {
		
	}
	
	public Warehouse(String warehouseCode, Date regDate, Date modDate, WarehouseEmployee regBy,
			WarehouseEmployee modBy, WarehouseTag warehouseTag) {
		super();
		this.warehouseCode = warehouseCode;
		this.regDate = regDate;
		this.modDate = modDate;
		this.regBy = regBy;
		this.modBy = modBy;
		this.warehouseTag = warehouseTag;
		this.useFlag = UseFlag.Y;
	}

	public static WarehouseBuilder builder() {
		return new WarehouseBuilder();
	}

	public static class WarehouseBuilder {
		private String warehouseCode;
		private Date regDate;
		private Date modDate;
		private WarehouseEmployee regBy;
		private WarehouseEmployee modBy;
		private WarehouseTag warehouseTag;
		
		public WarehouseBuilder() {
			
		}
		
		public WarehouseBuilder setWarehouseCode(String warehouseCode) {
			this.warehouseCode = warehouseCode;
			return this;
		}
		
		public WarehouseBuilder setRegDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}
		
		public WarehouseBuilder setModDate(Date modDate) {
			this.modDate = modDate;
			return this;
		}
		
		public WarehouseBuilder setWarehouseTag(WarehouseTag warehouseTag) {
			this.warehouseTag = warehouseTag;
			return this;
		}
		
		public WarehouseBuilder setRegBy(WarehouseEmployee regBy) {
			this.regBy = regBy;
			return this;
		}
		
		public WarehouseBuilder setModBy(WarehouseEmployee modBy) {
			this.modBy = modBy;
			return this;
		}
		
		public Warehouse createWarehouse() {
			return new Warehouse(warehouseCode, regDate, modDate, regBy, modBy, warehouseTag);
		}
	}
	
}
