package com.samsbeauty.warehouse.picking.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.samsbeauty.warehouse.Views;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.picking.model.PickingJob.PickingJobStatus;
import com.samsbeauty.warehouse.picking.model.converter.PickingJobStatusConverter;
import com.samsbeauty.warehouse.picking.report.model.PickingJobTimelineReport;

@Entity
@Table(name="picking_job_timeline")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PickingJobTimeline {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="timeline_id")
	private Long timelineId;
	
	@ManyToOne
	@JoinColumn(name="warehouse_employee_id")
	private WarehouseEmployee warehouseEmployee;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="event_time")
	private Date eventTime;
	
	@Column(name="picking_job_status_from")
	@Convert(converter = PickingJobStatusConverter.class)
	private String pickingJobStatusFrom;
	
	@Column(name="picking_job_status_to")
	@Convert(converter = PickingJobStatusConverter.class)
	private String pickingJobStatusTo;
	
	@ManyToOne
	@JoinColumn(name="picking_job_id")
	@JsonIgnore
	private PickingJob pickingJob;
	
	@Column
	private boolean activated;
	
	@OneToOne(mappedBy="endTimeline")
	private PickingJobTimelineReport pickingJobTimelineReport;

	@OneToMany(mappedBy="pickingJobTimeline")
	private List<PickingItem> pickingItems = new ArrayList<>();
	
	public void deactivate() {
		activated = false;
	}
	
	public boolean isAssigned() {
		if(pickingJobStatusTo.equals(PickingJobStatus.ASSIGNED)) {
			return true;
		}
		return false;
	}
	public PickingJobTimeline() {
	}

	public PickingJobTimeline(WarehouseEmployee warehouseEmployee, Date eventTime, String pickingJobStatusFrom,
			String pickingJobStatusTo, PickingJob pickingJob, boolean activated) {
		super();
		this.warehouseEmployee = warehouseEmployee;
		this.eventTime = eventTime;
		this.pickingJobStatusFrom = pickingJobStatusFrom;
		this.pickingJobStatusTo = pickingJobStatusTo;
		this.pickingJob = pickingJob;
		this.activated = activated;
	}
	
	public static PickingJobTimelineBuilder builder(PickingJob pickingJob) {
		return new PickingJobTimelineBuilder(pickingJob);
	}
	

	public PickingJobTimelineReport getPickingJobTimelineReport() {
		return pickingJobTimelineReport;
	}
	public static class PickingJobTimelineBuilder {
		private WarehouseEmployee warehouseEmployee;
		private Date eventTime;
		private String pickingJobStatusFrom;
		private String pickingJobStatusTo;
		private PickingJob pickingJob;
		public PickingJobTimelineBuilder(PickingJob pickingJob) {
			this.pickingJob = pickingJob;
		}
		public PickingJobTimelineBuilder setWarehouseEmployee(WarehouseEmployee warehouseEmployee) {
			this.warehouseEmployee = warehouseEmployee;
			return this;
		}
		
		public PickingJobTimelineBuilder setEventTime(Date eventTime) {
			this.eventTime = eventTime;
			return this;
		}
		
		public PickingJobTimelineBuilder setPickingJobStatusFrom(String pickingJobStatusFrom) {
			this.pickingJobStatusFrom = pickingJobStatusFrom;
			return this;
		}
		
		public PickingJobTimelineBuilder setPickingJobStatusTo(String pickingJobStatusTo) {
			this.pickingJobStatusTo = pickingJobStatusTo;
			return this;
		}
		public PickingJobTimeline createPickingJobTimeline() {
			return new PickingJobTimeline(warehouseEmployee, eventTime, pickingJobStatusFrom, pickingJobStatusTo, pickingJob, true);
		}
	}


	public Long getTimelineId() {
		return timelineId;
	}

	public WarehouseEmployee getWarehouseEmployee() {
		return warehouseEmployee;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public String getPickingJobStatusFrom() {
		return pickingJobStatusFrom;
	}

	public String getPickingJobStatusTo() {
		return pickingJobStatusTo;
	}

	public PickingJob getPickingJob() {
		return pickingJob;
	}

	public List<PickingItem> getPickingItems() {
		return pickingItems;
	}
}
