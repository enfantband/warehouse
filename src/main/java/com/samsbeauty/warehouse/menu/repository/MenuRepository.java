package com.samsbeauty.warehouse.menu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.menu.model.Menu;
public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {
	@Query("SELECT m FROM Menu m WHERE m.activated = 1 ORDER BY m.ordered ASC")
	Page<Menu> findAll(Pageable pageable);
	
	@Query("SELECT m FROM Menu m ORDER BY m.ordered ASC")
	List<Menu> findAll();
}