package com.samsbeauty.old.model;

import java.util.List;

public class LocationItemInput<T> {
	private String locationBarcode;
	private String gid;
	private String boxBarcode;
	private List<T> items;
	public String getLocationBarcode() {
		return locationBarcode;
	}
	public void setLocationBarcode(String locationBarcode) {
		this.locationBarcode = locationBarcode;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getBoxBarcode() {
		return boxBarcode;
	}
	public void setBoxBarcode(String boxBarcode) {
		this.boxBarcode = boxBarcode;
	}
}

