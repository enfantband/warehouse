package com.samsbeauty.warehouse.role.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.role.model.Privilege;

@PreAuthorize("isAuthenticated()")
public interface PrivilegeService {

	@PreAuthorize("hasAuthority('admin.role.read') or hasAuthority('all.role.read')")
	Page<Privilege> getAll(Integer pageNumber, Integer pageSize, Sort sort);

	@PreAuthorize("hasAuthority('admin.role.read') or hasAuthority('all.role.read')")
	List<Privilege> getAll();

	@PreAuthorize("hasAuthority('admin.role.read') or hasAuthority('all.role.read')")
	Privilege getOne(Long privilegeId);

	@PreAuthorize("hasAuthority('admin.role.write')")
	Privilege save(Privilege privilege);
	@PreAuthorize("hasAuthority('admin.role.write')")
	void delete(Long privilegeId);
}