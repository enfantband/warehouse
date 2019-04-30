package com.samsbeauty.warehouse.picking.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.exception.rest.PickingJobTimelineNotFoundException;
import com.samsbeauty.warehouse.picking.model.PickingItem;
import com.samsbeauty.warehouse.picking.model.PickingItem.PickingItemStatus;
import com.samsbeauty.warehouse.picking.model.PickingJob;
import com.samsbeauty.warehouse.picking.model.PickingJob.PickingJobStatus;
import com.samsbeauty.warehouse.picking.model.PickingJobTimeline;
import com.samsbeauty.warehouse.picking.report.model.PickingJobTimelineReport;
import com.samsbeauty.warehouse.picking.report.repository.PickingJobTimelineReportRepository;
import com.samsbeauty.warehouse.picking.repository.PickingItemRepository;
import com.samsbeauty.warehouse.picking.repository.PickingJobRepository;
import com.samsbeauty.warehouse.picking.repository.PickingJobTimelineRepository;

@Service
@Transactional
public class PickingJobTimelineServiceImpl implements PickingJobTimelineService {
	@Autowired private PickingJobTimelineRepository pickingJobTimelineRepository;	
	@Autowired private PickingJobRepository pickingJobRepository;
	@Autowired private PickingJobTimelineReportRepository pickingJobTimelineReportRepository;
	@Autowired private PickingItemRepository pickingItemRepository;

	public Page<PickingJobTimeline> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return pickingJobTimelineRepository.findByActivated(true, new PageRequest(pageNumber-1, pageSize, sort));
	}
	public List<PickingJobTimeline> getAssignedTimelinesByWarehouseEmployeeId(Long warehouseEmployeeId) {
		return pickingJobTimelineRepository.findAssignedTimelinesByWarehouseEmployeeId(warehouseEmployeeId);
	}
	public List<PickingJobTimeline> getAssignedTimelinesByGid(String gid) {
		return pickingJobTimelineRepository.findAssignedTimelinesByGid(gid);
	}
	public PickingJobTimeline getOne(Long timelineId) {
		return pickingJobTimelineRepository.getOne(timelineId);
	}
	public PickingJobTimeline save(PickingJobTimeline pickingJobTimeline) {
		return pickingJobTimelineRepository.save(pickingJobTimeline);
	}
	public void delete(Long timelineId) {
		pickingJobTimelineRepository.delete(timelineId);
	}

	public PickingJobTimeline addPickingJobToStart(PickingJob pickingJob, WarehouseEmployee warehouseEmployee, String pickingJobStatus, Date eventTime) {
		String prevStatus = pickingJob.getPickingStatus();
		pickingJob.updateStatus(pickingJobStatus);
		pickingJobRepository.save(pickingJob);
				
		PickingJobTimeline startingTimeline = PickingJobTimeline.builder(pickingJob)
				.setPickingJobStatusFrom(prevStatus)
				.setPickingJobStatusTo(pickingJobStatus)
				.setWarehouseEmployee(warehouseEmployee)
				.setEventTime(eventTime)
				.createPickingJobTimeline();
		return pickingJobTimelineRepository.save(startingTimeline);		
	}
	public PickingJobTimeline addPickingJobToEnd(PickingJob pickingJob, WarehouseEmployee warehouseEmployee, String pickingJobStatus, Date eventTime)  throws PickingJobTimelineNotFoundException {
		List<PickingJobTimeline> timelines = pickingJob.getPickingJobTimelines();
		if(timelines.size() == 0) {
			throw new PickingJobTimelineNotFoundException("Cannot find any timeline in this pikcing job");
		} 

		PickingJobTimeline startTimeline = timelines.get(timelines.size() - 1);
		
		if(!startTimeline.getPickingJobStatusTo().equals(PickingJobStatus.STARTED)) {
			throw new PickingJobTimelineNotFoundException("This job has not been started. Status: " + startTimeline.getPickingJobStatusTo());
		}
		String prevPickingJobStatus = pickingJob.getPickingStatus();
		PickingJobTimeline pauseTimeline = PickingJobTimeline.builder(pickingJob)
				.setPickingJobStatusFrom(prevPickingJobStatus)
				.setPickingJobStatusTo(pickingJobStatus)
				.setWarehouseEmployee(warehouseEmployee)
				.setEventTime(eventTime)
				.createPickingJobTimeline();
		pickingJobTimelineRepository.save(pauseTimeline);
		

		pickingJob.updateStatus(pickingJobStatus);
		pickingJobRepository.save(pickingJob);

		Set<String>	pickedItemChk = new HashSet<>();
		Set<String> missedItemChk = new HashSet<>();
		Set<String> pickedWithoutScanChk = new HashSet<>();
		Set<String> wrongLocationChk = new HashSet<>();
		Set<String> savedChk = new HashSet<>();
		
		Integer totalPicked = 0;
		Integer totalPickedUnique = 0;
		Integer totalMissed = 0;
		Integer totalMissedUnique = 0;
		Integer totalPickedWithoutScan = 0;
		Integer totalPickedWithoutScanUnique = 0;
		Integer totalWrongLocation = 0;
		Integer totalWrongLocationUnique = 0;
		Integer totalSaved = 0;
		Integer totalSavedUnique = 0;
		
		List<PickingItem> itemList = pickingItemRepository.findAllByTimelineId(startTimeline.getTimelineId());		
		for(PickingItem pickingItem : itemList) {			
			
			if(pickingItem.getPickingItemStatus().equals(PickingItemStatus.PICKED)) {
				if(!pickedItemChk.contains(pickingItem.getGeneratedBarcode())) {
					pickedItemChk.add(pickingItem.getGeneratedBarcode());
					totalPickedUnique ++;
				}
				totalPicked += pickingItem.getQuantity();
			} else if(pickingItem.getPickingItemStatus().equals(PickingItemStatus.MISSED)) {
				if(!missedItemChk.contains(pickingItem.getGeneratedBarcode())) {
					missedItemChk.add(pickingItem.getGeneratedBarcode());
					totalMissedUnique ++;
				}
				totalMissed += pickingItem.getQuantity();
			} else if(pickingItem.getPickingItemStatus().equals(PickingItemStatus.PICKED_WITHOUT_SCAN)) {
				if(!pickedWithoutScanChk.contains(pickingItem.getGeneratedBarcode())) {
					pickedWithoutScanChk.add(pickingItem.getGeneratedBarcode());
					totalPickedWithoutScanUnique ++;
				}
				totalPickedWithoutScan += pickingItem.getQuantity();
			} else if(pickingItem.getPickingItemStatus().equals(PickingItemStatus.WRONG_LOCATION)) {
				if(!wrongLocationChk.contains(pickingItem.getGeneratedBarcode())) {
					wrongLocationChk.add(pickingItem.getGeneratedBarcode());
					totalWrongLocationUnique ++;
				}
				totalWrongLocation += pickingItem.getQuantity();
			} else if(pickingItem.getPickingItemStatus().equals(PickingItemStatus.SAVE)) {
				if(!savedChk.contains(pickingItem.getGeneratedBarcode())) {
					savedChk.add(pickingItem.getGeneratedBarcode());
					totalSavedUnique ++;
				}
				totalSaved += pickingItem.getQuantity();
			}
		}
		
		PickingJobTimelineReport pickingJobTimelineReport = PickingJobTimelineReport.builder(pickingJob, warehouseEmployee, startTimeline, pauseTimeline)
				.setTotalMissed(totalMissed)
				.setTotalMissedUnique(totalMissedUnique)
				.setTotalPicked(totalPicked)
				.setTotalPickedUnique(totalPickedUnique)
				.setTotalPickedWithoutScan(totalPickedWithoutScan)
				.setTotalPickedWithoutScanUnique(totalPickedWithoutScanUnique)
				.setTotalWrongLocation(totalWrongLocation)
				.setTotalWrongLocationUnique(totalWrongLocationUnique)
				.setTotalTimeSpent(pauseTimeline.getEventTime().getTime() - startTimeline.getEventTime().getTime())
				.setTotalSaved(totalSaved)
				.setTotalSavedUnique(totalSavedUnique)
				.createPickingJobTimelineReport();
		
		pickingJobTimelineReportRepository.save(pickingJobTimelineReport);
		
		
		return pauseTimeline;
	}
	public PickingJobTimeline handoverPickingJob(PickingJob pickingJob, WarehouseEmployee warehouseEmployee, Date eventTime)  throws PickingJobTimelineNotFoundException {
		PickingJobTimeline startingTimeline = PickingJobTimeline.builder(pickingJob)
				.setPickingJobStatusFrom(pickingJob.getPickingStatus())
				.setPickingJobStatusTo(PickingJobStatus.PASSED)
				.setWarehouseEmployee(warehouseEmployee)
				.setEventTime(eventTime)
				.createPickingJobTimeline();
		
		pickingJob.updateStatus(PickingJobStatus.PASSED);
		pickingJobRepository.save(pickingJob);
		
		return pickingJobTimelineRepository.save(startingTimeline);	
	}
}