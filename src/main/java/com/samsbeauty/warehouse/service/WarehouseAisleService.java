package com.samsbeauty.warehouse.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.model.WarehouseAisle;

@PreAuthorize("isAuthenticated()")
public interface WarehouseAisleService {
	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	Page<WarehouseAisle> getAll(Integer pageNumber, Integer pageSize, Sort sort);

	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	Page<WarehouseAisle> getAllByWarehouseId(Long warehouseId, Integer pageNumber, Integer pageSize, Sort sort);

	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	List<WarehouseAisle> getAllByWarehouseId(Long warehouseId);

	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	List<WarehouseAisle> getAllByWarehouseCode(String warehouseCode);
	
	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	WarehouseAisle getOne(Long aisleId);

	@PreAuthorize("hasAuthority('admin.warehouse.write') or hasAuthority('all.warehouse.write')")	
	WarehouseAisle save(WarehouseAisle warehouseAisle);
	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	Integer getMaxCode(Long warehouseId);
	@PreAuthorize("hasAuthority('admin.warehouse.write') or hasAuthority('all.warehouse.write')")
	void delete(Long aisleId);
}