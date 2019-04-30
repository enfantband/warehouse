package com.samsbeauty.warehouse.picking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.picking.model.PickingGroupCompany;
public interface PickingGroupCompanyRepository extends JpaRepository<PickingGroupCompany, Long> {
	@Query("SELECT c FROM PickingGroupCompany c WHERE c.companyCode = ?1 and c.pickingGroup.pickingGroupId = ?2")
	PickingGroupCompany getByCompanyCodeAndPickingGroupId(String companyCode, Long pickingGroupId);
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM PickingGroupCompany c WHERE c.companyCode = ?1 and c.pickingGroup.pickingGroupId = ?2")
	boolean existsByCompanyCodeAndPickingGroupId(String companyCode, Long pickingGroupId);
}