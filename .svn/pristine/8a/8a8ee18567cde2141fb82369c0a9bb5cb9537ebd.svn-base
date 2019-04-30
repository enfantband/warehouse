package com.samsbeauty.warehouse.role.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.role.model.Privilege;
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
	Page<Privilege> findByActivated(boolean activated, Pageable pageable);
	@Query("SELECT p FROM Privilege p WHERE p.activated = 1")
	List<Privilege> findAll();
}