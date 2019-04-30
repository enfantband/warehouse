package com.samsbeauty.warehouse.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.exception.rest.TooManyResultsException;
import com.samsbeauty.warehouse.model.WarehouseItem;
import com.samsbeauty.warehouse.model.WarehouseItemBox;
import com.samsbeauty.warehouse.model.WarehouseLevel;
import com.samsbeauty.warehouse.param.WarehouseItemParam;

@PreAuthorize("isAuthenticated()")
public interface WarehouseItemService {
	Page<WarehouseItem> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	Page<WarehouseItem> getAllByLevelId(Long levelId, Integer pageNumber, Integer pageSize, Sort sort);
	List<WarehouseItem> getAllByLevelId(Long levelId);
	List<WarehouseItem> getAllByItemBoxId(Integer itemBoxId);
	Page<WarehouseItem> getAllByParam(WarehouseItemParam param, Integer pageNumber, Integer pageSize, Sort sort) throws TooManyResultsException;
	WarehouseItem getOne(Integer itemId);
	WarehouseItem getOneByGeneratedBarcode(String barcode);
	WarehouseItem save(WarehouseItem warehouseItem);
	WarehouseItem transfer(WarehouseItem warehouseItem, WarehouseLevel toLevel, WarehouseEmployee modBy);
	WarehouseItem transferWithBox(WarehouseItem warehouseItem, WarehouseLevel toLevel, WarehouseItemBox toBox, WarehouseEmployee modBy);
	void delete(Integer itemId);
	void outbound(WarehouseItem warehouseItem, Integer quantity, WarehouseEmployee modBy);
	void inbound(WarehouseItem warehouseItem, Integer quantity, WarehouseEmployee modBy);
	boolean exists(String generatedBarcode, Long levelId);
	boolean exists(String generatedBarocde, Long levelId, Integer itemBoxId);
}