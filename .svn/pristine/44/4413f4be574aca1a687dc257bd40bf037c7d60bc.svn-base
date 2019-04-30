package com.samsbeauty.warehouse.role.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.role.model.Role;
public interface RoleRepository extends JpaRepository<Role, Long> {
	Page<Role> findByActivated(boolean activated, Pageable pageable);
	Role getOneByRoleName(String roleName);
	
	@Query("SELECT r FROM Role r WHERE activated = 1")
	List<Role> findAll();
}