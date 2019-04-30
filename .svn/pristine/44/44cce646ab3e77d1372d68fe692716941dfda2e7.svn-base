package com.samsbeauty.warehouse.menu.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.menu.model.Menu;

@PreAuthorize("isAuthenticated()")
public interface MenuService {

	@PreAuthorize("hasAuthority('admin.menu.read')")
	Page<Menu> getAll(Integer pageNumber, Integer pageSize, Sort sort, String search);

	@PreAuthorize("hasAuthority('admin.menu.read')")
	Page<Menu> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	List<Menu> getAll();

	@PreAuthorize("hasAuthority('admin.menu.read')")
	Menu getOne(Long menuId);
	public List<Menu> getAll(String gid);
	@PreAuthorize("hasAuthority('admin.menu.write')")
	Menu save(Menu menu);
	@PreAuthorize("hasAuthority('admin.menu.write')")
	void delete(Long menuId);

	@PreAuthorize("hasAuthority('admin.menu.write')")
	public Menu setRoles(Long menuId, List<Long> roleIds);
}