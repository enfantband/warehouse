package com.samsbeauty.warehouse.picking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.samsbeauty.warehouse.picking.model.converter.PickingJobStatusConverter;
import com.samsbeauty.warehouse.picking.report.model.PickingJobTimelineReport;

@Table(name="picking_job")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PickingJob implements Serializable {
	public static class PickingJobStatus {
		public static final int READY_CODE = 1;
		public static final int ASSIGNED_CODE = 2;
		public static final int STARTED_CODE = 4;
		public static final int PAUSED_CODE = 3;
		public static final int FINISHED_CODE = 5;
		public static final int PASSED_CODE = 6;		
		
		public static final String READY = "Ready";
		public static final String ASSIGNED = "Assigned";
		public static final String STARTED = "Started";
		public static final String PAUSED = "Paused";
		public static final String FINISHED = "Finished";
		public static final String PASSED = "Handed Over";
	}
	
	private static final long serialVersionUID = -8267647510185534477L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long pickingJobId;
	
	@OneToMany(mappedBy="pickingJob", fetch=FetchType.LAZY)
	@Where(clause="activated = 1")
	@OrderBy("event_time ASC")
	private List<PickingJobTimeline> pickingJobTimelines;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="picking_group_id")
	private PickingGroup pickingGroup;
	
	@ManyToOne
	@JoinColumn(name="picking_job_group_id")
	@JsonIgnore
	private PickingJobGroup pickingJobGroup;
	
	@Transient
	private Integer pickingSet;
	
	// This is flag for later picking
	private Boolean saved;
	
	@OneToMany(mappedBy="pickingJob")
	@JsonIgnore
	private List<PickingJobTimelineReport> pickingJobTimelineReports;
	
	@OneToOne(mappedBy="pickingJob", fetch=FetchType.EAGER)
	private PickingJobExport pickingJobExport;
	
	@OneToOne(mappedBy="pickingJob", fetch=FetchType.EAGER)
	private MissingExport missingExport;
	
	@Column(name="picking_status")
	@Convert(converter = PickingJobStatusConverter.class)
	private String pickingStatus;
	
	@OneToMany(mappedBy="pickingJob", fetch=FetchType.LAZY)
	private List<PickingItemInfo> pickingItemInfos = new ArrayList<>();
	
	@OneToMany(mappedBy="pickingJob", fetch=FetchType.LAZY)
	private List<PickingItem> pickingItems = new ArrayList<>();
	
	@Transient
	private Long numberOfItems = 0L;
	
	@Transient
	private Long numberOfPickedItems = 0L;
	
	public List<PickingItemInfo> getPickingItemInfos() { 
		return pickingItemInfos;
	}

	public Long getNumberOfItems() {
		return numberOfItems;
	}
	public Long getNumberOfPickedItems() {
		return numberOfPickedItems;
	}
	
	public Boolean isSaved() {
		return saved;
	}
	
	public PickingJob() {
	}
	
	public void setPickingSet(Integer pickingSet) {
		this.pickingSet = pickingSet;
	}
	
	public Integer getPickingSet() {
		return this.pickingSet;
	}

	public PickingJob(Long pickingJobId, PickingJobGroup pickingJobGroup, PickingGroup pickingGroup, String pickingStatus, Boolean saved, Long numberOfItems, Long numberOfPickedItems) {
		this.pickingJobId = pickingJobId;
		this.pickingJobGroup = pickingJobGroup;
		this.pickingGroup = pickingGroup;
		this.pickingStatus = pickingStatus;
		this.numberOfItems = numberOfItems;
		if(saved == null) this.saved = false;
		else this.saved = saved;
		this.numberOfPickedItems = numberOfPickedItems == null ? 0L : numberOfPickedItems;
	}
	public PickingJob(Long pickingJobId, PickingJobGroup pickingJobGroup, PickingGroup pickingGroup, String pickingStatus, Boolean saved) {
		this.pickingJobId = pickingJobId;
		this.pickingJobGroup = pickingJobGroup;
		this.pickingGroup = pickingGroup;
		this.pickingStatus = pickingStatus;
		this.saved = saved;
	}
	
	public void updateStatus(String status) {
		this.pickingStatus = status;
	}
	
	public Long getPickingJobId() {
		return this.pickingJobId;
	}
	public String getPickingStatus() {
		return this.pickingStatus;
	}
	public PickingJobExport getPickingJobExport() {
		return pickingJobExport;
	}
	public MissingExport getMissingExport() {
		return missingExport;
	}
	public PickingGroup getPickingGroup() {
		return this.pickingGroup;
	}
	public List<PickingJobTimeline> getPickingJobTimelines() {
		return pickingJobTimelines;
	}
	public List<PickingItem> getPickingItems() {
		// picked item
		return pickingItems;
	}	
	public List<PickingJobTimelineReport> getPickingJobTimelineReports() {
		return pickingJobTimelineReports;
	}
	
	public static PickingJobBuilder builder(PickingJobGroup pickingJobGroup, PickingGroup pickingGroup) {
		return new PickingJobBuilder(pickingJobGroup, pickingGroup);
	}
	
	public static class PickingJobBuilder {
		private Long pickingJobId;
		private PickingJobGroup pickingJobGroup;
		private PickingGroup pickingGroup;
		private String pickingStatus;
		private Boolean saved;
		public PickingJobBuilder(PickingJobGroup pickingJobGroup, PickingGroup pickingGroup) {
			this.pickingJobGroup = pickingJobGroup;
			this.pickingGroup = pickingGroup;
		}
		public PickingJobBuilder setPickingJobId(Long pickingJobId) {
			this.pickingJobId = pickingJobId;
			return this;
		}
		public PickingJobBuilder setPickingStatus(String pickingStatus) {
			this.pickingStatus = pickingStatus;
			return this;
		}
		public PickingJobBuilder setSaved(Boolean saved) {
			this.saved = saved;
			return this;
		}
		
		public PickingJob createPickingJob() {
			return new PickingJob(pickingJobId, pickingJobGroup, pickingGroup, pickingStatus, saved);
		}
	}
}
