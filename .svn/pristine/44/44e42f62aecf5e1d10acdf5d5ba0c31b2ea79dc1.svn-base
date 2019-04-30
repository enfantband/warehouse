package com.samsbeauty.warehouse.printer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Printer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long printerId;
	
	@Column(name="server_name")
	private String serverName;
	
	@Column(name="printer_name")
	private String printerName;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="label_id")
	private PrinterLabel printerLabel;
	
	@Column
	private String port;
	
	@Column
	private boolean activated;
	
	public Printer() {
		
	}
	public Printer(String serverName, String printerName, PrinterLabel printerLabel, String port) {
		this.serverName = serverName;
		this.printerName = printerName;
		this.printerLabel = printerLabel;
		this.port = port;
		this.activated = true;
	}
	public void update(String serverName, String printerName, PrinterLabel printerLabel, String port) {
		this.serverName = serverName;
		this.printerName = printerName;
		this.printerLabel = printerLabel;
	}
	public void deactivate() {
		this.activated = false;
	}
	
	public Long getPrinterId() {
		return printerId;
	}
	
	public String getServerName() {
		return serverName;		
	}
	public String getPrinterName() {
		return printerName;
	}
	public PrinterLabel getPrinterLabel() {
		return printerLabel;
	}
	public String getPort() {
		return port;
	}
}
