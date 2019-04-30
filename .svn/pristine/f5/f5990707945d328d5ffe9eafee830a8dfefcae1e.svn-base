package com.samsbeauty.warehouse.picking.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.picking.model.PickingItem;
public interface PickingItemRepository extends JpaRepository<PickingItem, Long> {
	Page<PickingItem> findAllByActivated(boolean activated, Pageable pageable);
	@Query("SELECT p FROM PickingItem p WHERE p.pickingJobTimeline.timelineId = ?1 and p.activated = 1")
	List<PickingItem> findAllByTimelineId(Long timelineId);
}