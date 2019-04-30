package com.samsbeauty.warehouse.picking.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.picking.model.PickingJobGroup;

@PreAuthorize("isAuthenticated()")
public interface PickingJobGroupService {
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	Page<PickingJobGroup> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	Page<PickingJobGroup> getAll(Integer pageNumber, Integer pageSize, Sort sort, Date searchDateFrom, Date searchDateTo);
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	PickingJobGroup getOne(Long pickingJobGroupId);
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	PickingJobGroup save(PickingJobGroup pickingJobGroup);
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	void delete(Long pickingJobGroupId);
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	public Integer getMaxPickingSet();
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	List<PickingJobGroup> getAll(Date dateFrom, Date dateTo);
}