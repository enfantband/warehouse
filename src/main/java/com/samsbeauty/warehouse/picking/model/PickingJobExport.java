package com.samsbeauty.warehouse.picking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;

@Entity
@Table(name="picking_job_export")
public class PickingJobExport {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long exportId;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="job_id")
	private PickingJob pickingJob;
	
	@Column
	private String filename;
	
	@Column
	private Long filesize;
	
	@ManyToOne
	@JoinColumn(name="reg_employee_id")
	private WarehouseEmployee regBy;
	
	@Column(name="reg_date")
	private Date regDate;
	
	@Column
	private String folder;

	public PickingJobExport() {}
	public PickingJobExport(Long exportId, PickingJob pickingJob, String filename, String folder, Long filesize, WarehouseEmployee regBy, Date regDate ) {
		super();
		this.exportId = exportId;
		this.pickingJob = pickingJob;
		this.filename = filename;
		this.folder = folder;
		this.filesize = filesize;
		this.regBy = regBy;
		this.regDate = regDate;
	}

	public Long getExportId() {
		return exportId;
	}

	public Long getFilesize() {
		return filesize;
	}

	public WarehouseEmployee getRegBy() {
		return regBy;
	}
	
	public String getFolder() {
		return folder;
	}
	
	public String getFilename() { 
		return filename;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public static PickingJobExportBuilder builder(PickingJob pickingJob) {
		return new PickingJobExportBuilder(pickingJob);
	}
	
	public static class PickingJobExportBuilder {
		private Long exportId;
		private PickingJob pickingJob;
		private String filename;
		private String folder;
		private Long filesize;
		private WarehouseEmployee regBy;
		private Date regDate;
		
		public PickingJobExportBuilder(PickingJob pickingJob) {
			this.pickingJob = pickingJob;
		}
		
		public PickingJobExportBuilder setExportId(Long exportId) {
			this.exportId = exportId;
			return this;
		}
		
		public PickingJobExportBuilder setFilename(String filename) {
			this.filename = filename;
			return this;
		}
		
		public PickingJobExportBuilder setFolder(String folder) {
			this.folder = folder;
			return this;
		}
		
		public PickingJobExportBuilder setFilesize(Long filesize){
			this.filesize = filesize;
			return this;
		}
		
		public PickingJobExportBuilder setRegBy(WarehouseEmployee regBy) {
			this.regBy = regBy;
			return this;
		}
		
		public PickingJobExportBuilder setRegDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}
		
		public PickingJobExport createPickingJobExport() {
			return new PickingJobExport(exportId, pickingJob, filename, folder, filesize, regBy, regDate);
		}
		
	}
}
