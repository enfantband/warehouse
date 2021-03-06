package com.samsbeauty.warehouse.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.samsbeauty.warehouse.exception.rest.WrongParameterException;
import com.samsbeauty.warehouse.model.Walkable;
import com.samsbeauty.warehouse.model.Warehouse;
import com.samsbeauty.warehouse.model.WarehouseAisle;
import com.samsbeauty.warehouse.model.WarehouseGeoInfo;
import com.samsbeauty.warehouse.service.WarehouseAisleService;
import com.samsbeauty.warehouse.service.WarehouseService;
import com.samsbeauty.warehouse.tag.model.WarehouseTag;
import com.samsbeauty.warehouse.util.LocationUtil;

@RestController
@RequestMapping("/api/warehouse/aisle")
public class WarehouseAisleController {

	@Autowired
	private WarehouseAisleService warehouseAisleService;
	
	@Autowired
	private WarehouseService warehouseService;
	
	@Value("${page.default.num}")
	protected Integer DEFAULT_NUM_PER_PAGE;

	@RequestMapping(method=RequestMethod.GET, value="/warehouse/{warehouseId}")
	ResponseEntity<List<WarehouseAisle>> getAllByWarehouse (@PathVariable Long warehouseId) {
		return new ResponseEntity<List<WarehouseAisle>>(warehouseAisleService.getAllByWarehouseId(warehouseId), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/warehouse/code/{warehouseCode}")
	ResponseEntity<List<WarehouseAisle>> getAllByWarehouseCode (@PathVariable String warehouseCode) {
		return new ResponseEntity<List<WarehouseAisle>>(warehouseAisleService.getAllByWarehouseCode(warehouseCode), HttpStatus.OK);
	}
	
	// Retrieve All Resources
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<Page<WarehouseAisle>> getAll(
					@RequestParam(name="warehouseId", required=false) Long warehouseId,
					@RequestParam(name="pageSize", required=false) Integer pageSize,
					@RequestParam(name="page", required=false) Integer page,
					@RequestParam(name="sortingDirections", required=false) String sortingDirection,
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
			sort = new Sort(Sort.Direction.DESC, "aisleId");
		}
		Page<WarehouseAisle> warehouseAisleList = null;
		if(warehouseId != null) {
			warehouseAisleList = warehouseAisleService.getAllByWarehouseId(warehouseId, page, pageSize, sort);
		} else {
			warehouseAisleList = warehouseAisleService.getAll(page, pageSize, sort);
		}
		 
		return new ResponseEntity<Page<WarehouseAisle>>(warehouseAisleList, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, value="/warehouse/{warehouseId}/aisle")
	WarehouseAisle add(@PathVariable final Long warehouseId, @RequestBody @Valid final WarehouseAisle warehouseAisle) {
		Warehouse warehouse = warehouseService.getOne(warehouseId);
		WarehouseTag warehouseTag = WarehouseTag.builder()
				.setDescription(warehouseAisle.getWarehouseTag().getDescription())
				.setName(WarehouseTag.NameCode.AISLE)
				.setTag(warehouseAisle.getWarehouseTag().getTag())
				.createWarehouseTag();
		
		WarehouseGeoInfo warehouseGeoInfo = WarehouseGeoInfo.builder()
				.setEndCoordX(BigDecimal.valueOf(0.00))
				.setEndCoordY(BigDecimal.valueOf(0.00))
				.setStartCoordX(BigDecimal.valueOf(0.00))
				.setStartCoordY(BigDecimal.valueOf(0.00))
				.setWalkable(Walkable.Y)
				.createWarehouseGeoInfo();
				
		
		WarehouseAisle newWarehouseAisle = WarehouseAisle.builder(warehouseTag, warehouseGeoInfo)
				.setAisleCode(LocationUtil.getIncreaseCode(warehouseAisleService.getMaxCode(warehouseId), 3))
				.setWarehouse(warehouse)
				.createWarehouseAisle();
		//Implement here
		return warehouseAisleService.save(newWarehouseAisle);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/warehouse/{warehouseId}/aisle")
	WarehouseAisle update(@RequestBody @Valid final WarehouseAisle warehouseAisle) {
		WarehouseAisle updateWarehouseAisle = warehouseAisleService.getOne(warehouseAisle.getAisleId());
		
		updateWarehouseAisle.getWarehouseTag().update(warehouseAisle.getWarehouseTag().getTag(), warehouseAisle.getWarehouseTag().getDescription());
		//Implement here
		return warehouseAisleService.save(updateWarehouseAisle);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{aisleId}")
	WarehouseAisle delete(@PathVariable Long aisleId) {
		WarehouseAisle deleteWarehouseAisle = warehouseAisleService.getOne(aisleId);
		deleteWarehouseAisle.deactivate();
		return  warehouseAisleService.save(deleteWarehouseAisle);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/ids/{aisleIds}")
	ResponseEntity<List<WarehouseAisle>> deleteByIds(@PathVariable String aisleIds) {
		List<String> ids = Arrays.asList(aisleIds.split(","));
		List<WarehouseAisle> deletedList = new ArrayList<>();
		for(String id : ids) {
			Long aisleId = Long.valueOf(id);
			WarehouseAisle warehouseAisle = warehouseAisleService.getOne(aisleId);
			deletedList.add(warehouseAisle);
			warehouseAisle.deactivate();
			warehouseAisleService.save(warehouseAisle);
		}
		return new ResponseEntity<List<WarehouseAisle>>(deletedList, HttpStatus.OK);
	}

}