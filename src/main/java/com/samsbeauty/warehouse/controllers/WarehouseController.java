package com.samsbeauty.warehouse.controllers;

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

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.employee.service.WarehouseEmployeeService;
import com.samsbeauty.warehouse.exception.rest.WrongParameterException;
import com.samsbeauty.warehouse.model.Warehouse;
import com.samsbeauty.warehouse.service.WarehouseService;
import com.samsbeauty.warehouse.tag.model.WarehouseTag;
import com.samsbeauty.warehouse.util.LocationUtil;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

	@Autowired
	private WarehouseService warehouseService;
	
	@Autowired
	private WarehouseEmployeeService warehouseEmployeeService;
	

	@Value("${page.default.num}")
	protected Integer DEFAULT_NUM_PER_PAGE;

	// Retrieve All Resources
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<Warehouse>> getAll() throws WrongParameterException {		
		List<Warehouse> warehouseList = warehouseService.getAll();
		return new ResponseEntity<List<Warehouse>>(warehouseList, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST)
	Warehouse add(@RequestBody @Valid final Warehouse warehouse, HttpServletRequest request) {
		WarehouseTag warehouseTag = WarehouseTag.builder()
				.setDescription(warehouse.getWarehouseTag().getDescription())
				.setName(WarehouseTag.NameCode.WAREHOUSE)
				.setTag(warehouse.getWarehouseTag().getTag())
				.createWarehouseTag();
		
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee regBy = warehouseEmployeeService.getOneByGid(gid);
		Warehouse newWarehouse = Warehouse.builder()
				.setRegDate(new Date())
				.setModDate(new Date())
				.setRegBy(regBy)
				.setModBy(regBy)
				.setWarehouseTag(warehouseTag)
				.setWarehouseCode(LocationUtil.getIncreaseCode(warehouseService.getMaxCode(), 2))
				.createWarehouse();
				
		//Implement here
		return warehouseService.save(newWarehouse);
	}

	@RequestMapping(method=RequestMethod.PUT)
	Warehouse update(@RequestBody @Valid final Warehouse warehouse, HttpServletRequest request) {
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee modEmployee = warehouseEmployeeService.getOneByGid(gid);
		Warehouse updateWarehouse = warehouseService.getOne(warehouse.getWarehouseId());
		updateWarehouse.getWarehouseTag().update(warehouse.getWarehouseTag().getTag(), warehouse.getWarehouseTag().getDescription());
		updateWarehouse.update(modEmployee);
		return warehouseService.save(updateWarehouse);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{warehouseId}")
	Warehouse delete(@PathVariable Long warehouseId, HttpServletRequest request) {
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee modEmployee = warehouseEmployeeService.getOneByGid(gid);
		Warehouse deleteWarehouse = warehouseService.getOne(warehouseId);
		deleteWarehouse.deactivate(modEmployee);
		return  warehouseService.save(deleteWarehouse);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/ids/{warehouseIds}")
	ResponseEntity<List<Warehouse>> deleteByIds(@PathVariable String warehouseIds, HttpServletRequest request) {
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee modEmployee = warehouseEmployeeService.getOneByGid(gid);
		List<String> ids = Arrays.asList(warehouseIds.split(","));
		List<Warehouse> deletedList = new ArrayList<>();
		for(String id : ids) {
			Long warehouseId = Long.valueOf(id);
			Warehouse deleteWarehouse = warehouseService.getOne(warehouseId);
			deletedList.add(deleteWarehouse);
			deleteWarehouse.deactivate(modEmployee);
			warehouseService.save(deleteWarehouse);
		}
		return new ResponseEntity<List<Warehouse>>(deletedList, HttpStatus.OK);
	}
	
}