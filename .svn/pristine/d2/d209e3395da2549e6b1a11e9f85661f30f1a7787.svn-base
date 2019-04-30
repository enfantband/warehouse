package com.samsbeauty.warehouse.picking.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.picking.model.PickingItemInfo;

@PreAuthorize("isAuthenticated()")
public interface PickingItemInfoService {
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	Page<PickingItemInfo> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	List<PickingItemInfo> getAllByPickingJobIdGroupByGeneratedBarcode(Long pickingJobId);

	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	PickingItemInfo getOne(Long pickingItemInfoId);

	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	PickingItemInfo save(PickingItemInfo pickingItemInfo);

	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	void delete(Long pickingItemInfoId);
}