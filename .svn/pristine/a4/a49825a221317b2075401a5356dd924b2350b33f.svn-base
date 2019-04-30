package com.samsbeauty.warehouse.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.model.WarehouseItemBox;

@PreAuthorize("isAuthenticated()")
public interface WarehouseItemBoxService {
	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	Page<WarehouseItemBox> getAll(Integer pageNumber, Integer pageSize, Sort sort);

	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	Page<WarehouseItemBox> getAllByLevelId(Long levelId, Integer pageNumber, Integer pagesize, Sort sort);

	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	List<WarehouseItemBox> getAllByLevelId(Long levelId);

	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	WarehouseItemBox getOneByBoxPrefixAndBoxCodeAndLevelId(String boxCode, Long levelId);

	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	WarehouseItemBox getOne(Integer itemBoxId);

	@PreAuthorize("hasAuthority('admin.warehouse.write') or hasAuthority('all.warehouse.write')")
	WarehouseItemBox save(WarehouseItemBox warehouseItemBox);
	@PreAuthorize("hasAuthority('admin.warehouse.write') or hasAuthority('all.warehouse.write')")
	void delete(Integer itemBoxId);
	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	Integer getMaxCode(String boxPrefix);
}