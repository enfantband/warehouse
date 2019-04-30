package com.samsbeauty.warehouse.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.model.Warehouse;

@PreAuthorize("isAuthenticated()")
public interface WarehouseService {
	Integer getMaxCode();
	Page<Warehouse> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	List<Warehouse> getAll();
	Warehouse getOne(Long warehouseId);
	Warehouse save(Warehouse warehouse);
	void delete(Long warehouseId);
}