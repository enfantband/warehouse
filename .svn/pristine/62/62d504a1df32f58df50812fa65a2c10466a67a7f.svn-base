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
@Table(name="packing_export")
public class PackingExport {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="export_id")
	private Long exportId;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="picking_job_group_id")
	private PickingJobGroup pickingJobGroup;
	
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

	public PackingExport() {}
	public PackingExport(Long exportId, PickingJobGroup pickingJobGroup, String folder, String filename, Long filesize,
			WarehouseEmployee regBy, Date regDate) {
		super();
		this.exportId = exportId;
		this.pickingJobGroup = pickingJobGroup;
		this.folder = folder;
		this.filename = filename;
		this.filesize = filesize;
		this.regBy = regBy;
		this.regDate = regDate;
	}

	public static PackingExportBuilder builder() {
		return new PackingExportBuilder();
	}

	public static class PackingExportBuilder {
		private Long exportId;
		private PickingJobGroup pickingJobGroup;
		private String folder;
		private String filename;
		private Long filesize;
		private WarehouseEmployee regBy;
		private Date regDate;
		
		public PackingExportBuilder setExportId(Long exportId) {
			this.exportId = exportId;
			return this;
		}
		public PackingExportBuilder setPickingGroup(PickingJobGroup pickingJobGroup) {
			this.pickingJobGroup = pickingJobGroup;
			return this;
		}
		public PackingExportBuilder setFolder(String folder) {
			this.folder = folder;
			return this;
		}
		public PackingExportBuilder setFilename(String filename) {
			this.filename = filename;
			return this;
		}
		public PackingExportBuilder setFilesize(Long filesize) {
			this.filesize = filesize;
			return this;
		}
		public PackingExportBuilder setRegBy(WarehouseEmployee regBy) {
			this.regBy = regBy;
			return this;
		}
		public PackingExportBuilder setRegDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}
		public PackingExport createPackingExport() {
			return new PackingExport(exportId, pickingJobGroup, folder, filename, filesize, regBy, regDate);
		}
		
	}
}
