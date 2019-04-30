package com.samsbeauty.warehouse.picking.export.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.samsbeauty.warehouse.picking.model.PickingJob;

public class GroupPickingJobExport {
	private PickingJob pickingJob;
	private String groupName;
	private String groupDescription;
	private Map<String, GroupPickingItemExport> itemMap;
	private List<GroupPickingItemExport> itemList;
	
	public GroupPickingJobExport() {
		itemMap = new LinkedHashMap<>();
	}

	public GroupPickingJobExport(PickingJob pickingJob, String groupName, String groupDescription, Map<String, GroupPickingItemExport> itemMap) {
		this.pickingJob = pickingJob;
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.itemMap = itemMap;
	}
	
	public PickingJob getPickingJob() {
		return pickingJob;
	}
	
	public String getGroupName() {
		return groupName;
	}
	public String getGroupDescription() {
		return groupDescription;
	}
	public void setItemList(List<GroupPickingItemExport> itemList) {
		this.itemList = itemList;
	}
	public Map<String, GroupPickingItemExport> getItemMap() {
		return itemMap;
	}
	public List<GroupPickingItemExport> getItemList() { 
		return itemList;
	}
}
