package com.samsbeauty.warehouse.adjustment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samsbeauty.warehouse.adjustment.model.AdjustmentRequest;
import com.samsbeauty.warehouse.adjustment.service.AdjustmentRequestService;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.employee.service.WarehouseEmployeeService;

@RestController
@RequestMapping("/api/adjustmentRequest")
public class AdjustmentRequestController {

	@Autowired private AdjustmentRequestService adjustmentRequestService;
	@Autowired private WarehouseEmployeeService warehouseEmployeeService;
	
	@Value("${page.default.num}")
	protected Integer DEFAULT_NUM_PER_PAGE;

	// Retrieve All Resources
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<AdjustmentRequest>> getAll() {		
		List<AdjustmentRequest> adjustmentRequestList = adjustmentRequestService.getAll();
		return new ResponseEntity<>(adjustmentRequestList, HttpStatus.OK);
	}


	@RequestMapping(method=RequestMethod.PUT)
	AdjustmentRequest update(@RequestBody @Valid final AdjustmentRequest adjustmentRequest) {
		AdjustmentRequest updateAdjustmentRequest = adjustmentRequestService.getOne(adjustmentRequest.getRequestId());
		//Implement here
		return updateAdjustmentRequest;
	}
	@RequestMapping(method=RequestMethod.PUT, value="/ids/{requestIds}/process")
	List<AdjustmentRequest> processMulti(@PathVariable String requestIds, HttpServletRequest request) {
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee modBy = warehouseEmployeeService.getOneByGid(gid);
		List<AdjustmentRequest> requests = new ArrayList<>();
		List<String> ids = Arrays.asList(requestIds.split(","));
		for(String id : ids) {
			Long requestId = Long.valueOf(id);
			AdjustmentRequest ar = adjustmentRequestService.getOne(requestId);
			AdjustmentRequest mod = AdjustmentRequest.builder(ar.getPickingItem())
					.setModBy(modBy)
					.setModDate(new Date())
					.setReason(ar.getReason())
					.setRegBy(ar.getRegBy())
					.setRegDate(ar.getRegDate())
					.setRequestId(ar.getRequestId())
					.setStatus(AdjustmentRequest.Status.PROCESSED)
					.createAdjustmentRequest();
			adjustmentRequestService.save(mod);
			requests.add(mod);
		}
		
		return requests;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{requestId}/process")
	AdjustmentRequest process(@PathVariable Long requestId, HttpServletRequest request) {
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee modBy = warehouseEmployeeService.getOneByGid(gid);
		AdjustmentRequest ar = adjustmentRequestService.getOne(requestId);
		AdjustmentRequest mod = AdjustmentRequest.builder(ar.getPickingItem())
				.setModBy(modBy)
				.setModDate(new Date())
				.setReason(ar.getReason())
				.setRegBy(ar.getRegBy())
				.setRegDate(ar.getRegDate())
				.setRequestId(ar.getRequestId())
				.setStatus(AdjustmentRequest.Status.PROCESSED)
				.createAdjustmentRequest();
		return adjustmentRequestService.save(mod);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{requestId}/complete")
	AdjustmentRequest complete(@PathVariable Long requestId, HttpServletRequest request) {
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee modBy = warehouseEmployeeService.getOneByGid(gid);
		AdjustmentRequest ar = adjustmentRequestService.getOne(requestId);
		AdjustmentRequest mod = AdjustmentRequest.builder(ar.getPickingItem())
				.setModBy(modBy)
				.setModDate(new Date())
				.setReason(ar.getReason())
				.setRegBy(ar.getRegBy())
				.setRegDate(ar.getRegDate())
				.setRequestId(ar.getRequestId())
				.setStatus(AdjustmentRequest.Status.COMPLETED)
				.createAdjustmentRequest();
		return adjustmentRequestService.save(mod);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/ids/{requestIds}/complete")
	List<AdjustmentRequest> completeMulti(@PathVariable String requestIds, HttpServletRequest request) {
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee modBy = warehouseEmployeeService.getOneByGid(gid);
		List<AdjustmentRequest> requests = new ArrayList<>();
		List<String> ids = Arrays.asList(requestIds.split(","));
		for(String id : ids) {
			Long requestId = Long.valueOf(id);
			AdjustmentRequest ar = adjustmentRequestService.getOne(requestId);
			AdjustmentRequest mod = AdjustmentRequest.builder(ar.getPickingItem())
					.setModBy(modBy)
					.setModDate(new Date())
					.setReason(ar.getReason())
					.setRegBy(ar.getRegBy())
					.setRegDate(ar.getRegDate())
					.setRequestId(ar.getRequestId())
					.setStatus(AdjustmentRequest.Status.COMPLETED)
					.createAdjustmentRequest();
			adjustmentRequestService.save(mod);
			requests.add(mod);
		}
		
		return requests;
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{requestId}")
	AdjustmentRequest delete(@PathVariable Long requestId) {
		AdjustmentRequest deleteAdjustmentRequest = adjustmentRequestService.getOne(requestId);
		adjustmentRequestService.delete(requestId);
		return  deleteAdjustmentRequest;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/ids/{requestIds}")
	ResponseEntity<List<AdjustmentRequest>> deleteByIds(@PathVariable String requestIds, HttpServletRequest request) {
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee modEmployee = warehouseEmployeeService.getOneByGid(gid);
		List<String> ids = Arrays.asList(requestIds.split(","));
		List<AdjustmentRequest> deletedList = new ArrayList<>();
		for(String id : ids) {
			Long requestId = Long.valueOf(id);
			AdjustmentRequest deleteReq = adjustmentRequestService.getOne(requestId);
			deletedList.add(deleteReq);
			deleteReq.delete(modEmployee, new Date());
			adjustmentRequestService.save(deleteReq);
		}
		return new ResponseEntity<>(deletedList, HttpStatus.OK);
	}
}