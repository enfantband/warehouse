package com.samsbeauty.warehouse.picking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.picking.model.PickingJob;
public interface PickingJobRepository extends JpaRepository<PickingJob, Long>{
	@Query("SELECT new PickingJob(p.pickingJobId, p.pickingJobGroup, p.pickingGroup, p.pickingStatus, p.saved, SUM(pi.orderQuantity), SUM(pic.quantity) ) FROM PickingJob p JOIN p.pickingJobTimelines pt LEFT JOIN p.pickingItemInfos pi LEFT JOIN p.pickingItems pic WHERE pt.warehouseEmployee.warehouseEmployeeId=?1 and p.pickingStatus in (" + PickingJob.PickingJobStatus.ASSIGNED_CODE + ", " + PickingJob.PickingJobStatus.STARTED_CODE + ", " + PickingJob.PickingJobStatus.PAUSED_CODE + ") GROUP BY p.pickingJobId")
	List<PickingJob> findAssignedByWarehouseEmployeeId(Long warehouseEmployeeId);
	
	@Query("SELECT COUNT(p) FROM PickingJob p WHERE p.pickingJobGroup.pickingJobGroupId = ?1 and p.pickingGroup.pickingGroupId=?2 and p.saved = 1")
	Long countByPickingJobGroupAndSaved(Long pickingJobGroupId, Long pickingGroupId);
	
	@Query("SELECT new PickingJob(p.pickingJobId, p.pickingJobGroup, p.pickingGroup, p.pickingStatus, p.saved, SUM(pi.orderQuantity), SUM(pic.quantity) ) FROM PickingJob p LEFT JOIN p.pickingItemInfos pi LEFT JOIN p.pickingItems pic WHERE p.pickingJobGroup.pickingJobGroupId = ?1 and p.pickingGroup.pickingGroupId=?2 and p.saved = 1")
	PickingJob getByPickingJobGroupAndSaved(Long pickingJobGroupId, Long pickingGroupId);
}