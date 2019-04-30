package com.samsbeauty.warehouse.picking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.picking.model.PickingItemInfo;
public interface PickingItemInfoRepository extends JpaRepository<PickingItemInfo, Long> {
	@Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM PickingItemInfo i WHERE i.generatedBarcode = ?1 and i.orderNo = ?2")
	boolean existsByGeneratedBarcodeAndOrderNo(String generatedBarcode, String orderNo);
	@Query("SELECT p FROM PickingItemInfo p WHERE p.pickingJob.pickingJobId=?1")
	List<PickingItemInfo> findByPickingJobId(Long pickingJobId);
}