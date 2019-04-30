package com.samsbeauty.warehouse.employee.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.employee.repository.WarehouseEmployeeRepository;
import com.samsbeauty.warehouse.role.model.Role;
import com.samsbeauty.warehouse.role.repository.RoleRepository;

@Service
@Transactional
public class WarehouseEmployeeServiceImpl implements WarehouseEmployeeService {
	@Autowired
	private WarehouseEmployeeRepository warehouseEmployeeRepository;
	@Autowired
	private RoleRepository roleRepository;

	public Page<WarehouseEmployee> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return warehouseEmployeeRepository.findByActivated(true, new PageRequest(pageNumber-1, pageSize, sort));
	}
	public List<WarehouseEmployee> getAll() {
		return warehouseEmployeeRepository.findAll();
	}
	public Page<WarehouseEmployee> getAllByRoles(Integer pageNumber, Integer pageSize, Sort sort, Set<Role> roles) {
		return warehouseEmployeeRepository.findByActivatedAndRoles(true, roles, new PageRequest(pageNumber-1, pageSize, sort));
	}
	public WarehouseEmployee getOne(Long warehouseEmployeeId) {
		return warehouseEmployeeRepository.getOne(warehouseEmployeeId);
	}
	public WarehouseEmployee getOneByGid(String gid) {
		return warehouseEmployeeRepository.getOneByGid(gid);
	}
	public WarehouseEmployee save(WarehouseEmployee warehouseEmployee) {
		return warehouseEmployeeRepository.save(warehouseEmployee);
	}
	public WarehouseEmployee setRoles(Long warehouseEmployeeId, List<Long> roleIds) {
		WarehouseEmployee warehouseEmployee = warehouseEmployeeRepository.getOne(warehouseEmployeeId);
		warehouseEmployee.removeAllRoles();
		
		for(Long roleId : roleIds) {
			Role role = roleRepository.getOne(roleId);
			warehouseEmployee.addRole(role);
		}
		
		return warehouseEmployeeRepository.save(warehouseEmployee);
	}
	public void delete(Long warehouseEmployeeId) {
		warehouseEmployeeRepository.delete(warehouseEmployeeId);
	}
}