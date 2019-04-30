package com.samsbeauty.warehouse.picking.export.model;

public class GroupPickingItemExportSort {
	private GroupPickingItemExport data;
	private Integer aisle;
	private Integer subgroup;
	private Integer level;
	public GroupPickingItemExportSort(GroupPickingItemExport data) {
		this.data = data;
	}
	
	public GroupPickingItemExport getData() {
		return data;
	}
	public void setData(GroupPickingItemExport data) {
		this.data = data;
	}
	public Integer getAisle() {
		return aisle;
	}
	public void setAisle(Integer aisle) {
		this.aisle = aisle;
	}
	public Integer getSubgroup() {
		return subgroup;
	}
	public void setSubgroup(Integer subgroup) {
		this.subgroup = subgroup;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
}
