package com.samsbeauty.warehouse.picking.report.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.picking.report.model.AveragePickingSummary;
import com.samsbeauty.warehouse.picking.report.model.PickingJobTimelineReport;
import com.samsbeauty.warehouse.picking.report.repository.PickingJobTimelineReportRepository;

@Service
@Transactional
public class PickingJobTimelineReportServiceImpl implements PickingJobTimelineReportService {
	@Autowired
	private PickingJobTimelineReportRepository pickingJobTimelineReportRepository;

	public Page<PickingJobTimelineReport> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return pickingJobTimelineReportRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));
	}
	
	public Page<PickingJobTimelineReport> getAll(Date startDate, Date endDate, Integer pageNumber, Integer pageSize, Sort sort) {
		return pickingJobTimelineReportRepository.findAllForReport(startDate, endDate, new PageRequest(pageNumber-1, pageSize, sort));
	}
	
	public List<WarehouseEmployee> getAllPickersByStartTimeAndEndTime(Date startTime, Date endTime) {
		return pickingJobTimelineReportRepository.findAllPickersByStartTimeAndEndTime(startTime, endTime);
	}

	public PickingJobTimelineReport getReportForPicker(Date startDate, Date endDate, Long warehouseEmployeeId) {
		return pickingJobTimelineReportRepository.getReportForPicker(startDate, endDate, warehouseEmployeeId);
	}
	public AveragePickingSummary getReportForAvg(Date startDate, Date endDate) {
		List<PickingJobTimelineReport> reports = pickingJobTimelineReportRepository.findAllForReport(startDate, endDate);
		Double avgTotalPicked = 0.0;
		Double avgTotalPickedUnique = 0.0;
		Double avgTotalMissed = 0.0;
		Double avgTotalMissedUnique = 0.0;
		Double avgTotalPickedWithoutScan = 0.0;
		Double avgTotalPickedWithoutScanUnique = 0.0;
		Double avgTotalWrongLocation = 0.0;
		Double avgTotalWrongLocationUnique = 0.0;
		for(PickingJobTimelineReport r : reports) {
			avgTotalPicked += Double.valueOf(r.getTotalPicked());
			avgTotalPickedUnique += Double.valueOf(r.getTotalPickedUnique());
			avgTotalMissed += Double.valueOf(r.getTotalMissed());
			avgTotalMissedUnique += Double.valueOf(r.getTotalMissedUnique());
			avgTotalPickedWithoutScan += Double.valueOf(r.getTotalPickedWithoutScan());
			avgTotalPickedWithoutScanUnique += Double.valueOf(r.getTotalPickedWithoutScanUnique());
			avgTotalWrongLocation += Double.valueOf(r.getTotalWrongLocation());
			avgTotalWrongLocationUnique += Double.valueOf(r.getTotalWrongLocationUnique());
		}
		Double size = Double.valueOf(reports.size());
		AveragePickingSummary summary = AveragePickingSummary.builder()
				.setAvgTotalMissed(avgTotalMissed/size)
				.setAvgTotalMissedUnique(avgTotalMissedUnique/size)
				.setAvgTotalPicked(avgTotalPicked/size)
				.setAvgTotalPickedUnique(avgTotalPickedUnique/size)
				.setAvgTotalPickedWithoutScan(avgTotalPickedWithoutScan/size)
				.setAvgTotalPickedWithoutScanUnique(avgTotalPickedWithoutScanUnique/size)
				.setAvgTotalWrongLocation(avgTotalWrongLocation/size)
				.setAvgTotalWrongLocationUnique(avgTotalWrongLocationUnique/size)
				.createAveragePickingSummary();
				
		return summary;
	}
	
	public PickingJobTimelineReport getOne(Long reportId) {
		return pickingJobTimelineReportRepository.getOne(reportId);
	}
	public PickingJobTimelineReport save(PickingJobTimelineReport pickingJobTimelineReport) {
		return pickingJobTimelineReportRepository.save(pickingJobTimelineReport);
	}
	public void delete(Long reportId) {
		pickingJobTimelineReportRepository.delete(reportId);
	}
}