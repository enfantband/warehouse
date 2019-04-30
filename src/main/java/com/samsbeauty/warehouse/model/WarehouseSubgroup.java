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
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.tag.model.WarehouseTag;

@Entity
@Table(name="warehouse_subgroup")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WarehouseSubgroup {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="subgroup_id")
	private Long subgroupId;
	
	@Column(name="subgroup_code", columnDefinition="CHAR(3)")
	private String subgroupCode;
	
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
	
	@Enumerated(EnumType.STRING)
	private UseFlag useFlag;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="group_id", referencedColumnName="group_id")
	@Where(clause="use_flag='Y'")
	@JsonIgnore
	private WarehouseGroup warehouseGroup;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="warehouseSubgroup")
	@JsonIgnore
	private List<WarehouseLevel> warehouseLevel;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="geoinfo_id")
	private WarehouseGeoInfo warehouseGeoInfo;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tagId")
	private WarehouseTag warehouseTag;
	
	public Long getSubgroupId() {
		return subgroupId;
	}
	
	public String getSubgroupCode() {
		return subgroupCode;
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
	
	public WarehouseGroup getWarehouseGroup() {
		return warehouseGroup;
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
	
	public WarehouseSubgroup() {
		
	}

	public WarehouseSubgroup(String subgroupCode, Date regDate, Date modDate, WarehouseEmployee regBy,
			WarehouseEmployee modBy, WarehouseGroup warehouseGroup, WarehouseTag warehouseTag, WarehouseGeoInfo warehouseGeoInfo) {
		super();
		this.subgroupCode = subgroupCode;
		this.regDate = regDate;
		this.modDate = modDate;
		this.regBy = regBy;
		this.modBy = modBy;
		this.warehouseGroup = warehouseGroup;
		this.useFlag = UseFlag.Y;
		this.warehouseTag = warehouseTag;
		this.warehouseGeoInfo = warehouseGeoInfo;
	}
	
	public static WarehouseSubgroupBuilder builder(WarehouseGroup warehouseGroup, WarehouseTag warehouseTag, WarehouseGeoInfo warehouseGeoInfo) {
		return new WarehouseSubgroupBuilder(warehouseGroup, warehouseTag, warehouseGeoInfo);
	}
	
	public static class WarehouseSubgroupBuilder {
		private String subgroupCode;
		private Date regDate;
		private Date modDate;
		private WarehouseEmployee regBy;
		private WarehouseEmployee modBy;
		private WarehouseGroup warehouseGroup;
		private WarehouseTag warehouseTag;
		private WarehouseGeoInfo warehouseGeoInfo;
		
		public WarehouseSubgroupBuilder(WarehouseGroup warehouseGroup, WarehouseTag warehouseTag, WarehouseGeoInfo warehouseGeoInfo) {			
			this.warehouseGroup = warehouseGroup;
			this.warehouseTag = warehouseTag;
			this.warehouseGeoInfo = warehouseGeoInfo;
		}
		
		public WarehouseSubgroupBuilder setSubgroupCode(String subgroupCode) {
			this.subgroupCode = subgroupCode;
			return this;
		}
		
		public WarehouseSubgroupBuilder setRegDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}
		
		public WarehouseSubgroupBuilder setModDate(Date modDate) {
			this.modDate = modDate;
			return this;
		}
		
		public WarehouseSubgroupBuilder setRegBy(WarehouseEmployee regBy) {
			this.regBy = regBy;
			return this;
		}
		public WarehouseSubgroupBuilder setModBy(WarehouseEmployee modBy) {
			this.modBy = modBy;
			return this;
		}
		public WarehouseSubgroupBuilder setWarehouseTag(WarehouseTag warehouseTag) {
			this.warehouseTag = warehouseTag;
			return this;
		}
		public WarehouseSubgroup createWarehouseSubgroup() {
			return new WarehouseSubgroup(subgroupCode, regDate, modDate, regBy, modBy, warehouseGroup, warehouseTag, warehouseGeoInfo);
		}
		
	}
}