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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="warehouse_group")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WarehouseGroup {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="group_id")
	private Long groupId;

	@Column(name="group_code", columnDefinition="CHAR(3)")
	private String groupCode;

	@Column(name="r_date")
	private Date regDate;
	@Column(name="m_date")
	private Date modDate;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tag_id")
	private WarehouseTag warehouseTag;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="r_e_gid", referencedColumnName="gid")
	private WarehouseEmployee regBy;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="m_e_gid", referencedColumnName="gid")
	private WarehouseEmployee modBy;
	
	@Enumerated(EnumType.STRING)
	private UseFlag useFlag;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="geoinfo_id")
	private WarehouseGeoInfo warehouseGeoInfo;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="warehouse_aisle_group_mapping",
			joinColumns=@JoinColumn(name="group_id", referencedColumnName="group_id"),
			inverseJoinColumns=@JoinColumn(name="aisle_id", referencedColumnName="aisle_id")
			)
	@Where(clause="use_flag='Y'")
	private List<WarehouseAisle> warehouseAisles;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="group_id")
	@JsonIgnore
	private List<WarehouseSubgroup> warehouseSubgroups;
	
	
	public Long getGroupId() {
		return groupId;
	}
	
	public String getGroupCode() {
		return groupCode;
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
	
	public WarehouseGeoInfo getWarehouseGeoInfo() {
		return warehouseGeoInfo; 
	}
	
	public List<WarehouseAisle> getWarehouseAisles() {
		return warehouseAisles;
	}
	
	public WarehouseTag getWarehouseTag() {
		return warehouseTag;
	}
	
	public void deactivate() {
		this.useFlag = UseFlag.N;
	}
	
	public WarehouseGroup() {
		
	}

	public WarehouseGroup(String groupCode, Date regDate, Date modDate, WarehouseTag warehouseTag,
			WarehouseEmployee regBy, WarehouseEmployee modBy, WarehouseGeoInfo warehouseGeoInfo, List<WarehouseAisle> warehouseAisles) {
		super();
		this.groupCode = groupCode;
		this.regDate = regDate;
		this.modDate = modDate;
		this.warehouseTag = warehouseTag;
		this.regBy = regBy;
		this.modBy = modBy;
		this.warehouseGeoInfo = warehouseGeoInfo;
		this.useFlag = UseFlag.Y;
		this.warehouseAisles = warehouseAisles;
	}
	
	public static WarehouseGroupBuilder builder(WarehouseTag warehouseTag, WarehouseGeoInfo warehouseGeoInfo) {
		return new WarehouseGroupBuilder(warehouseTag, warehouseGeoInfo);
	}
	public static class WarehouseGroupBuilder {
		private String groupCode;
		private Date regDate;
		private Date modDate;
		private WarehouseTag warehouseTag;
		private WarehouseEmployee regBy;
		private WarehouseEmployee modBy;
		private WarehouseGeoInfo warehouseGeoInfo;
		private List<WarehouseAisle> warehouseAisles; 
		
		public WarehouseGroupBuilder(WarehouseTag warehouseTag, WarehouseGeoInfo warehouseGeoInfo) {
			this.warehouseTag = warehouseTag;
			this.warehouseGeoInfo = warehouseGeoInfo;
		}
		
		public WarehouseGroupBuilder setGroupCode(String groupCode) {
			this.groupCode = groupCode;
			return this;
		}
		
		public WarehouseGroupBuilder setRegDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}
		
		public WarehouseGroupBuilder setModDate(Date modDate) {
			this.modDate = modDate;
			return this;
		}
		
		public WarehouseGroupBuilder setWarehouseTag(WarehouseTag warehouseTag) {
			this.warehouseTag = warehouseTag;
			return this;
		}
		
		public WarehouseGroupBuilder setRegBy(WarehouseEmployee regBy) {
			this.regBy = regBy;
			return this;
		}
		
		public WarehouseGroupBuilder setModBy(WarehouseEmployee modBy) {
			this.modBy = modBy;
			return this;
		}
		
		public WarehouseGroupBuilder setWarehouseGeoInfo(WarehouseGeoInfo warehouseGeoInfo) {
			this.warehouseGeoInfo = warehouseGeoInfo;
			return this;
		}
		
		public WarehouseGroupBuilder setWarehouseAisles(List<WarehouseAisle> warehouseAisles) {
			this.warehouseAisles = warehouseAisles;
			return this;
		}
		
		public WarehouseGroup createWarehouseGroup() {
			return new WarehouseGroup( groupCode, regDate, modDate, warehouseTag, regBy, modBy, warehouseGeoInfo, warehouseAisles);
		}
		
	}
	
}
