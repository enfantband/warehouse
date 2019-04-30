package com.samsbeauty.warehouse.adjustment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.samsbeauty.warehouse.adjustment.model.converter.AdjustmentReasonConverter;
import com.samsbeauty.warehouse.adjustment.model.converter.AdjustmentStatusConverter;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.picking.model.PickingItem;

@Entity
@Table(name="adjustment_request")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AdjustmentRequest {
	public static final class Reason {
		public static final int WRONG_BARCODE_CODE = 0;
		public static final int MISSING_BARCODE_CODE = 1;
		public static final int NO_ITEM_CODE = 2;
		public static final int NO_ITEM_SECOND_CODE = 3;
		
		public static final String WRONG_BARCODE = "Wrong Barcode";
		public static final String MISSING_BARCODE = "Missing Barcode";
		public static final String NO_ITEM = "No Item";
		public static final String NO_ITEM_SECOND = "No Item 2nd";
	}
	
	public static final class Status {
		public static final int CREATED_CODE = 0;
		public static final int PROCESSED_CODE = 1;
		public static final int COMPLETED_CODE = 2;
		
		public static final String CREATED = "Created";
		public static final String PROCESSED = "Processed";
		public static final String COMPLETED = "Completed";
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="request_id")
	private Long requestId;
	
	
	@Column
	@Convert(converter=AdjustmentReasonConverter.class)
	private String reason;
	
	@Column
	@Convert(converter=AdjustmentStatusConverter.class)
	private String status;
	
	@OneToOne
	@JoinColumn(name="picking_item_id")
	private PickingItem pickingItem;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="reg_employee_id", referencedColumnName="warehouse_employee_id")
	private WarehouseEmployee regBy;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mod_employee_id", referencedColumnName="warehouse_employee_id")
	private WarehouseEmployee modBy;
	
	@Column(name="reg_date")
	private Date regDate;
	
	@Column(name="mod_date")
	private Date modDate;
	
	@Column
	private boolean activated;
	
	public Long getRequestId() {
		return requestId;
	}
	
	public String getReason() {
		return reason;
	}
	
	public String getStatus() {
		return status;
	}
	
	public WarehouseEmployee getRegBy() {
		return regBy;
	}
	
	public WarehouseEmployee getModBy() {
		return modBy;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public Date getModDate() {
		return modDate;
	}
	
	public PickingItem getPickingItem() {
		return pickingItem;
	}
	
	public void delete(WarehouseEmployee modBy, Date modDate) {
		this.activated = false;
		this.modBy = modBy;
		this.modDate = modDate;
	}

	public AdjustmentRequest() {
		
	}
	
	public AdjustmentRequest(Long requestId, PickingItem pickingItem, String reason,
			String status, WarehouseEmployee regBy, WarehouseEmployee modBy, Date regDate, Date modDate) {
		super();
		this.requestId = requestId;
		this.pickingItem = pickingItem;
		this.reason = reason;
		this.status = status;
		this.regBy = regBy;
		this.modBy = modBy;
		this.regDate = regDate;
		this.modDate = modDate;
		this.activated = true;
	}
	
	public static AdjustmentRequestBuilder builder(PickingItem pickingItem) {
		return new AdjustmentRequestBuilder(pickingItem);
	}
	
	public static class AdjustmentRequestBuilder {
		private Long requestId;
		private String reason;
		private String status;
		private PickingItem pickingItem;
		private WarehouseEmployee regBy;		
		private WarehouseEmployee modBy;		
		private Date regDate;		
		private Date modDate;
		
		public AdjustmentRequestBuilder copyOf(AdjustmentRequest ar) {
			this.requestId = ar.getRequestId();
			this.reason = ar.getReason();
			this.status = ar.getStatus();
			this.pickingItem = ar.getPickingItem();
			this.regBy = ar.getRegBy();
			this.modBy = ar.getModBy();
			this.regDate = ar.getRegDate();
			this.modDate = ar.getModDate();
			return this;
		}
		
		public AdjustmentRequestBuilder(PickingItem pickingItem) {
			this.pickingItem = pickingItem;
		}
		
		public AdjustmentRequestBuilder setRequestId(Long requestId) {
			this.requestId = requestId;
			return this;
		}
		
		public AdjustmentRequestBuilder setReason(String reason) {
			this.reason = reason;
			return this;
		}
		public AdjustmentRequestBuilder setStatus(String status) {
			this.status = status;
			return this;
		}
		public AdjustmentRequestBuilder setRegBy(WarehouseEmployee regBy) {
			this.regBy = regBy;
			return this;
		}
		public AdjustmentRequestBuilder setModBy(WarehouseEmployee modBy) {
			this.modBy = modBy;
			return this;
		}
		public AdjustmentRequestBuilder setRegDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}
		public AdjustmentRequestBuilder setModDate(Date modDate) {
			this.modDate = modDate;
			return this;
		}
		public AdjustmentRequest createAdjustmentRequest() {
			return new AdjustmentRequest(requestId, pickingItem, reason, status, regBy, modBy, regDate, modDate);
		}
	}
}
