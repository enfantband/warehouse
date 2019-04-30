package com.samsbeauty.warehouse.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.exception.rest.LocationNotFoundException;
import com.samsbeauty.warehouse.model.WarehouseLevel;

@PreAuthorize("isAuthenticated()")
public interface WarehouseLevelService {
	Page<WarehouseLevel> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	Page<WarehouseLevel> getAllByLocationBarcode(String locationBarcode, Integer pageNumber, Integer pageSize, Sort sort);
	List<WarehouseLevel> getAllByLocationBarcode(String locationBarcode);
	Page<WarehouseLevel> getAllByTag(Long warehouseId, Long aisleId, Long groupId, Long subgroupId, String tag, Integer pageNumber, Integer pageSize, Sort sort);
	List<WarehouseLevel> getAllByGeneratedBarcode(String generatedBarcode);
	List<WarehouseLevel> getAllByGeneratedBarcodeAndType(String generatedBarcode, String levelType);
	WarehouseLevel getOne(Long levelId);
	WarehouseLevel getOneByLocationBarcode(String locationBarcode) throws LocationNotFoundException;
	WarehouseLevel save(WarehouseLevel warehouseLevel);
	void delete(Long levelId);
	Integer getMaxCode(Long subgroupId);
}