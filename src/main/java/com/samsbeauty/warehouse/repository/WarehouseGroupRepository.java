package com.samsbeauty.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.model.WarehouseGroup;
public interface WarehouseGroupRepository extends JpaRepository<WarehouseGroup, Long>, JpaSpecificationExecutor<WarehouseGroup> {
	@Query("SELECT ( CASE WHEN COUNT(wg.groupCode) = 0 THEN 0 ELSE MAX(wg.groupCode) END ) FROM WarehouseGroup wg JOIN wg.warehouseAisles wa WHERE wa.aisleId = ?1 and wg.useFlag='Y'")
	Integer getMaxCode(Long aisleId);
}