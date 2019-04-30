package com.samsbeauty.warehouse.picking.report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.samsbeauty.warehouse.DateUtil;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.employee.service.WarehouseEmployeeService;
import com.samsbeauty.warehouse.exception.rest.WarehouseEmployeeNotFoundException;
import com.samsbeauty.warehouse.exception.rest.WrongDateFormatException;
import com.samsbeauty.warehouse.exception.rest.WrongParameterException;
import com.samsbeauty.warehouse.picking.report.model.AveragePickingSummary;
import com.samsbeauty.warehouse.picking.report.model.PickingJobTimelineReport;
import com.samsbeauty.warehouse.picking.report.service.PickingJobTimelineReportService;

@RestController
@RequestMapping("/api/pickingJobTimelineReport")
public class PickingJobTimelineReportController {

	@Autowired private PickingJobTimelineReportService pickingJobTimelineReportService;
	@Autowired private WarehouseEmployeeService warehouseEmployeeService;

	@Value("${page.default.num}")
	protected Integer DEFAULT_NUM_PER_PAGE;

	// Retrieve All Resources
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<Page<PickingJobTimelineReport>> getAll(
					@RequestParam(name="pageSize", required=false) Integer pageSize,
					@RequestParam(name="page", required=false) Integer page,
					@RequestParam(name="sortingDirection", required=false) String sortingDirection,
					@RequestParam(name="sortingFields", required=false) String sortingField,
					@RequestParam(name="reportDate", required=false) String reportDate) throws WrongParameterException, WrongDateFormatException {

		
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
			sort = new Sort(Sort.Direction.DESC, "reportId");
		}

		SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DATE_FORMAT_REPORT);
		Date dateFrom = null;
		Date dateTo = null;
		
		if(reportDate == null) {
			dateFrom = new Date();
		} else {
			try {
				dateFrom = (Date) formatter.parse(reportDate);
			} catch (ParseException e) {
				throw new WrongDateFormatException("You passed a wrong format of searchDateFrom. The format of date must be {mm/dd/yyyy}");
			}
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateFrom);
		cal.add(Calendar.MONTH, 1);
		dateTo = cal.getTime();
		
		Page<PickingJobTimelineReport> pickingJobTimelineReportList = pickingJobTimelineReportService.getAll(dateFrom, dateTo, page, pageSize, sort);
		return new ResponseEntity<Page<PickingJobTimelineReport>>(pickingJobTimelineReportList, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/individual/{employeeId}")
	ResponseEntity<Map<String, Object>> getIndividualData( 
			@PathVariable final Long employeeId,
    		@RequestParam("reportDate") final String reportDate,
    		HttpServletRequest request) throws WarehouseEmployeeNotFoundException, WrongDateFormatException {
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee employee = warehouseEmployeeService.getOneByGid(gid);
		if(employee == null) {
			throw new WarehouseEmployeeNotFoundException("You are not authroized to view this page!");
		}
		
		WarehouseEmployee picker = warehouseEmployeeService.getOne(employeeId);
		if(picker == null) {
			throw new WarehouseEmployeeNotFoundException("Cannot find an employee");
		}

    	SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DATE_FORMAT_REPORT);
		Date dateFrom = null;
		Date dateTo = null;
		
		if(reportDate == null) {
			dateFrom = new Date();
		} else {
			try {
				dateFrom = (Date) formatter.parse(reportDate);
			} catch (ParseException e) {
				throw new WrongDateFormatException("You passed a wrong format of searchDateFrom. The format of date must be {mm/dd/yyyy}");
			}
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateFrom);
		cal.add(Calendar.MONTH, 1);
		dateTo = cal.getTime();
    	
    	PickingJobTimelineReport pickerReport = pickingJobTimelineReportService.getReportForPicker(dateFrom, dateTo, picker.getWarehouseEmployeeId());
    	AveragePickingSummary averageReport = pickingJobTimelineReportService.getReportForAvg(dateFrom, dateTo);
    	Integer totalPicked = pickerReport.getTotalPicked();
    	Integer totalPickedUnique = pickerReport.getTotalPickedUnique();
    	Integer totalMissed = pickerReport.getTotalMissed();
    	Integer totalMissedUnique = pickerReport.getTotalMissedUnique();
    	Integer totalPickedWithoutScan = pickerReport.getTotalPickedWithoutScan();
    	Integer totalPickedWithoutScanUnique = pickerReport.getTotalPickedWithoutScanUnique();
    	Integer totalWrongLocation = pickerReport.getTotalWrongLocation();
    	Integer totalWrongLocationUnique = pickerReport.getTotalWrongLocationUnique();
    	
    	Double avgTotalPicked = averageReport.getAvgTotalPicked().doubleValue();
    	Double avgTotalPickedUnique = averageReport.getAvgTotalPickedUnique().doubleValue();
    	Double avgTotalMissed = averageReport.getAvgTotalMissed().doubleValue();
    	Double avgTotalMissedUnique = averageReport.getAvgTotalMissedUnique().doubleValue();
    	Double avgTotalPickedWithoutScan = averageReport.getAvgTotalPickedWithoutScan().doubleValue();
    	Double avgTotalPickedWithoutScanUnique = averageReport.getAvgTotalPickedWithoutScanUnique().doubleValue();
    	Double avgTotalWrongLocation = averageReport.getAvgTotalWrongLocation().doubleValue();
    	Double avgTotalWrongLocationUnique = averageReport.getAvgTotalWrongLocationUnique().doubleValue();
    	
    	Map<String, Object> map = new HashMap<>();
    	map.put("totalPicked",totalPicked);
    	map.put("totalPickedUnique",totalPickedUnique);
    	map.put("totalMissed",totalMissed);
    	map.put("totalMissedUnique",totalMissedUnique);
    	map.put("totalPickedWithoutScan",totalPickedWithoutScan);
    	map.put("totalPickedWithoutScanUnique",totalPickedWithoutScanUnique);
    	map.put("totalWrongLocation",totalWrongLocation);
    	map.put("totalWrongLocationUnique",totalWrongLocationUnique);
    	
    	map.put("avgTotalPicked",avgTotalPicked);
    	map.put("avgTotalPickedUnique",avgTotalPickedUnique);
    	map.put("avgTotalMissed",avgTotalMissed);
    	map.put("avgTotalMissedUnique",avgTotalMissedUnique);
    	map.put("avgTotalPickedWithoutScan",avgTotalPickedWithoutScan);
    	map.put("avgTotalPickedWithoutScanUnique",avgTotalPickedWithoutScanUnique);
    	map.put("avgTotalWrongLocation",avgTotalWrongLocation);
    	map.put("avgTotalWrongLocationUnique",avgTotalWrongLocationUnique);
    	
    	map.put("pickerName", picker.getName());
    	
    	return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST)
	PickingJobTimelineReport add(@RequestBody @Valid final PickingJobTimelineReport pickingJobTimelineReport) {
		PickingJobTimelineReport newPickingJobTimelineReport = new PickingJobTimelineReport();
		//Implement here
		return pickingJobTimelineReportService.save(newPickingJobTimelineReport);
	}

	@RequestMapping(method=RequestMethod.PUT)
	PickingJobTimelineReport update(@RequestBody @Valid final PickingJobTimelineReport pickingJobTimelineReport) {
		PickingJobTimelineReport updatePickingJobTimelineReport = pickingJobTimelineReportService.getOne(pickingJobTimelineReport.getReportId());
		//Implement here
		return updatePickingJobTimelineReport;
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{reportId}")
	PickingJobTimelineReport delete(@PathVariable Long reportId) {
		PickingJobTimelineReport deletePickingJobTimelineReport = pickingJobTimelineReportService.getOne(reportId);
		pickingJobTimelineReportService.delete(reportId);
		return  deletePickingJobTimelineReport;
	}

}