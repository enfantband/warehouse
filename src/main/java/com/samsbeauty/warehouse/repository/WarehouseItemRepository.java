package com.samsbeauty.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.model.WarehouseItem;
public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Integer>, JpaSpecificationExecutor<WarehouseItem> {
	@Query("SELECT ( CASE WHEN COUNT(ws.subgroupCode) = 0 THEN 0 ELSE MAX(ws.subgroupCode) END ) FROM WarehouseSubgroup ws")
	Integer getMaxCode();
	
	WarehouseItem getOneByGeneratedBarcode(String barcode);
	
	@Query("SELECT ( CASE WHEN COUNT(wi.itemId) = 0 THEN FALSE ELSE TRUE END ) FROM WarehouseItem wi WHERE wi.generatedBarcode = ?1 AND wi.warehouseLevel.levelId = ?2")
	boolean exists(String generatedBarcode, Long levelId);
	
	@Query("SELECT ( CASE WHEN COUNT(wi.itemId) = 0 THEN FALSE ELSE TRUE END ) FROM WarehouseItem wi WHERE wi.generatedBarcode = ?1 AND wi.warehouseLevel.levelId = ?2 AND wi.warehouseItemBox.itemBoxId = ?3")
	boolean exists(String generatedBarcode, Long levelId, Integer itemBoxId);
}