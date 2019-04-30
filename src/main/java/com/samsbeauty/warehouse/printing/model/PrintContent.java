package com.samsbeauty.warehouse.printing.model;

import java.util.List;

public class PrintContent {
	private List<PrintItem> printItems;
	private String printer;
	private String label;
	
	public List<PrintItem> getPrintItems() {
		return printItems;
	}
	public void setPrintItems(List<PrintItem> printItems) {
		this.printItems = printItems;
	}
	public String getPrinter() {
		return printer;
	}
	public void setPrinter(String printer) {
		this.printer = printer;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
