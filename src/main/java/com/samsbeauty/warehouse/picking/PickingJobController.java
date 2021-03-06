package com.samsbeauty.warehouse.picking;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samsbeauty.old.model.Inventory;
import com.samsbeauty.warehouse.DateUtil;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.employee.service.WarehouseEmployeeService;
import com.samsbeauty.warehouse.exception.rest.InvalidProductBarcodeException;
import com.samsbeauty.warehouse.exception.rest.InvalidRequestException;
import com.samsbeauty.warehouse.exception.rest.LocationNotFoundException;
import com.samsbeauty.warehouse.exception.rest.PickingJobNotFoundException;
import com.samsbeauty.warehouse.exception.rest.PickingJobTimelineNotFoundException;
import com.samsbeauty.warehouse.exception.rest.WarehouseEmployeeNotFoundException;
import com.samsbeauty.warehouse.exception.rest.WrongDateFormatException;
import com.samsbeauty.warehouse.exception.rest.WrongParameterException;
import com.samsbeauty.warehouse.model.WarehouseItem;
import com.samsbeauty.warehouse.model.WarehouseItemBox;
import com.samsbeauty.warehouse.model.WarehouseLevel;
import com.samsbeauty.warehouse.old.helper.InventoryRestHelper;
import com.samsbeauty.warehouse.picking.exception.WrongItemScanException;
import com.samsbeauty.warehouse.picking.exception.WrongTimelineException;
import com.samsbeauty.warehouse.picking.export.model.GroupPickingItemExport;
import com.samsbeauty.warehouse.picking.model.PickingGroup;
import com.samsbeauty.warehouse.picking.model.PickingItem;
import com.samsbeauty.warehouse.picking.model.PickingItemInfo;
import com.samsbeauty.warehouse.picking.model.PickingJob;
import com.samsbeauty.warehouse.picking.model.PickingJob.PickingJobStatus;
import com.samsbeauty.warehouse.picking.model.PickingJobGroup;
import com.samsbeauty.warehouse.picking.model.PickingJobTimeline;
import com.samsbeauty.warehouse.picking.report.model.ChartJsColumn;
import com.samsbeauty.warehouse.picking.report.model.PickingJobTimelineReport;
import com.samsbeauty.warehouse.picking.report.model.PickingJobTimelineReportForChartJS;
import com.samsbeauty.warehouse.picking.report.service.PickingJobTimelineReportService;
import com.samsbeauty.warehouse.picking.service.PickingGroupService;
import com.samsbeauty.warehouse.picking.service.PickingItemInfoService;
import com.samsbeauty.warehouse.picking.service.PickingItemService;
import com.samsbeauty.warehouse.picking.service.PickingJobGroupService;
import com.samsbeauty.warehouse.picking.service.PickingJobService;
import com.samsbeauty.warehouse.picking.service.PickingJobTimelineService;
import com.samsbeauty.warehouse.picking.sort.PickingSorting;
import com.samsbeauty.warehouse.service.WarehouseItemBoxService;
import com.samsbeauty.warehouse.service.WarehouseItemService;
import com.samsbeauty.warehouse.service.WarehouseLevelService;
import com.samsbeauty.warehouse.util.BarcodeUtil;

@RestController
@RequestMapping("/api/pickingJob")
public class PickingJobController {	
	@RequestMapping(method=RequestMethod.GET, value="/group")
	ResponseEntity<Page<PickingJobGroup>> getAllPickingJob(
			@RequestParam(name="pageSize", required=false) Integer pageSize, 
			@RequestParam(name="page", required=false) Integer page,
			@RequestParam(name="sortingDirections", required=false) String sortingDirection,
			@RequestParam(name="sortingFields", required=false) String sortingField,
			@RequestParam(name="searchDateFrom", required=false) String searchDateFrom,
			@RequestParam(name="searchDateTo", required=false) String searchDateTo 
			) throws WrongParameterException, WrongDateFormatException {
		if(pageSize == null){
			pageSize = DEFAULT_NUM_PER_PAGE;
		}
		if(page == null) {
			page = 1;
		}
		Sort sort = null;
		if(sortingDirection != null && sortingField != null) {
			String[] directions = sortingDirection.split(" ");
			String[] sortingFields = sortingField.split(" ");
			if(directions.length > 0) {
				if(directions.length != sortingFields.length) {
					throw new WrongParameterException("Number of sorting field and direction are different.");
				}
				sort = new Sort(Sort.Direction.fromString(directions[0]), sortingFields[0]);
				if(directions.length > 1) {
					for(int i=1; i<directions.length; i++) {
						sort = sort.and(new Sort(Sort.Direction.fromString(directions[i]), sortingFields[i]));
					}
				}
			}
		} else {
			sort = new Sort(Sort.Direction.DESC, "pickingJobGroupId");
		}
		Page<PickingJobGroup> pickingJobGroups = null;
		if(searchDateFrom != null && searchDateTo != null) {
			if(!DateUtil.isValidDateFormat(searchDateFrom) || !DateUtil.isValidDateFormat(searchDateTo)) {
				throw new WrongDateFormatException("You passed a wrong format of date for searching. The format of date must be {mm/dd/yyyy} but you passed " + searchDateFrom + " ~ " + searchDateTo);
			}
			SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DATE_FORMAT);
			Date dateFrom = null;
			Date dateTo = null;
			try {
				dateFrom = (Date) formatter.parse(searchDateFrom);
			} catch (ParseException e) {
				throw new WrongDateFormatException("You passed a wrong format of searchDateFrom. The format of date must be {mm/dd/yyyy}");
			}
			try {

				dateTo = (Date) formatter.parse(searchDateTo);	
			} catch (ParseException e) {
				throw new WrongDateFormatException("You passed a wrong format of searchDateTo. The format of date must be {mm/dd/yyyy}");
			}
			pickingJobGroups = pickingJobGroupService.getAll(page, pageSize, sort, dateFrom, dateTo);
		} else {
			pickingJobGroups = pickingJobGroupService.getAll(page, pageSize, sort);	
		}
		
		return new ResponseEntity<>(pickingJobGroups, HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.DELETE, value="/group/{pickingJobGroupId}")
	PickingJobGroup removePickingGroup(@PathVariable Long pickingJobGroupId) {
		PickingJobGroup pickingJobGroup = pickingJobGroupService.getOne(pickingJobGroupId);
		pickingJobGroup.deactivate();
		return pickingJobGroupService.save(pickingJobGroup);
	}
	

	// Retrieve All Resources
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<Page<PickingJob>> getAll(
			@RequestParam(name="pageSize", required=false) Integer pageSize,
			@RequestParam(name="page", required=false) Integer page,
			@RequestParam(name="sortingDirection", required=false) String sortingDirection,
			@RequestParam(name="sortingFields", required=false) String sortingField) throws WrongParameterException {
		if(pageSize == null){
			pageSize = DEFAULT_NUM_PER_PAGE;
		}
		if(page == null) {
			page = 1;
		}
		Sort sort = null;
		if(sortingDirection != null && sortingField != null) {
			String[] directions = sortingDirection.split(" ");
			String[] sortingFields = sortingField.split(" ");
			if(directions.length > 0) {
				if(directions.length != sortingFields.length) {
					throw new WrongParameterException("Number of sorting field and direction are different.");
				}
				sort = new Sort(Sort.Direction.fromString(directions[0]), sortingFields[0]);
				if(directions.length > 1) {
					for(int i=1; i<directions.length; i++) {
						sort = sort.and(new Sort(Sort.Direction.fromString(directions[i]), sortingFields[i]));
					}
				}
			}
		} else {
			sort = new Sort(Sort.Direction.DESC, "pickingJobId");
		}
		Page<PickingJob> pickingJobList = pickingJobService.getAll(page, pageSize, sort);
		return new ResponseEntity<>(pickingJobList, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST)
	PickingJob add(@RequestBody @Valid final PickingJob pickingJob) {
		PickingJob newPickingJob = new PickingJob();
		//Implement here
		return pickingJobService.save(newPickingJob);
	}
	
	
	
	@RequestMapping(method=RequestMethod.PUT)
	PickingJob update(@RequestBody @Valid final PickingJob pickingJob) {
		PickingJob updatePickingJob = pickingJobService.getOne(pickingJob.getPickingJobId());
		//Implement here
		return updatePickingJob;
	}
	

	@RequestMapping(method=RequestMethod.DELETE, value="/{pickingJobId}")
	PickingJob delete(@PathVariable Long pickingJobId) {
		PickingJob deletePickingJob = pickingJobService.getOne(pickingJobId);
		pickingJobService.delete(pickingJobId);
		return  deletePickingJob;
	}

	@RequestMapping(method=RequestMethod.POST, value="/create") 
	PickingJobGroup createPickingJob(
			@RequestParam(value="numberOfProcess", required=true) Integer numberOfProcess, 
			@RequestParam(value="includingOrders", required=true) String includingOrders,
			@RequestParam(value="amzhs", required=true) Integer amzhs,
			@RequestParam(value="amzeb", required=true) Integer amzeb,
			HttpServletRequest request) throws Exception {
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee regBy = warehouseEmployeeService.getOneByGid(gid);
		Map<String, PickingGroup> pickingGroupTable = pickingGroupService.getPickingGroupTable();
		PickingJobGroup pickingJobGroup = pickingJobService.createPickingJob(numberOfProcess, pickingGroupTable, includingOrders, amzhs, amzeb, regBy);
		return pickingJobGroup;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/timeline/picker/{pickerId}/pickingJob/{pickingJobId}")
	PickingJobTimeline createTimeline(@PathVariable Long pickingJobId, @PathVariable Long pickerId) throws PickingJobNotFoundException, WarehouseEmployeeNotFoundException {
		PickingJob pickingJob = pickingJobService.getOne(pickingJobId);
		if(pickingJob == null) {
			throw new PickingJobNotFoundException("Cannot find the picking job");
		}
		
		WarehouseEmployee warehouseEmployee = warehouseEmployeeService.getOne(pickerId);
		if(warehouseEmployee == null) {
			throw new WarehouseEmployeeNotFoundException("Cannot find the picker!!");
		}
		
		PickingJobTimeline newTimeline = PickingJobTimeline.builder(pickingJob)
				.setWarehouseEmployee(warehouseEmployee)
				.setPickingJobStatusFrom(pickingJob.getPickingStatus())
				.setPickingJobStatusTo(PickingJob.PickingJobStatus.ASSIGNED)
				.setEventTime(new Date())
				.createPickingJobTimeline();
		pickingJob.updateStatus(PickingJob.PickingJobStatus.ASSIGNED);
		pickingJobService.save(pickingJob);
		return pickingJobTimelineService.save(newTimeline);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/timeline/{timelineId}/picker/{pickerId}")
	PickingJobTimeline deleteAssignedPicker(@PathVariable Long pickerId, @PathVariable Long timelineId) {
		PickingJobTimeline timeline = pickingJobTimelineService.getOne(timelineId);
		if(timeline.isAssigned()) {
			// don't delete the picker if it already started or finished.
			timeline.deactivate();
		}
		return pickingJobTimelineService.save(timeline);
	}
		
	@RequestMapping(method=RequestMethod.GET, value="/picker/{pickerId}/assignedJobs")
	List<PickingJob> getAssignedJobs(@PathVariable Long pickerId) throws WarehouseEmployeeNotFoundException {		
		return pickingJobService.getAssignedJobs(pickerId);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/picker/gid/{gid}/assignedJobs")
	List<PickingJob> getAssignedJobsByGid(@PathVariable String gid) throws WarehouseEmployeeNotFoundException {
		WarehouseEmployee picker = warehouseEmployeeService.getOneByGid(gid); 
		if(picker == null) {
			throw new WarehouseEmployeeNotFoundException("There is no employee mathcing the gid");
		}
		return pickingJobService.getAssignedJobs(picker.getWarehouseEmployeeId());
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/picker/gid/{gid}/assignedJob/{assignedJobId}/start")
	PickingJobTimeline startJobByGid(@PathVariable String gid, @PathVariable Long assignedJobId) throws WarehouseEmployeeNotFoundException, PickingJobTimelineNotFoundException {
		WarehouseEmployee warehouseEmployee = warehouseEmployeeService.getOneByGid(gid);
		if(warehouseEmployee == null) {
			throw new WarehouseEmployeeNotFoundException("Cannot find the picker");
		}
		PickingJob pickingJob = pickingJobService.getOne(assignedJobId);
		if(!pickingJob.getPickingStatus().equals(PickingJobStatus.ASSIGNED) &&
				!pickingJob.getPickingStatus().equals(PickingJobStatus.PASSED) &&
				!pickingJob.getPickingStatus().equals(PickingJobStatus.PAUSED)) {
			throw new PickingJobTimelineNotFoundException("This job is not ready for picking.");
		}
		return pickingJobTimelineService.addPickingJobToStart(pickingJob, warehouseEmployee, PickingJobStatus.STARTED, new Date());
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/picker/{pickerId}/assignedJob/{assignedJobId}/start")	
	PickingJobTimeline startJob(@PathVariable Long pickerId, @PathVariable Long assignedJobId) throws WarehouseEmployeeNotFoundException, PickingJobTimelineNotFoundException {
		WarehouseEmployee  warehouseEmployee = warehouseEmployeeService.getOne(pickerId);
		if(warehouseEmployee == null) {
			throw new WarehouseEmployeeNotFoundException("Cannot find the picker");
		}
		PickingJob pickingJob = pickingJobService.getOne(assignedJobId);
		System.out.println(pickingJob.getPickingStatus());
		if(!pickingJob.getPickingStatus().equals(PickingJobStatus.ASSIGNED) &&
				!pickingJob.getPickingStatus().equals(PickingJobStatus.PASSED)) {
			throw new PickingJobTimelineNotFoundException("This job is not ready for picking.");
		}
		
		return pickingJobTimelineService.addPickingJobToStart(pickingJob, warehouseEmployee, PickingJobStatus.STARTED, new Date());
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/picker/{pickerId}/assignedJob/{assignedJobId}/finish")
	PickingJobTimeline finishJob(@PathVariable Long pickerId, @PathVariable Long assignedJobId) throws WarehouseEmployeeNotFoundException, PickingJobTimelineNotFoundException {
		WarehouseEmployee  warehouseEmployee = warehouseEmployeeService.getOne(pickerId);
		if(warehouseEmployee == null) {
			throw new WarehouseEmployeeNotFoundException("Cannot find the picker");
		}
		PickingJob pickingJob = pickingJobService.getOne(assignedJobId);
		if(!pickingJob.getPickingStatus().equals(PickingJobStatus.STARTED)) {
			throw new PickingJobTimelineNotFoundException("This job has not been started");
		}
		
		return pickingJobTimelineService.addPickingJobToEnd(pickingJob, warehouseEmployee, PickingJobStatus.FINISHED, new Date());
	}
	

	@RequestMapping(method=RequestMethod.POST, value="/picker/gid/{gid}/assignedJob/{assignedJobId}/finish")
	PickingJobTimeline finishJob(@PathVariable String gid, @PathVariable Long assignedJobId) throws WarehouseEmployeeNotFoundException, PickingJobTimelineNotFoundException {
		WarehouseEmployee  warehouseEmployee = warehouseEmployeeService.getOneByGid(gid);
		if(warehouseEmployee == null) {
			throw new WarehouseEmployeeNotFoundException("Cannot find the picker");
		}
		PickingJob pickingJob = pickingJobService.getOne(assignedJobId);
		if(!pickingJob.getPickingStatus().equals(PickingJobStatus.STARTED)) {
			throw new PickingJobTimelineNotFoundException("This job has not been started");
		}
		
		return pickingJobTimelineService.addPickingJobToEnd(pickingJob, warehouseEmployee, PickingJobStatus.FINISHED, new Date());
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/picker/{pickerId}/assignedJob/{assignedJobId}/pause")
	PickingJobTimeline pauseJob(@PathVariable Long pickerId, @PathVariable Long assignedJobId) throws WarehouseEmployeeNotFoundException, PickingJobTimelineNotFoundException {
		WarehouseEmployee  warehouseEmployee = warehouseEmployeeService.getOne(pickerId);
		if(warehouseEmployee == null) {
			throw new WarehouseEmployeeNotFoundException("Cannot find the picker");
		}
		PickingJob pickingJob = pickingJobService.getOne(assignedJobId);
		if(!pickingJob.getPickingStatus().equals(PickingJobStatus.STARTED)) {
			throw new PickingJobTimelineNotFoundException("This job has not been started");
		}		
		return pickingJobTimelineService.addPickingJobToEnd(pickingJob, warehouseEmployee, PickingJobStatus.PAUSED, new Date());
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/picker/gid/{gid}/assignedJob/{assignedJobId}/pause")
	PickingJobTimeline pauseJobByGid(@PathVariable String gid, @PathVariable Long assignedJobId) throws WarehouseEmployeeNotFoundException, PickingJobTimelineNotFoundException {
		WarehouseEmployee  warehouseEmployee = warehouseEmployeeService.getOneByGid(gid);
		if(warehouseEmployee == null) {
			throw new WarehouseEmployeeNotFoundException("Cannot find the picker");
		}
		PickingJob pickingJob = pickingJobService.getOne(assignedJobId);
		if(!pickingJob.getPickingStatus().equals(PickingJobStatus.STARTED)) {
			throw new PickingJobTimelineNotFoundException("This job has not been started");
		}		
		return pickingJobTimelineService.addPickingJobToEnd(pickingJob, warehouseEmployee, PickingJobStatus.PAUSED, new Date());
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/picker/{pickerId}/assignedJob/{assignedJobId}/pass")
	PickingJobTimeline passJob(@PathVariable Long pickerId, @PathVariable Long assignedJobId) throws WarehouseEmployeeNotFoundException, PickingJobTimelineNotFoundException {
		WarehouseEmployee  warehouseEmployee = warehouseEmployeeService.getOne(pickerId);
		if(warehouseEmployee == null) {
			throw new WarehouseEmployeeNotFoundException("Cannot find the picker");
		}
		PickingJob pickingJob = pickingJobService.getOne(assignedJobId);
		if(!pickingJob.getPickingStatus().equals(PickingJobStatus.PAUSED)) {
			throw new PickingJobTimelineNotFoundException("Before hand over the job, you should pause the job first.");
		}		
		return pickingJobTimelineService.handoverPickingJob(pickingJob, warehouseEmployee, new Date());
	}

	@RequestMapping(method=RequestMethod.POST, value="/picker/gid/{gid}/assignedJob/{assignedJobId}/pass")
	PickingJobTimeline passJobByGid(@PathVariable String gid, @PathVariable Long assignedJobId) throws WarehouseEmployeeNotFoundException, PickingJobTimelineNotFoundException {
		WarehouseEmployee  warehouseEmployee = warehouseEmployeeService.getOneByGid(gid);
		if(warehouseEmployee == null) {
			throw new WarehouseEmployeeNotFoundException("Cannot find the picker");
		}
		PickingJob pickingJob = pickingJobService.getOne(assignedJobId);
		if(!pickingJob.getPickingStatus().equals(PickingJobStatus.PAUSED)) {
			throw new PickingJobTimelineNotFoundException("Before hand over the job, you should pause the job first.");
		}		
		return pickingJobTimelineService.handoverPickingJob(pickingJob, warehouseEmployee, new Date());
	}

	@RequestMapping(method=RequestMethod.POST, value="/picker/gid/{gid}/assignedJob/{assignedJobId}/pickitem/{barcode}/quantity/{quantity}/location/{locationBarcode}")
	PickingItem pickItem(
			@PathVariable final String gid, 
			@PathVariable final Long assignedJobId, 
			@PathVariable final String barcode, 
			@PathVariable final Integer quantity, 
			@PathVariable final String locationBarcode,
			@RequestParam(name="boxBarcode", required=false) final String boxBarcode,
			@RequestParam(name="reason", required=false) final String reason) throws WarehouseEmployeeNotFoundException, 
																								PickingJobTimelineNotFoundException, 
																								WrongTimelineException , 
																								WrongItemScanException, 
																								LocationNotFoundException , 
																								InvalidProductBarcodeException {
		WarehouseEmployee picker = warehouseEmployeeService.getOneByGid(gid);
		// Timeline check. If the timeline is not for picking, then it should return wrong timeline exception.
		PickingJob pickingJob = pickingJobService.getOne(assignedJobId);
		List<PickingJobTimeline> timelines = pickingJob.getPickingJobTimelines();
		if(timelines.size() == 0) {
			throw new WrongTimelineException("This job is not assigned to you!!");
		}
		PickingJobTimeline latestTimeline = timelines.get(timelines.size() - 1);
		if(!latestTimeline.getWarehouseEmployee().equals(picker)) {
			throw new WrongTimelineException("This job is not assigned to you!!");
		}
		if(!latestTimeline.getPickingJobStatusTo().equals(PickingJob.PickingJobStatus.STARTED)) {
			throw new WrongTimelineException("This job has not been started. Please start this job first.");
		}
		
		List<PickingItemInfo> pickingItemInfos = pickingItemInfoService.getAllByPickingJobIdGroupByGeneratedBarcode(pickingJob.getPickingJobId());
		PickingItemInfo selectedPickingItem = null;
		for(PickingItemInfo pickingItemInfo : pickingItemInfos) {
			if(pickingItemInfo.getGeneratedBarcode().equals(barcode) || pickingItemInfo.getProductBarcode().equals(barcode)) {
				selectedPickingItem = pickingItemInfo;
				break;
			}
		}
		if(selectedPickingItem == null) {
			throw new WrongItemScanException("Barocde does not match any product");
		}
		
		// Check location barcode. If there does not exist the scanned item in the location then put the item in the location set to 0 quantity;
		List<WarehouseLevel> warehouseLevelList = warehouseLevelService.getAllByLocationBarcode(BarcodeUtil.getLocationCode(locationBarcode));
		if(warehouseLevelList == null || warehouseLevelList.size() == 0) {
			throw new LocationNotFoundException("Cannot find a location");
		}		
		WarehouseLevel level = warehouseLevelList.get(0);
		WarehouseItem warehouseItem = null;
		WarehouseItemBox warehouseItemBox = null;
		if(!StringUtils.isEmpty(boxBarcode) && BarcodeUtil.isBoxCode(boxBarcode)) {
			// If a scanned item is in a box(user scanned a box barcode), then select items in the box first then find the item.
			String boxCode = BarcodeUtil.getBoxCode(boxBarcode);
			warehouseItemBox = warehouseItemBoxService.getOneByBoxPrefixAndBoxCodeAndLevelId(boxCode, level.getLevelId());
			
			List<WarehouseItem> items = warehouseItemService.getAllByItemBoxId(warehouseItemBox.getItemBoxId());
			
			for(WarehouseItem item : items) {
				if(item.getGeneratedBarcode().equals(selectedPickingItem.getGeneratedBarcode())) {
					warehouseItem = item;
				}
			}
		} else {
			List<WarehouseItem> warehouseItemList = warehouseItemService.getAllByLevelId(level.getLevelId()); 
			for(WarehouseItem item : warehouseItemList) {
				if(item.getGeneratedBarcode().equals(selectedPickingItem.getGeneratedBarcode())) {
					warehouseItem = item;
				}
			}	
		}
		
		return pickingItemService.pick(level, pickingJob, warehouseItemBox, warehouseItem, picker, latestTimeline, quantity, barcode, reason);		
	}
	
	// Save for later function 
	@RequestMapping(method=RequestMethod.POST, value="/picker/gid/{gid}/assignedJob/{assignedJobId}/pickitem/{barcode}/save/quantity/{quantity}")
	PickingItem saveForLater(@PathVariable String gid, @PathVariable Long assignedJobId, @PathVariable String barcode, @PathVariable Integer quantity) throws InvalidRequestException, WrongTimelineException, InvalidProductBarcodeException {
		WarehouseEmployee picker = warehouseEmployeeService.getOneByGid(gid);
		PickingJob pickingJob = pickingJobService.getOne(assignedJobId);
		if(pickingJob.isSaved()) {
			throw new InvalidRequestException("This job already has been saved for later picking.");
		}
		
		List<PickingJobTimeline> timelines = pickingJob.getPickingJobTimelines();
		if(timelines.size() == 0) {
			throw new WrongTimelineException("This job is not assigned to you!!");
		}
		PickingJobTimeline latestTimeline = timelines.get(timelines.size() - 1);
		if(!latestTimeline.getWarehouseEmployee().equals(picker)) {
			throw new WrongTimelineException("This job is not assigned to you!!");
		}
		
		List<Inventory> inventoryList = inventoryRestHelper.getInventoryList(Arrays.asList(barcode));
		if(inventoryList == null || inventoryList.size() == 0) {
			throw new InvalidProductBarcodeException("Cannot find a product with the barcode you provided");
		}
		Inventory inventory = inventoryList.get(0);
		return pickingItemService.saveForLater(pickingJob, picker, inventory, latestTimeline, quantity);
	}
	
	// Miss function with Location Barcode
	@RequestMapping(method=RequestMethod.POST, value="/picker/gid/{gid}/assignedJob/{assignedJobId}/pickitem/{barcode}/miss/quantity/{quantity}/location/{locationBarcode}")
	PickingItem skipItemWithLocation(@PathVariable String gid, @PathVariable Long assignedJobId, @PathVariable String barcode, @PathVariable Integer quantity, @PathVariable String locationBarcode) throws WrongTimelineException, InvalidProductBarcodeException, LocationNotFoundException {
		WarehouseEmployee picker = warehouseEmployeeService.getOneByGid(gid);
		WarehouseLevel level = warehouseLevelService.getOneByLocationBarcode(BarcodeUtil.getLocationCode(locationBarcode));	
		
		// Timeline check. If the timeline is not for picking, then it should return wrong timeline exception.
		PickingJob pickingJob = pickingJobService.getOne(assignedJobId);
		List<PickingJobTimeline> timelines = pickingJob.getPickingJobTimelines();
		if(timelines.size() == 0) {
			throw new WrongTimelineException("This job is not assigned to you!!");
		}
		PickingJobTimeline latestTimeline = timelines.get(timelines.size() - 1);
		if(!latestTimeline.getWarehouseEmployee().equals(picker)) {
			throw new WrongTimelineException("This job is not assigned to you!!");
		}
		
		List<Inventory> inventoryList = inventoryRestHelper.getInventoryList(Arrays.asList(barcode));
		if(inventoryList == null || inventoryList.size() == 0) {
			throw new InvalidProductBarcodeException("Cannot find a product with the barcode you provided");
		}
		Inventory inventory = inventoryList.get(0);
		return pickingItemService.miss(level, pickingJob, picker, inventory.getGeneratedBarcode(), timelines.get(timelines.size()-1), quantity);
	}
	// Skip function without Location Barcode
	@RequestMapping(method=RequestMethod.POST, value="/picker/gid/{gid}/assignedJob/{assignedJobId}/pickitem/{barcode}/miss/quantity/{quantity}")
	PickingItem skipItem(@PathVariable String gid, @PathVariable Long assignedJobId, @PathVariable String barcode, @PathVariable Integer quantity) throws WrongTimelineException, InvalidProductBarcodeException, LocationNotFoundException {
		WarehouseEmployee picker = warehouseEmployeeService.getOneByGid(gid);
		// Timeline check. If the timeline is not for picking, then it should return wrong timeline exception.
		PickingJob pickingJob = pickingJobService.getOne(assignedJobId);
		List<PickingJobTimeline> timelines = pickingJob.getPickingJobTimelines();
		if(timelines.size() == 0) {
			throw new WrongTimelineException("This job is not assigned to you!!");
		}
		PickingJobTimeline latestTimeline = timelines.get(timelines.size() - 1);
		if(!latestTimeline.getWarehouseEmployee().equals(picker)) {
			throw new WrongTimelineException("This job is not assigned to you!!");
		}
		
		List<Inventory> inventoryList = inventoryRestHelper.getInventoryList(Arrays.asList(barcode));
		if(inventoryList == null || inventoryList.size() == 0) {
			throw new InvalidProductBarcodeException("Cannot find a product with the barcode you provided");
		}
		Inventory inventory = inventoryList.get(0);
		return pickingItemService.miss(null, pickingJob, picker, inventory.getGeneratedBarcode(), timelines.get(timelines.size()-1), quantity);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/report")
	PickingJobTimelineReportForChartJS<Integer> exportForChartJS (
			@RequestParam(name="searchDateFrom", required=false) String searchDateFrom,
			@RequestParam(name="searchDateTo", required=false) String searchDateTo 
			) throws WrongDateFormatException {
		PickingJobTimelineReportForChartJS<Integer> pickingPerformance = null;
		if(searchDateFrom == null || searchDateTo == null) {
			throw new WrongDateFormatException("You must pass the range of date as the parameter");
		} else {
			if(!DateUtil.isValidDateFormat(searchDateFrom) || !DateUtil.isValidDateFormat(searchDateTo)) {
				throw new WrongDateFormatException("You passed a wrong format of date for searching. The format of date must be {mm/dd/yyyy} but you passed " + searchDateFrom + " ~ " + searchDateTo);
			}
			SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DATE_FORMAT);
			Date dateFrom = null;
			Date dateTo = null;
			try {
				dateFrom = (Date) formatter.parse(searchDateFrom);
			} catch (ParseException e) {
				throw new WrongDateFormatException("You passed a wrong format of searchDateFrom. The format of date must be {mm/dd/yyyy}");
			}
			try {

				dateTo = (Date) formatter.parse(searchDateTo);	
			} catch (ParseException e) {
				throw new WrongDateFormatException("You passed a wrong format of searchDateTo. The format of date must be {mm/dd/yyyy}");
			}

			List<PickingJobGroup> jobGroups = pickingJobGroupService.getAll(dateFrom, dateTo);

			Map<String, Map<WarehouseEmployee, Integer>> pickingGroupEmployeeReports = new LinkedHashMap<>();
			
			// get all pickers in timeline between dateFrom and dateTo
			
			List<WarehouseEmployee> todayPickers = pickingJobTimelineReportService.getAllPickersByStartTimeAndEndTime(dateFrom, dateTo);
			 
			for(PickingJobGroup jobGroup : jobGroups) {
				String groupSet = "Picking #" + jobGroup.getPickingSet().toString();
				Map<WarehouseEmployee, Integer> pickers = new LinkedHashMap<>();
				for(WarehouseEmployee employee : todayPickers) {
					//initiate data for each picker
					pickers.put(employee, 0);
				}
				
				List<PickingJob> pickingJobs = jobGroup.getPickingJobs();
				for(PickingJob pickingJob : pickingJobs) {
					List<PickingJobTimelineReport> pickingJobTimelineReports = pickingJob.getPickingJobTimelineReports();
					for(PickingJobTimelineReport timelineReport : pickingJobTimelineReports) {
						if(pickingGroupEmployeeReports.get(groupSet).containsKey(timelineReport.getWarehouseEmployee().getWarehouseEmployeeId())) {
							Integer picked = pickers.get(timelineReport.getWarehouseEmployee());
							picked += timelineReport.getTotalPicked();
							pickers.put(timelineReport.getWarehouseEmployee(), picked);
						} else {
							pickers.put(timelineReport.getWarehouseEmployee(), timelineReport.getTotalPicked());
						}
					}
				}
				
			}
			
			pickingPerformance = new PickingJobTimelineReportForChartJS<>();
			List<String> reportLabels = new ArrayList<String>();
			pickingPerformance.setLabels(reportLabels);

			Map<WarehouseEmployee, List<Integer>> mergedEmployeeReports = new LinkedHashMap<>();
			
			for(Entry<String, Map<WarehouseEmployee, Integer>> entry : pickingGroupEmployeeReports.entrySet()) {
				reportLabels.add(entry.getKey());
				Map<WarehouseEmployee, Integer> employeeReports = entry.getValue();
				for(Entry<WarehouseEmployee, Integer> employeeReport : employeeReports.entrySet()) {
					if(mergedEmployeeReports.containsKey(employeeReport.getKey())) {
						List<Integer> pickingJobs = mergedEmployeeReports.get(employeeReport.getKey());
						pickingJobs.add(employeeReport.getValue());
					} else {
						List<Integer> pickingJobs = new ArrayList<>();
						pickingJobs.add(employeeReport.getValue());
					}
				}
			}
			
			for(Entry<WarehouseEmployee, List<Integer>> employeeReport : mergedEmployeeReports.entrySet()) {
				ChartJsColumn<Integer> column = new ChartJsColumn<>(
						employeeReport.getKey().getName(),
						employeeReport.getKey().getReportColor(),
						employeeReport.getValue()
						);
				pickingPerformance.getDatasets().add(column);
				
			}
		}
		return pickingPerformance;
	}
	
	// Picking item info list : without paging
	@RequestMapping(method=RequestMethod.GET, value="/{pickingJobId}/pickingItemInfos")
	ResponseEntity<List<PickingItemInfo>> getPickingItemInfos(@PathVariable Long pickingJobId) {
		List<PickingItemInfo> list = pickingItemInfoService.getAllByPickingJobIdGroupByGeneratedBarcode(pickingJobId);
		Map<String, PickingItemInfo> listMap = new HashMap<>();
		List<String> barcodes = new ArrayList<>();
		for(PickingItemInfo item:list){
			barcodes.add(item.getGeneratedBarcode());
			listMap.put(item.getGeneratedBarcode(), item);
		}
		List<Inventory> invlist = inventoryRestHelper.getInventoryList(barcodes);
		Map<String, Inventory> inventoryMap = new HashMap<>();
		for(Inventory inventory : invlist) {
			inventoryMap.put(inventory.getGeneratedBarcode(), inventory);
		}
		List<GroupPickingItemExport> exportItems = new ArrayList<>();
		for(PickingItemInfo item : list) {
			List<WarehouseLevel> levels = null;
			levels = warehouseLevelService.getAllByGeneratedBarcodeAndType(item.getGeneratedBarcode(), WarehouseLevel.LevelType.PICKING);				
			List<String> locationNames = new ArrayList<>();
			for(WarehouseLevel level : levels) {
				locationNames.add(level.getLocationName());
			}	
			Inventory inventory = inventoryMap.get(item.getGeneratedBarcode());
			GroupPickingItemExport itemExport = GroupPickingItemExport.builder()
					.setDescription(inventory.getTitle())
					.setGeneratedBarcode(item.getGeneratedBarcode())
					.setLocations(locationNames)
					.setOption(inventory.getProductOption())
					.setStock(Integer.valueOf(inventory.getStock()))
					.setProductType(inventory.getProductType())
					.setQuantity(item.getOrderQuantity())
					.createOrderItemExport();
			exportItems.add(itemExport);
		}
		exportItems = PickingSorting.pickingItemSortByLevel(exportItems);
		List<PickingItemInfo> returnList = new ArrayList<>();
		for(GroupPickingItemExport itemExport : exportItems){
			returnList.add(listMap.get(itemExport.getGeneratedBarcode()));
		}
		
		return new ResponseEntity<>(returnList, HttpStatus.OK);
	}
	
	// Add missing item 
	@RequestMapping(method=RequestMethod.POST, value="/{pickingJobId}/timeline/{timelineId}/pickingItemInfos/missingItems")
	ResponseEntity<List<PickingItem>> addMissingItems(@PathVariable Long pickingJobId, @PathVariable Long timelineId,  @RequestParam final String pickingDate, @RequestParam final String pickingTime, @RequestBody List<PickingItemInfo> infos,
			HttpServletRequest request) throws WrongParameterException, PickingJobTimelineNotFoundException {
		
		if(StringUtils.isEmpty(pickingDate) || StringUtils.isEmpty(pickingTime)) {
			throw new WrongParameterException("You passed wrong parameters");
		}
		
		DateFormat df = new SimpleDateFormat("M/d/yy h:mm aa");
		Date pickingDateTime = null;
		try {
			pickingDateTime = df.parse(pickingDate + " " + pickingTime);			
		} catch (ParseException e) {
			e.printStackTrace();
			throw new WrongParameterException("You passed wrong parameter for date and time");
		}
		
		PickingJobTimeline timeline = pickingJobTimelineService.getOne(timelineId);
		PickingJob pickingJob = pickingJobService.getOne(pickingJobId);

		List<PickingItem> itemList = new ArrayList<>();
		for(PickingItemInfo info : infos) {
			Integer totalQty = info.getOrderQuantity();
			Integer missedQty = info.getMissedQuantity();
			Integer pickedQty = totalQty - missedQty;
			
			if(missedQty > 0){
				final PickingItem missingItem = PickingItem.builder(timeline, null)
						.setGeneratedBarcode(info.getGeneratedBarcode())
						.setPicker(timeline.getWarehouseEmployee())
						.setPickingJob(pickingJob)
						.setQuantity(missedQty)
						.setRegDate(new Date())
						.setStatus(PickingItem.PickingItemStatus.MISSED)
						.createPickingItem();
				itemList.add(missingItem);
			}
			if(pickedQty > 0) {
				final PickingItem pickingItem = PickingItem.builder(timeline, null)
						.setGeneratedBarcode(info.getGeneratedBarcode())
						.setPicker(timeline.getWarehouseEmployee())
						.setPickingJob(pickingJob)
						.setQuantity(pickedQty)
						.setRegDate(new Date())
						.setStatus(PickingItem.PickingItemStatus.PICKED_WITHOUT_SCAN)
						.createPickingItem();
				itemList.add(pickingItem);
			}
		}
		List<PickingItem> returnList = pickingItemService.saveItems(itemList);
		pickingJobTimelineService.addPickingJobToEnd(pickingJob, timeline.getWarehouseEmployee(), PickingJobStatus.FINISHED, pickingDateTime);

		return new ResponseEntity<>(returnList, HttpStatus.OK);
	}
	
	// request missing list pdf
	@RequestMapping(method=RequestMethod.POST, value="/pickingJobGroup/{pickingJobGroupId}/missingItemPdf")
	ResponseEntity<PickingJobGroup> requestMissingPdf(@PathVariable Long pickingJobGroupId, HttpServletRequest request) {

		Map<String, PickingGroup> pickingGroupTable = pickingGroupService.getPickingGroupTable();
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee regBy = warehouseEmployeeService.getOneByGid(gid);
		PickingJobGroup pickingJobGroup = pickingJobGroupService.getOne(pickingJobGroupId);
		try {
			pickingJobService.createMissingList(pickingJobGroup, regBy, pickingGroupTable);	
		} catch (Exception e) {

		}
		return new ResponseEntity<>(pickingJobGroup, HttpStatus.OK);
	}
	
	
	@Autowired private PickingItemInfoService pickingItemInfoService;
	@Autowired private WarehouseItemService warehouseItemService;
	@Autowired private InventoryRestHelper inventoryRestHelper;
	@Autowired private WarehouseLevelService warehouseLevelService;
	@Autowired private WarehouseItemBoxService warehouseItemBoxService;
	@Autowired private PickingJobTimelineReportService pickingJobTimelineReportService;
	@Autowired private PickingItemService pickingItemService;
	@Autowired private PickingJobTimelineService pickingJobTimelineService;
	@Autowired private PickingGroupService pickingGroupService;
	@Autowired private PickingJobService pickingJobService;
	@Autowired private PickingJobGroupService pickingJobGroupService;
	@Autowired private WarehouseEmployeeService warehouseEmployeeService;

	@Value("${page.default.num}")
	protected Integer DEFAULT_NUM_PER_PAGE;

}