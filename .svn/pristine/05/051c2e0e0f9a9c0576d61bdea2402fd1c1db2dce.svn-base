package com.samsbeauty.warehouse.device.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="device")
public class Device {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="device_id")
	private Integer deviceId;
	
	@Column(name="device_number")
	private String deviceNumber;
	
	private String model;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_date")
	private Date regDate;
	
	private boolean activated;
	
	@OneToMany(mappedBy="device")
	private List<DeviceSetting> deviceSettings;

	public Integer getDeviceId() {
		return deviceId;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public String getModel() {
		return model;
	}

	public Date getRegDate() {
		return regDate;
	}

	public boolean isActivated() {
		return activated;
	}

	public Device(String deviceNumber, String model, Date regDate, boolean activated) {
		super();
		this.deviceNumber = deviceNumber;
		this.model = model;
		this.regDate = regDate;
		this.activated = activated;
	}
	
	public static class DeviceBuilder {
		private String deviceNumber;
		private String model;
		private Date regDate;
		private boolean activated;
		
		public DeviceBuilder() {
			
		}
		
		public DeviceBuilder setDeviceNumber(String deviceNumber) {
			this.deviceNumber = deviceNumber;
			return this;
		}
		
		public DeviceBuilder setModel(String model) {
			this.model = model;
			return this;
		}
		
		public DeviceBuilder setRegDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}
		
		public DeviceBuilder setActivated(boolean activated) {
			this.activated = activated;
			return this;
		}
		
		public Device createDevice() {
			return new Device(deviceNumber, model, regDate, activated);
		}
	}
}
