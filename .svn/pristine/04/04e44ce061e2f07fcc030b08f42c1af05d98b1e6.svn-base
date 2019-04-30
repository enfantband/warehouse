package com.samsbeauty.warehouse.picking.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.picking.model.PickingGroup;

@PreAuthorize("isAuthenticated()")
public interface PickingGroupService {
	@PreAuthorize("hasAuthority('admin.pickingGroup.read') or hasAuthority('all.pickingGroup.read')")
	List<PickingGroup> getAll();
	@PreAuthorize("hasAuthority('admin.pickingGroup.read') or hasAuthority('all.pickingGroup.read')")
	Page<PickingGroup> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	@PreAuthorize("hasAuthority('admin.pickingGroup.read') or hasAuthority('all.pickingGroup.read')")
	PickingGroup getOne(Long pickingGroupId);
	@PreAuthorize("hasAuthority('admin.pickingGroup.write')")
	PickingGroup save(PickingGroup pickingGroup);
	@PreAuthorize("hasAuthority('admin.pickingGroup.write')")
	void delete(Long pickingGroupId);

	@PreAuthorize("hasAuthority('admin.pickingGroup.read') or hasAuthority('all.pickingGroup.read')")
	Map<String, PickingGroup> getPickingGroupTable();
}