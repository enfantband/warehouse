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
@Table(name="missing_export")
public class MissingExport {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="export_id")
	private Long exportId;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="job_id")
	private PickingJob pickingJob;
	

	@Column
	private String folder;
	
	@Column
	private String filename;
	
	@Column
	private Long filesize;
	
	@ManyToOne
	@JoinColumn(name="reg_employee_id")
	private WarehouseEmployee regBy;
	
	@Column(name="reg_date")
	private Date regDate;
	
	
	
	public Long getExportId() {
		return exportId;
	}
	
	public String getFolder() {
		return folder;
	}

	public String getFilename() {
		return filename;
	}

	public Long getFilesize() {
		return filesize;
	}

	public WarehouseEmployee getRegBy() {
		return regBy;
	}

	public Date getRegDate() {
		return regDate;
	}
	public MissingExport() {}
	
	public MissingExport(Long exportId, PickingJob pickingJob, String folder, String filename, Long filesize,
			WarehouseEmployee regBy, Date regDate) {
		super();
		this.exportId = exportId;
		this.pickingJob = pickingJob;
		this.folder = folder;
		this.filename = filename;
		this.filesize = filesize;
		this.regBy = regBy;
		this.regDate = regDate;
	}
	
	public static MissingExportBuilder builder() {
		return new MissingExportBuilder();
	}

	public static class MissingExportBuilder {
		private Long exportId;
		private PickingJob pickingJob;
		private String folder;
		private String filename;
		private Long filesize;
		private WarehouseEmployee regBy;
		private Date regDate;
		
		public MissingExportBuilder setExportId(Long exportId) {
			this.exportId = exportId;
			return this;
		}
		public MissingExportBuilder setPickingJob(PickingJob pickingJob) {
			this.pickingJob = pickingJob;
			return this;
		}
		public MissingExportBuilder setFolder(String folder) {
			this.folder = folder;
			return this;
		}
		public MissingExportBuilder setFilename(String filename) {
			this.filename = filename;
			return this;
		}
		public MissingExportBuilder setFilesize(Long filesize) {
			this.filesize = filesize;
			return this;
		}
		public MissingExportBuilder setRegBy(WarehouseEmployee regBy) {
			this.regBy = regBy;
			return this;
		}
		public MissingExportBuilder setRegDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}
		public MissingExport createMissingExport() {
			return new MissingExport(exportId, pickingJob, folder, filename, filesize, regBy, regDate);
		}
		
	}
}
