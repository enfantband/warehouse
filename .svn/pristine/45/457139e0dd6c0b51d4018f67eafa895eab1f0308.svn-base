package com.samsbeauty.warehouse.picking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.picking.model.PickingJobGroup;
public interface PickingJobGroupRepository extends JpaRepository<PickingJobGroup, Long>,  JpaSpecificationExecutor<PickingJobGroup> {
	@Query("SELECT MAX(g.pickingSet) FROM PickingJobGroup g WHERE g.regDate > DATE_FORMAT(now(), '%Y-%m-%d') and g.activated = 1")
	Integer getMaxPickingSet();
	
	@Query("SELECT g FROM PickingJobGroup g JOIN g.pickingJobs j WHERE j.pickingJobId = ?1")
	PickingJobGroup getOneByPickingJobId(Long pickingJobId);
}