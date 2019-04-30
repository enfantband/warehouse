package com.samsbeauty.warehouse.picking.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.picking.model.PickingJob.PickingJobStatus;
import com.samsbeauty.warehouse.picking.model.PickingJobTimeline;
public interface PickingJobTimelineRepository extends JpaRepository<PickingJobTimeline, Long> {
	Page<PickingJobTimeline> findByActivated(boolean activated, Pageable pageable);
	@Query("SELECT p FROM PickingJobTimeline p WHERE p.warehouseEmployee.warehouseEmployeeId=?1 and p.activated = 1")
	List<PickingJobTimeline> findAssignedTimelinesByWarehouseEmployeeId(Long warehouseEmployeeId);
	
	@Query("SELECT p FROM PickingJobTimeline p WHERE p.warehouseEmployee.gid=?1 and p.activated = 1 and p.pickingJobStatusTo=" + PickingJobStatus.ASSIGNED_CODE)
	List<PickingJobTimeline> findAssignedTimelinesByGid(String gid);
	
	@Query("SELECT p FROM PickingJobTimeline p WHERE p.pickingJob.pickingJobId=?1")
	List<PickingJobTimeline> findByPickingJobId(Long pickingJobId);
}