package com.samsbeauty.warehouse.picking.report.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.picking.report.model.AveragePickingSummary;
import com.samsbeauty.warehouse.picking.report.model.PickingJobTimelineReport;

public interface PickingJobTimelineReportService {
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	Page<PickingJobTimelineReport> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	Page<PickingJobTimelineReport> getAll(Date startDate, Date endDate, Integer pageNumber, Integer pageSize, Sort sort);
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	PickingJobTimelineReport getReportForPicker(Date startDate, Date endDate, Long warehouseEmployeeId);
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	AveragePickingSummary getReportForAvg(Date startDate, Date endDate);
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	List<WarehouseEmployee> getAllPickersByStartTimeAndEndTime(Date startTime, Date endTime);
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	PickingJobTimelineReport getOne(Long reportId);
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	PickingJobTimelineReport save(PickingJobTimelineReport pickingJobTimelineReport);
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	void delete(Long reportId);
}