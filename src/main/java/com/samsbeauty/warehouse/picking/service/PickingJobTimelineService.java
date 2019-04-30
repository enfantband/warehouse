package com.samsbeauty.warehouse.picking.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.exception.rest.PickingJobTimelineNotFoundException;
import com.samsbeauty.warehouse.picking.model.PickingJob;
import com.samsbeauty.warehouse.picking.model.PickingJobTimeline;

@PreAuthorize("isAuthenticated()")
public interface PickingJobTimelineService {
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	Page<PickingJobTimeline> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	List<PickingJobTimeline> getAssignedTimelinesByWarehouseEmployeeId(Long warehouseEmployeeId);
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	List<PickingJobTimeline> getAssignedTimelinesByGid(String gid);
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	PickingJobTimeline getOne(Long timelineId);
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	PickingJobTimeline save(PickingJobTimeline pickingJobTimeline);
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	PickingJobTimeline addPickingJobToStart(PickingJob pickingJob, WarehouseEmployee warehouseEmployee, String pickingJobStatus, Date eventTime);
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	PickingJobTimeline addPickingJobToEnd(PickingJob pickingJob, WarehouseEmployee warehouseEmployee, String pickingJobStatus, Date eventTime)  throws PickingJobTimelineNotFoundException;
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	PickingJobTimeline handoverPickingJob(PickingJob pickingJob, WarehouseEmployee warehouseEmployee, Date eventTime)  throws PickingJobTimelineNotFoundException;
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	void delete(Long timelineId);
}