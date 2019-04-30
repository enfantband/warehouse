package com.samsbeauty.warehouse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.model.WarehouseInbound;

public interface WarehouseInboundService {
	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	Page<WarehouseInbound> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	@PreAuthorize("hasAuthority('admin.warehouse.read') or hasAuthority('all.warehouse.read')")
	WarehouseInbound getOne(Long inboundId);
	@PreAuthorize("hasAuthority('admin.warehouse.write') or hasAuthority('all.warehouse.write')")
	WarehouseInbound save(WarehouseInbound warehouseInbound);
	@PreAuthorize("hasAuthority('admin.warehouse.write') or hasAuthority('all.warehouse.write')")
	void delete(Long inboundId);
}