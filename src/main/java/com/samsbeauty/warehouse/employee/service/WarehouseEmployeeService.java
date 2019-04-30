package com.samsbeauty.warehouse.employee.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.role.model.Role;

@PreAuthorize("isAuthenticated()")
public interface WarehouseEmployeeService {
	Page<WarehouseEmployee> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	List<WarehouseEmployee> getAll();
	Page<WarehouseEmployee> getAllByRoles(Integer pageNumber, Integer pageSize, Sort sort, Set<Role> roles);
	WarehouseEmployee getOne(Long warehouseEmployeeId);
	WarehouseEmployee getOneByGid(String gid);
	WarehouseEmployee setRoles(Long warehouseEmployeeId, List<Long> roleIds);
	WarehouseEmployee save(WarehouseEmployee warehouseEmployee);
	void delete(Long warehouseEmployeeId);
}