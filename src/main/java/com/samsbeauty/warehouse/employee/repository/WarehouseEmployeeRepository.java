package com.samsbeauty.warehouse.employee.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.role.model.Role;
public interface WarehouseEmployeeRepository extends JpaRepository<WarehouseEmployee, Long> {
	@Query("SELECT w FROM WarehouseEmployee w WHERE w.activated = 1")
	List<WarehouseEmployee> findAll();
	Page<WarehouseEmployee> findByActivated(boolean activated, Pageable pageable);
	Page<WarehouseEmployee> findByActivatedAndRoles(boolean activated, Set<Role> roles, Pageable pageable);
	@Query("SELECT we FROM WarehouseEmployee we WHERE we.gid = ?1")
	WarehouseEmployee getOneByGid(String gid);
}