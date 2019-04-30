package com.samsbeauty.warehouse.tag.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.samsbeauty.warehouse.model.Warehouse;
import com.samsbeauty.warehouse.model.WarehouseAisle;
import com.samsbeauty.warehouse.model.WarehouseGroup;
import com.samsbeauty.warehouse.model.WarehouseLevel;
import com.samsbeauty.warehouse.model.WarehouseSubgroup;

@Entity
@Table(name="warehouse_tag")
public class WarehouseTag {	
	public static class NameCode {
		public static final String BAY = "093001000";
		public static final String SECTION = "093002000";
		public static final String WAREHOUSE = "093003000";
		public static final String LEVEL = "093004000";
		public static final String AISLE = "093005000";
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tag_id")
	private Long tagId;
	
	@Column
	private String tag;
	@Column
	private String name;
	@Column
	private String description;
	
	@OneToOne(mappedBy="warehouseTag")
	private Warehouse warehouse;
	
	@OneToOne(mappedBy="warehouseTag")
	private WarehouseAisle warehouseAisle;
	
	@OneToOne(mappedBy="warehouseTag")
	private WarehouseGroup warehouseGroup;
	
	@OneToOne(mappedBy="warehouseTag")
	private WarehouseSubgroup warehouseSubgroup;
	
	@OneToOne(mappedBy="warehouseTag")
	private WarehouseLevel warehouseLevel;
	
	public void update(String tag, String description) {
		this.tag = tag;
		this.description = description;
	}
	
	public static WarehouseTagBuilder builder() {
		return new WarehouseTagBuilder();
	}
	
	public WarehouseTag() {
		
	}
	
	public Long getTagId() {
		return tagId;
	}
	
	public String getTag() {
		return tag;
	}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	public WarehouseTag(String tag, String name, String description) {
		this.tag = tag;
		this.name = name;
		this.description = description;
	}
	
	public static class WarehouseTagBuilder {
		private String tag;
		private String name;
		private String description;
		
		public WarehouseTagBuilder setTag(String tag) {
			this.tag = tag;
			return this;
		}
		
		public WarehouseTagBuilder setName(String name){
			this.name = name;
			return this;
		}
		
		public WarehouseTagBuilder setDescription(String description) {
			this.description = description;
			return this;
		}
		
		public WarehouseTag createWarehouseTag() {
			return new WarehouseTag(tag, name, description);
		}
	}
	
	
}
