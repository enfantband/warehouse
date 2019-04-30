package com.samsbeauty.warehouse.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.model.WarehouseLevel;
public interface WarehouseLevelRepository extends JpaRepository<WarehouseLevel, Long>, JpaSpecificationExecutor<WarehouseLevel> {
	@Query("SELECT wl FROM WarehouseLevel wl "
			+ "JOIN wl.warehouseSubgroup.warehouseGroup.warehouseAisles wa WHERE wl.useFlag='Y' and ( (wa.aisleId = ?2 or ?2 is null or ?2 = '') and wa.useFlag='Y' ) AND ((wa.warehouse.warehouseId = ?1 or ?1 = '' or ?1 is null) and wa.warehouse.useFlag='Y') AND ((wl.warehouseSubgroup.subgroupId = ?4 or ?4 = '' or ?4 is null) and wl.warehouseSubgroup.useFlag='Y') AND ((wl.warehouseSubgroup.warehouseGroup.groupId = ?3 or ?3 = '' or ?3 is null) and wl.warehouseSubgroup.warehouseGroup.useFlag='Y') AND (?5 is null OR ?5 = '' OR wl.warehouseTag.tag = ?5)")
	Page<WarehouseLevel> getAllByTag(Long warehouseId, Long aisleId, Long groupId, Long subgroupId, String tag, Pageable pageable);
	
	@Query("SELECT wl FROM WarehouseLevel wl JOIN wl.warehouseItems wi WHERE wi.generatedBarcode = ?1")
	List<WarehouseLevel> getAllByGeneratedBarcode(String generatedBarcode);
	
	@Query("SELECT wl FROM WarehouseLevel wl JOIN wl.warehouseItems wi WHERE wi.generatedBarcode = ?1 and wl.levelType=?2")
	List<WarehouseLevel> getAllByGeneratedBarcodeAndLevelType(String generatedBarcode, String levelType);
	
	@Query("SELECT ( CASE WHEN COUNT(wl.levelCode) = 0 THEN 0 ELSE MAX(wl.levelCode) END ) FROM WarehouseLevel wl WHERE wl.useFlag='Y' and wl.warehouseSubgroup.subgroupId=?1")
	Integer getMaxLevel(Long subgroupId);
}