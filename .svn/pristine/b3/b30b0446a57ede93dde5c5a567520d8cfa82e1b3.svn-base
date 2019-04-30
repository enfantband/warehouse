package com.samsbeauty.warehouse.device.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="device_setting")
@Entity
public class DeviceSetting {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="setting_id")
	private Long settingId;
	
	private String key;
	private String value;
	
	@ManyToOne
	@JoinColumn(name="device_id")
	private Device device;
	
	public DeviceSetting() {
		
	}
	
	public DeviceSetting(Device device) {
		this.device = device;
	}
	
	public void set(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
}
