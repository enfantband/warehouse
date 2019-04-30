package com.samsbeauty.warehouse.picking.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import com.fasterxml.jackson.annotation.JsonView;
import com.samsbeauty.warehouse.Views;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;

@Entity
@Table(name="picking_job_group")
public class PickingJobGroup {
	
	public class PickingJobGroupStatus {
		public static final int NOT_FINISHED = 0;
		public static final int FINISHED = 1;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="picking_job_group_id")
	private Long pickingJobGroupId;
	

	@OneToMany(mappedBy="pickingJobGroup", fetch=FetchType.LAZY)
	private List<PickingJob> pickingJobs = new ArrayList<PickingJob>();
	
	@Column(name="picking_set")
	@JsonView({Views.Picker.class, Views.Admin.class})
	private Integer pickingSet;
	
	@Column(name="group_status")
	private Integer groupStatus;
	
	@Column(name="total_items")
	private Integer totalItems;
	
	@OneToOne(mappedBy="pickingJobGroup", fetch=FetchType.EAGER)
	private PackingExport packingExport;
	
	@ManyToOne
	@JoinColumn(name="reg_employee_id")
	private WarehouseEmployee regBy;
	
	@Column(name="start_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	@Column(name="end_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	@Column(name="reg_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;
	
	@Column
	private boolean activated;
	
	public PickingJobGroup () {
		
	}
	
	public PickingJobGroup(Integer pickingSet,
			Integer groupStatus, Integer totalItems, WarehouseEmployee regBy, Date startTime, Date endTime, Date regDate, boolean activated) {
		super();
		this.pickingSet = pickingSet;
		this.groupStatus = groupStatus;
		this.totalItems = totalItems;
		this.regBy = regBy;
		this.startTime = startTime;
		this.endTime = endTime;
		this.regDate = regDate; 
		this.activated = activated;
	}
	public void deactivate() {
		this.activated = false;
	}
	public List<PickingJob> getPickingJobs() {
		return pickingJobs;
	}
	
	public Long getPickingJobGroupId() {
		return pickingJobGroupId;
	}

	public Integer getPickingSet() {
		return pickingSet;
	}

	public Integer getGroupStatus() {
		return groupStatus;
	}

	public Integer getTotalItems() {
		return totalItems;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}
	
	public PackingExport getPackingExport() {
		return packingExport;
	}

	public static PickingJobGroupBuilder builder() {
		return new PickingJobGroupBuilder();
	}
	
	public static class PickingJobGroupBuilder {
		private Integer pickingSet;		
		private Integer groupStatus;		
		private Integer totalItems;			
		private WarehouseEmployee regBy;		
		private Date startTime;		
		private Date endTime;
		private Date regDate;
		
		public PickingJobGroupBuilder() {
			
		}
		
		public PickingJobGroupBuilder setPickingSet(Integer pickingSet) {
			this.pickingSet = pickingSet;
			return this;
		}

		public PickingJobGroupBuilder setGroupStatus(Integer groupStatus) {
			this.groupStatus = groupStatus;
			return this;
		}

		public PickingJobGroupBuilder setTotalItems(Integer totalItems) {
			this.totalItems = totalItems;
			return this;
		}

		public PickingJobGroupBuilder setRegBy(WarehouseEmployee regBy) {
			this.regBy = regBy;
			return this;
		}

		public PickingJobGroupBuilder setStartTime(Date startTime) {
			this.startTime = startTime;
			return this;
		}

		public PickingJobGroupBuilder setEndTime(Date endTime) {
			this.endTime = endTime;
			return this;
		}		
		public PickingJobGroupBuilder setRegDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}
		public PickingJobGroup createPickingJobGroup() {
			return new PickingJobGroup(pickingSet, groupStatus, totalItems, regBy, startTime, endTime, regDate, true);
		}
		
	}
}
