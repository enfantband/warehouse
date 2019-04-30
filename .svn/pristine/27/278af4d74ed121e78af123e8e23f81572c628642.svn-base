package com.samsbeauty.warehouse.printer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="printer_label")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PrinterLabel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="label_id")
	private Long labelId;
	
	@Column(name="label_name")
	private String labelName;
	
	@Column(name="label_type")
	private String labelType;
	
	@Column
	private Boolean activated;
	
	public PrinterLabel() {
		
	}
	
	public PrinterLabel(String labelName, String labelType) {
		this.labelName = labelName;
		this.labelType = labelType;
		this.activated = true;
	}
	
	public void update(String labelName, String labelType) {
		this.labelName = labelName;
		this.labelType = labelType;
	}
	public void deactivate() {
		this.activated = false;
	}
	
	public Long getLabelId() {
		return labelId;
	}
	
	public String getLabelName() {
		return labelName;
	}
	
	public String getLabelType() {
		return labelType;
	}
}
