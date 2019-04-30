package com.samsbeauty.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.model.Warehouse;
public interface WarehouseRepository extends JpaRepository<Warehouse, Long>, JpaSpecificationExecutor<Warehouse> {
	@Query("SELECT ( CASE WHEN COUNT(w.warehouseCode) = 0 THEN 0 ELSE MAX(w.warehouseCode) END ) FROM Warehouse w WHERE w.useFlag = 'Y'")
	Integer getMaxCode();
}