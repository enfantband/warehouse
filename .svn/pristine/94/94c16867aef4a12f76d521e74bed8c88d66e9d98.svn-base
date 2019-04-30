package com.samsbeauty.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.model.WarehouseItemBox;
public interface WarehouseItemBoxRepository extends JpaRepository<WarehouseItemBox, Integer>, JpaSpecificationExecutor<WarehouseItemBox> {

	@Query("SELECT ( CASE WHEN COUNT(wb.boxCode) = 0 THEN 0 ELSE MAX(wb.boxCode) END ) FROM WarehouseItemBox wb WHERE wb.useFlag='Y' and wb.boxPrefix=?1")
	Integer getMaxCode(String boxPrefix);
	
	@Query("SELECT wb FROM WarehouseItemBox wb WHERE concat(wb.boxPrefix, wb.boxCode) = ?1 AND wb.warehouseLevel.levelId = ?2")
	WarehouseItemBox getOneByBoxPrefixAndBoxCode(String boxCode, Long levelID);
}