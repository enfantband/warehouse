package com.samsbeauty.warehouse.picking.report.model;

import java.io.Serializable;

import javax.persistence.Column;
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
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.picking.model.PickingJob;
import com.samsbeauty.warehouse.picking.model.PickingJobTimeline;

@Entity
@Table(name="picking_job_timeline_report")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PickingJobTimelineReport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7216881340711879500L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="report_id")
	private Long reportId; 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="picking_job_id")
	private PickingJob pickingJob;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="start_timeline_id")
	private PickingJobTimeline startTimeline;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="end_timeline_id")
	private PickingJobTimeline endTimeline;
	
	@Column(name="total_picked")
	private Integer totalPicked;
	
	
	@Column(name="total_picked_unique")
	private Integer totalPickedUnique;
	
	@Column(name="total_missed")
	private Integer totalMissed;
	
	@Column(name="total_saved")
	private Integer totalSaved;
	
	@Column(name="total_missed_unique")
	private Integer totalMissedUnique;
	
	@Column(name="total_time_spent")
	private Long totalTimeSpent;
	
	@Column(name="total_picked_without_scan")
	private Integer totalPickedWithoutScan;
	
	@Column(name="total_picked_without_scan_unique")
	private Integer totalPickedWithoutScanUnique;
	
	@Column(name="total_wrong_location")
	private Integer totalWrongLocation;
	
	@Column(name="total_wrong_location_unique")
	private Integer totalWrongLocationUnique;
	
	@Column(name="total_saved_unique")
	private Integer totalSavedUnique;
	
	@ManyToOne
	@JoinColumn(name="warehouse_employee_id")
	private WarehouseEmployee warehouseEmployee;
	
	@Column
	private boolean activated;
	
	public Long getReportId() {
		return this.reportId;
	}

	public WarehouseEmployee getWarehouseEmployee() {
		return warehouseEmployee;
	}
	
	public PickingJobTimelineReport() {
		
	}

	public PickingJobTimelineReport(
			PickingJob pickingJob, 
			WarehouseEmployee warehouseEmployee, 
			PickingJobTimeline startTimeline, 
			PickingJobTimeline endTimeline,
			Integer totalMissed, 
			Integer totalMissedUnique, 
			Integer totalPicked, 
			Integer totalPickedUnique,  
			Integer totalPickedWithoutScan,
			Integer totalPickedWithoutScanUnique,
			Integer totalWrongLocation,
			Integer totalWrongLocationUnique,
			Integer totalSaved,
			Integer totalSavedUnique,
			Long totalTimeSpent,
			boolean activated) {
		super();
		this.pickingJob = pickingJob;
		this.warehouseEmployee = warehouseEmployee;
		this.startTimeline = startTimeline;
		this.endTimeline = endTimeline;
		this.totalMissed = totalMissed;
		this.totalMissedUnique = totalMissedUnique;
		this.totalPicked = totalPicked;
		this.totalPickedUnique = totalPickedUnique;
		this.totalTimeSpent = totalTimeSpent;
		this.totalPickedWithoutScan = totalPickedWithoutScan;
		this.totalPickedWithoutScanUnique = totalPickedWithoutScanUnique;
		this.totalWrongLocation = totalWrongLocation;
		this.totalWrongLocationUnique = totalWrongLocationUnique;

		this.totalSaved = totalSaved.intValue();
		this.totalSavedUnique = totalSavedUnique.intValue();
		this.activated = activated;
	}
	public PickingJobTimelineReport(
			PickingJob pickingJob, 
			WarehouseEmployee warehouseEmployee, 
			PickingJobTimeline startTimeline, 
			PickingJobTimeline endTimeline,
			Long totalMissed, 
			Long totalMissedUnique, 
			Long totalPicked,
			Long totalPickedUnique,  
			Long totalPickedWithoutScan,
			Long totalPickedWithoutScanUnique,
			Long totalWrongLocation,
			Long totalWrongLocationUnique,
			Long totalSaved,
			Long totalSavedUnique,
			Long totalTimeSpent,
			boolean activated) {
		super();
		this.pickingJob = pickingJob;
		this.warehouseEmployee = warehouseEmployee;
		this.startTimeline = startTimeline;
		this.endTimeline = endTimeline;
		this.totalMissed = totalMissed.intValue();
		this.totalMissedUnique = totalMissedUnique.intValue();
		this.totalPicked = totalPicked.intValue();
		
		this.totalPickedUnique = totalPickedUnique.intValue();
		this.totalPickedWithoutScan = totalPickedWithoutScan.intValue();
		this.totalPickedWithoutScanUnique = totalPickedWithoutScanUnique.intValue();
		this.totalWrongLocation = totalWrongLocation.intValue();
		this.totalWrongLocationUnique = totalWrongLocationUnique.intValue();
		
		this.totalSaved = totalSaved.intValue();
		this.totalSavedUnique = totalSavedUnique.intValue();
		this.totalTimeSpent = totalTimeSpent;
		this.activated = activated;
	}

	public void deactivate() {
		this.activated = false;
	}
	
	public Integer getTotalPicked() {
		return totalPicked;
	}

	public Integer getTotalPickedUnique() {
		return totalPickedUnique;
	}

	public Integer getTotalPickedWithoutScan() {
		return totalPickedWithoutScan;
	}

	public Integer getTotalPickedWithoutScanUnique() {
		return totalPickedWithoutScanUnique;
	}

	public Integer getTotalWrongLocation() {
		return totalWrongLocation;
	}

	public Integer getTotalWrongLocationUnique() {
		return totalWrongLocationUnique;
	}

	public Integer getTotalMissed() {
		return totalMissed;
	}

	public Integer getTotalMissedUnique() {
		return totalMissedUnique;
	}

	public Long getTotalTimeSpent() {
		return totalTimeSpent;
	}
	
	public PickingJobTimeline getStartTimeline() {
		return startTimeline;
	}

	public static PickingJobTimelineReportBuilder builder(PickingJob pickingJob, WarehouseEmployee warehouseEmployee, PickingJobTimeline startTimeline, PickingJobTimeline endTimeline) {
		return new PickingJobTimelineReportBuilder(pickingJob, warehouseEmployee, startTimeline, endTimeline);
	}
	
	public static class PickingJobTimelineReportBuilder {
		private PickingJob pickingJob;
		private PickingJobTimeline startTimeline;
		private PickingJobTimeline endTimeline;
		private WarehouseEmployee warehouseEmployee;
		private Integer totalPicked = 0;
		private Integer totalPickedUnique = 0;
		private Integer totalMissed = 0;
		private Integer totalMissedUnique = 0;
		private Integer totalPickedWithoutScan = 0;
		private Integer totalPickedWithoutScanUnique = 0;
		private Integer totalWrongLocation = 0;
		private Integer totalWrongLocationUnique = 0;
		private Integer totalSaved = 0;
		private Integer totalSavedUnique = 0;
		
		private Long totalTimeSpent = 0L;
		
		public PickingJobTimelineReportBuilder(PickingJob pickingJob, WarehouseEmployee warehouseEmployee, PickingJobTimeline startTimeline, PickingJobTimeline endTimeline) {
			this.pickingJob = pickingJob;
			this.warehouseEmployee = warehouseEmployee;
			this.startTimeline = startTimeline;
			this.endTimeline = endTimeline;
		}
		
		public PickingJobTimelineReportBuilder setTotalPickedWithoutScan(Integer totalPickedWithoutScan) {
			this.totalPickedWithoutScan = totalPickedWithoutScan;
			return this;
		}
		
		public PickingJobTimelineReportBuilder setTotalPickedWithoutScanUnique(Integer totalPickedWithoutScanUnique) {
			this.totalPickedWithoutScanUnique = totalPickedWithoutScanUnique;
			return this;
		}
		
		public PickingJobTimelineReportBuilder setTotalWrongLocation(Integer totalWrongLocation) {
			this.totalWrongLocation = totalWrongLocation;
			return this;
		}
		
		public PickingJobTimelineReportBuilder setTotalWrongLocationUnique(Integer totalWrongLocationUnique) {
			this.totalWrongLocationUnique = totalWrongLocationUnique;
			return this;
		}
		
		public PickingJobTimelineReportBuilder setTotalPicked(Integer totalPicked) {
			this.totalPicked = totalPicked;
			return this;
		}
		
		public PickingJobTimelineReportBuilder setTotalPickedUnique(Integer totalPickedUnique) {
			this.totalPickedUnique = totalPickedUnique;
			return this;
		}
		
		public PickingJobTimelineReportBuilder setTotalMissed(Integer totalMissed) {
			this.totalMissed = totalMissed;
			return this;
		}
		
		public PickingJobTimelineReportBuilder setTotalMissedUnique(Integer totalMissedUnique) {
			this.totalMissedUnique = totalMissedUnique;
			return this;
		}
		
		public PickingJobTimelineReportBuilder setTotalTimeSpent(Long totalTimeSpent) {
			this.totalTimeSpent = totalTimeSpent;
			return this;
		}
		
		public PickingJobTimelineReportBuilder setTotalSaved(Integer totalSaved) {
			this.totalSaved = totalSaved;
			return this;
		}
		
		public PickingJobTimelineReportBuilder setTotalSavedUnique(Integer totalSavedUnique) {
			this.totalSavedUnique = totalSavedUnique;
			return this;
		}
		
		public PickingJobTimelineReport createPickingJobTimelineReport() {
			return new PickingJobTimelineReport(pickingJob, warehouseEmployee, startTimeline, endTimeline, totalMissed, totalMissedUnique, totalPicked, totalPickedUnique, totalPickedWithoutScan,
					totalPickedWithoutScanUnique,
					totalWrongLocation,
					totalWrongLocationUnique, totalSaved, totalSavedUnique, totalTimeSpent, true);
		}
	}
	
}
