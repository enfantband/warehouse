package com.samsbeauty.warehouse.adjustment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.adjustment.model.AdjustmentRequest;
public interface AdjustmentRequestRepository extends JpaRepository<AdjustmentRequest, Long>, JpaSpecificationExecutor<AdjustmentRequest>{
	@Query("SELECT a FROM AdjustmentRequest a WHERE a.pickingItem.pickingItemId = ?1")
	AdjustmentRequest getOneByPickingItemId(Long pickingItemId);
}