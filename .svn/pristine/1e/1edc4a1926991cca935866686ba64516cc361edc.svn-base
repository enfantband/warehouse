package com.samsbeauty.warehouse.picking.report.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.picking.report.model.PickingJobTimelineReport;

public interface PickingJobTimelineReportRepositoryCustom {
	List<WarehouseEmployee> findAllPickersByStartTimeAndEndTime(Date startTime, Date endTime);
	Page<PickingJobTimelineReport> findAllForReport(Date startDate, Date endDate, Pageable pageable);
	PickingJobTimelineReport getReportForPicker(Date startDate, Date endDate, Long warehouseEmployeeId);
	List<PickingJobTimelineReport> findAllForReport(Date startDate, Date endDate);
}
