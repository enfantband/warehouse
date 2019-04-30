package com.samsbeauty.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.model.WarehouseSubgroup;
public interface WarehouseSubgroupRepository extends JpaRepository<WarehouseSubgroup, Long>, JpaSpecificationExecutor<WarehouseSubgroup> {
	@Query("SELECT ( CASE WHEN COUNT(ws.subgroupCode) = 0 THEN 0 ELSE MAX(ws.subgroupCode) END ) FROM WarehouseSubgroup ws WHERE ws.warehouseGroup.groupId = ?1")
	Integer getMaxCode(Long groupId);
}