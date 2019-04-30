package com.samsbeauty.warehouse.picking.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.model.WarehouseLevel;
import com.samsbeauty.warehouse.picking.model.converter.PickingItemStatusConverter;

@Entity
@Table(name="picking_item")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PickingItem implements Serializable {
	private static final long serialVersionUID = 5509663859502115986L;

	public static class PickingItemStatus {
		public static final int READY_CODE = 0;
		public static final int PICKED_CODE = 1;
		public static final int PICKED_WITHOUT_SCAN_CODE = 2;
		public static final int MISSED_CODE = 3;
		public static final int WRONG_LOCATION_CODE = 4;
		public static final int SAVE_CODE = 5;
		
		public static final String READY = "Ready";
		public static final String PICKED = "Picked";
		public static final String PICKED_WITHOUT_SCAN = "No Scan Picked";
		public static final String MISSED = "Missed";
		public static final String WRONG_LOCATION = "Wrong Location";
		public static final String SAVE = "Save for Later";
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="picking_item_id")
	private Long pickingItemId;
	
	@ManyToOne
	@JoinColumn(name="warehouse_employee_id")
	private WarehouseEmployee picker;
	
	@Column(name="generated_barcode")
	private String generatedBarcode;
	
	@ManyToOne
	@JoinColumn(name="timeline_id")
	private PickingJobTimeline pickingJobTimeline;
	
	@ManyToOne
	@JoinColumn(name="picking_job_id")
	private PickingJob pickingJob;
	
	@Column
	@Convert(converter=PickingItemStatusConverter.class)
	private String status;
	
	@ManyToOne
	@JoinColumn(name="level_id")
	private WarehouseLevel warehouseLevel;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_date")
	private Date regDate;
	
	@Column
	private Integer quantity = 1;
	
	@Column
	private boolean activated;
	
	public Long getPickingItemId() { 
		return pickingItemId;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public String getPickingItemStatus() {
		return status;
	}
	public String getLocationName() {
		if(warehouseLevel != null) {
			return warehouseLevel.getLocationName();
		} else {
			return "";
		}
	}
	public String getWarehouseCode() {
		if(warehouseLevel != null) {
			return warehouseLevel.getWarehouseCode();
		} else {
			return "";
		}
	}
	public String getGeneratedBarcode() { 
		return generatedBarcode;
	}
	public WarehouseLevel getWarehouseLevel() { 
		return warehouseLevel;
	}
	public PickingItem() {}
	public PickingItem(PickingJobTimeline pickingJobTimeline, WarehouseLevel warehouseLevel, Date regDate,
			String generatedBarcode, String status, boolean activated, PickingJob pickingJob, WarehouseEmployee picker, Integer quantity) {
		super();
		this.pickingJobTimeline = pickingJobTimeline;
		this.warehouseLevel = warehouseLevel;
		this.regDate = regDate;
		this.generatedBarcode = generatedBarcode;
		this.status = status;
		this.activated = activated;
		this.pickingJob = pickingJob;
		this.picker = picker;
		this.quantity = quantity;
	}
	
	public static PickingItemBuilder builder(PickingJobTimeline pickingJobTimeline, WarehouseLevel warehouseLevel) {
		return new PickingItemBuilder(pickingJobTimeline, warehouseLevel);
	}
	
	public static class PickingItemBuilder {
		private PickingJobTimeline pickingJobTimeline;
		private WarehouseLevel warehouseLevel;
		private String generatedBarcode;
		private Date regDate;
		private String status;
		private PickingJob pickingJob;
		private WarehouseEmployee picker;
		private Integer quantity = 1;
		
		public PickingItemBuilder(PickingJobTimeline pickingJobTimeline, WarehouseLevel warehouseLevel) {
			this.pickingJobTimeline = pickingJobTimeline;
			this.warehouseLevel = warehouseLevel;
		}
		
		public PickingItemBuilder setGeneratedBarcode(String generatedBarcode) {
			this.generatedBarcode = generatedBarcode;
			return this;
		}
		
		public PickingItemBuilder setStatus(String status) {
			this.status = status;
			return this;
		}
		public PickingItemBuilder setRegDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}
		public PickingItemBuilder setPickingJob(PickingJob pickingJob) {
			this.pickingJob = pickingJob;
			return this;
		}
		public PickingItemBuilder setPicker(WarehouseEmployee picker) {
			this.picker = picker;
			return this;
		}
		public PickingItemBuilder setQuantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}
		
		public PickingItem createPickingItem() {
			return new PickingItem( pickingJobTimeline, warehouseLevel, regDate, generatedBarcode, status, true, pickingJob, picker, quantity);
		}

		
	}
}
