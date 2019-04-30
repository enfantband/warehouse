package com.samsbeauty.warehouse.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samsbeauty.warehouse.model.Walkable;
import com.samsbeauty.warehouse.model.WarehouseAisle;
import com.samsbeauty.warehouse.model.WarehouseGeoInfo;
import com.samsbeauty.warehouse.model.WarehouseGroup;
import com.samsbeauty.warehouse.service.WarehouseAisleService;
import com.samsbeauty.warehouse.service.WarehouseGroupService;
import com.samsbeauty.warehouse.tag.model.WarehouseTag;
import com.samsbeauty.warehouse.util.LocationUtil;

@RestController
@RequestMapping("/api/warehouse/group")
public class WarehouseGroupController {

	@Autowired
	private WarehouseGroupService warehouseGroupService;
	
	@Autowired
	private WarehouseAisleService warehouseAisleService;

	@Value("${page.default.num}")
	protected Integer DEFAULT_NUM_PER_PAGE;

	@RequestMapping(method=RequestMethod.GET, value="/aisle/code/{aisleCode}")
	ResponseEntity<List<WarehouseGroup>> getAllByAisleCode(@PathVariable String aisleCode) {
		return new ResponseEntity<List<WarehouseGroup>>(warehouseGroupService.getAllByAisleCode(aisleCode), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/aisle/{aisleId}")
	ResponseEntity<List<WarehouseGroup>> getAllByAisleId(@PathVariable Long aisleId) {
		return new ResponseEntity<List<WarehouseGroup>>(warehouseGroupService.getAllByAisleId(aisleId), HttpStatus.OK);
	}
	
	// Retrieve All Resources
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<WarehouseGroup>> getAll(@RequestParam(name="aisleId", required=false) Long aisleId){
		List<WarehouseGroup> warehouseGroupList;
		if(aisleId != null){
			warehouseGroupList = warehouseGroupService.getAllByAisleId(aisleId);
		} else {
			warehouseGroupList = warehouseGroupService.getAll();
		}
		return new ResponseEntity<>(warehouseGroupList, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, value="/aisle/{aisleId}")
	WarehouseGroup add(@PathVariable Long aisleId, @RequestBody @Valid final WarehouseGroup warehouseGroup) {
		WarehouseAisle aisle = warehouseAisleService.getOne(aisleId);
		WarehouseTag warehouseTag = WarehouseTag.builder()
				.setDescription(warehouseGroup.getWarehouseTag().getDescription())
				.setName(WarehouseTag.NameCode.AISLE)
				.setTag(warehouseGroup.getWarehouseTag().getTag())
				.createWarehouseTag();
		
		WarehouseGeoInfo warehouseGeoInfo = WarehouseGeoInfo.builder()
				.setEndCoordX(BigDecimal.valueOf(0.00))
				.setEndCoordY(BigDecimal.valueOf(0.00))
				.setStartCoordX(BigDecimal.valueOf(0.00))
				.setStartCoordY(BigDecimal.valueOf(0.00))
				.setWalkable(Walkable.Y)
				.createWarehouseGeoInfo();
		
		WarehouseGroup newWarehouseGroup = WarehouseGroup.builder(warehouseTag, warehouseGeoInfo)
				.setGroupCode(LocationUtil.getIncreaseCode(warehouseGroupService.getMaxCode(aisleId), 3))
				.setModDate(new Date())
				.setRegDate(new Date())
				.setWarehouseAisles(Arrays.asList(aisle))
				.createWarehouseGroup();
				
		//Implement here
		return warehouseGroupService.save(newWarehouseGroup);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/aisle/{aisleId}")
	WarehouseGroup update(@RequestBody @Valid final WarehouseGroup warehouseGroup) {
		WarehouseGroup updateWarehouseGroup = warehouseGroupService.getOne(warehouseGroup.getGroupId());
		updateWarehouseGroup.getWarehouseTag().update(warehouseGroup.getWarehouseTag().getTag(), warehouseGroup.getWarehouseTag().getDescription());
		return warehouseGroupService.save(updateWarehouseGroup);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{groupId}")
	WarehouseGroup delete(@PathVariable Long groupId) {
		WarehouseGroup deleteWarehouseGroup = warehouseGroupService.getOne(groupId);
		deleteWarehouseGroup.deactivate();		
		return warehouseGroupService.save(deleteWarehouseGroup);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/ids/{groupIds}")
	ResponseEntity<List<WarehouseGroup>> deleteByIds(@PathVariable String groupIds) {
		List<String> ids = Arrays.asList(groupIds.split(","));
		List<WarehouseGroup> deletedList = new ArrayList<>();
		for(String id : ids) {
			Long groupId = Long.valueOf(id);
			WarehouseGroup deletedWarehouseGroup = warehouseGroupService.getOne(groupId);
			deletedList.add(deletedWarehouseGroup);
			deletedWarehouseGroup.deactivate();
			warehouseGroupService.save(deletedWarehouseGroup);
		}
		return new ResponseEntity<List<WarehouseGroup>> (deletedList, HttpStatus.OK);
	}

}