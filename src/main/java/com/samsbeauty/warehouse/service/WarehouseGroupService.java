package com.samsbeauty.warehouse.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.model.WarehouseGroup;

@PreAuthorize("isAuthenticated()")
public interface WarehouseGroupService {

	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	Page<WarehouseGroup> getAll(Integer pageNumber, Integer pageSize, Sort sort);

	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	List<WarehouseGroup> getAll();

	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	Page<WarehouseGroup> getAllByAisleId(Long aisleId, Integer pageNumber, Integer pageSize, Sort sort);

	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	List<WarehouseGroup> getAllByAisleId(Long aisleId);

	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	List<WarehouseGroup> getAllByAisleCode(String aisleCode);

	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	WarehouseGroup getOne(Long groupId);

	@PreAuthorize("hasAuthority('admin.warehouse.write') or hasAuthority('all.warehouse.write')")
	WarehouseGroup save(WarehouseGroup warehouseGroup);
	@PreAuthorize("hasAuthority('admin.warehouse.write') or hasAuthority('all.warehouse.write')")
	void delete(Long groupId);
	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	Integer getMaxCode(Long aisleId);
}