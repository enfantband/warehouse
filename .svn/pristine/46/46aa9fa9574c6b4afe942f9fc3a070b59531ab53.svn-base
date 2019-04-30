package com.samsbeauty.warehouse.picking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.picking.model.PickingGroupCompany;

@PreAuthorize("isAuthenticated()")
public interface PickingGroupCompanyService {
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	Page<PickingGroupCompany> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	PickingGroupCompany getOne(Long pickingGroupCompanyId);
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	PickingGroupCompany getOneByCompanyCodeAndPickingGroupId(String companyCode, Long pickingGroupId);
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	boolean existsByCompanyCodeAndPickingGroupId(String companyCode, Long pickingGroupId);
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	PickingGroupCompany save(PickingGroupCompany pickingGroupCompany);
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	void delete(Long pickingGroupCompanyId);
}