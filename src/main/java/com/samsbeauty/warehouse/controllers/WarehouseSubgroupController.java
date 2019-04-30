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
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samsbeauty.warehouse.exception.rest.WarehouseGroupNotFoundException;
import com.samsbeauty.warehouse.exception.rest.WrongParameterException;
import com.samsbeauty.warehouse.model.Walkable;
import com.samsbeauty.warehouse.model.WarehouseGeoInfo;
import com.samsbeauty.warehouse.model.WarehouseGroup;
import com.samsbeauty.warehouse.model.WarehouseSubgroup;
import com.samsbeauty.warehouse.service.WarehouseGroupService;
import com.samsbeauty.warehouse.service.WarehouseSubgroupService;
import com.samsbeauty.warehouse.tag.model.WarehouseTag;
import com.samsbeauty.warehouse.util.LocationUtil;

@RestController
@RequestMapping("/api/warehouse/subgroup")
public class WarehouseSubgroupController {

	@Autowired
	private WarehouseSubgroupService warehouseSubgroupService;
	
	@Autowired
	private WarehouseGroupService warehouseGroupService;

	@Value("${page.default.num}")
	protected Integer DEFAULT_NUM_PER_PAGE;

	@RequestMapping(method=RequestMethod.GET, value="/group/code/{groupCode}")
	ResponseEntity<List<WarehouseSubgroup>> getAllByGroupId(@PathVariable String groupCode) {
		return new ResponseEntity<List<WarehouseSubgroup>>(warehouseSubgroupService.getAllByGroupCode(groupCode), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/group/{groupId}")
	ResponseEntity<List<WarehouseSubgroup>> getAllByGroupId(@PathVariable Long groupId) {
		return new ResponseEntity<List<WarehouseSubgroup>>(warehouseSubgroupService.getAllByGroupId(groupId), HttpStatus.OK);
	}
	
	// Retrieve All Resources
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<WarehouseSubgroup>> getAll(@RequestParam(name="groupId", required=false) Long groupId) throws WrongParameterException {		
		List<WarehouseSubgroup> warehouseSubgroupList = null;
		if(groupId != null) {
			warehouseSubgroupList = warehouseSubgroupService.getAllByGroupId(groupId);
		} else {
			warehouseSubgroupList = new ArrayList<>();	
		}
		return new ResponseEntity<>(warehouseSubgroupList, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, value="/group/{groupId}")
	WarehouseSubgroup add(@PathVariable Long groupId, @RequestBody @Valid final WarehouseSubgroup warehouseSubgroup) throws WarehouseGroupNotFoundException, HttpMessageNotWritableException {
		
		WarehouseGroup warehouseGroup = warehouseGroupService.getOne(groupId);
		if(warehouseGroup == null) {
			throw new WarehouseGroupNotFoundException("Cannot find the warehouse group");
		}
		WarehouseTag warehouseTag = WarehouseTag.builder()
				.setDescription(warehouseSubgroup.getWarehouseTag().getDescription())
				.setName(WarehouseTag.NameCode.AISLE)
				.setTag(warehouseSubgroup.getWarehouseTag().getTag())
				.createWarehouseTag();
		
		WarehouseGeoInfo warehouseGeoInfo = WarehouseGeoInfo.builder()
				.setEndCoordX(BigDecimal.valueOf(0.00))
				.setEndCoordY(BigDecimal.valueOf(0.00))
				.setStartCoordX(BigDecimal.valueOf(0.00))
				.setStartCoordY(BigDecimal.valueOf(0.00))
				.setWalkable(Walkable.Y)
				.createWarehouseGeoInfo();
		
		WarehouseSubgroup newWarehouseSubgroup = WarehouseSubgroup.builder(warehouseGroup, warehouseTag, warehouseGeoInfo)
				.setModDate(new Date())
				.setRegDate(new Date())
				.setSubgroupCode(LocationUtil.getIncreaseCode(warehouseSubgroupService.getMaxCode(groupId), 3))
				.createWarehouseSubgroup();
		//Implement here
		return warehouseSubgroupService.save(newWarehouseSubgroup);
	}

	@RequestMapping(method=RequestMethod.PUT)
	WarehouseSubgroup update(@RequestBody @Valid final WarehouseSubgroup warehouseSubgroup) {
		WarehouseSubgroup updateWarehouseSubgroup = warehouseSubgroupService.getOne(warehouseSubgroup.getSubgroupId());
		updateWarehouseSubgroup.getWarehouseTag().update(warehouseSubgroup.getWarehouseTag().getTag(), warehouseSubgroup.getWarehouseTag().getDescription());
		//Implement here
		return warehouseSubgroupService.save(updateWarehouseSubgroup);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{subgroupId}")
	WarehouseSubgroup delete(@PathVariable Long subgroupId) {
		WarehouseSubgroup deleteWarehouseSubgroup = warehouseSubgroupService.getOne(subgroupId);
		deleteWarehouseSubgroup.deactivate();
		return  warehouseSubgroupService.save(deleteWarehouseSubgroup);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/ids/{subgroupIds}")
	ResponseEntity<List<WarehouseSubgroup>> deleteByIds(@PathVariable String subgroupIds) {
		List<String> ids = Arrays.asList(subgroupIds.split(","));
		List<WarehouseSubgroup> deletedList = new ArrayList<>();
		for(String id : ids) {
			Long subgroupId = Long.valueOf(id);
			WarehouseSubgroup deleteSubgroup = warehouseSubgroupService.getOne(subgroupId);
			deletedList.add(deleteSubgroup);
			deleteSubgroup.deactivate();
			warehouseSubgroupService.save(deleteSubgroup);
		}
		return new ResponseEntity<List<WarehouseSubgroup>>(deletedList, HttpStatus.OK);
	}
}