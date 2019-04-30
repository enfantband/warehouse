package com.samsbeauty.warehouse.role.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.role.model.Role;

@PreAuthorize("isAuthenticated()")
public interface RoleService {

	@PreAuthorize("hasAuthority('admin.role.read') or hasAuthority('all.role.read')")
	List<Role> getAll();
	@PreAuthorize("hasAuthority('admin.role.read') or hasAuthority('all.role.read')")
	Page<Role> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	@PreAuthorize("hasAuthority('admin.role.read')")
	Role getOne(Long roleId);
	@PreAuthorize("hasAuthority('admin.role.read') or hasAuthority('all.role.read')")
	Role getOneByRoleName(String roleName);
	@PreAuthorize("hasAuthority('admin.role.write') or hasAuthority('all.role.write')")
	Role save(Role role);
	@PreAuthorize("hasAuthority('admin.role.write')")
	void delete(Long roleId);
}