package com.samsbeauty.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.model.WarehouseAisle;
public interface WarehouseAisleRepository extends JpaRepository<WarehouseAisle, Long>, JpaSpecificationExecutor<WarehouseAisle> {
	
	@Query("SELECT ( CASE WHEN COUNT(wa.aisleCode) = 0 THEN 0 ELSE MAX(wa.aisleCode) END ) FROM WarehouseAisle wa WHERE wa.warehouse.warehouseId = ?1")
	Integer getMaxCode(Long warehouseId);
}