package com.samsbeauty.warehouse.picking.report.model;

import java.util.List;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;

public class PickingJobTimelineReportSummary {
	private WarehouseEmployee picker;
	private List<Integer> picked;
	private List<Integer> missed;
	private List<Integer> average;
}
