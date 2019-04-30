package com.samsbeauty.warehouse.picking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.picking.model.PickingGroup;
public interface PickingGroupRepository extends JpaRepository<PickingGroup, Long> {
	@Query("SELECT p FROM PickingGroup p ORDER BY p.filterOrder asc")
	List<PickingGroup> findAll();
}