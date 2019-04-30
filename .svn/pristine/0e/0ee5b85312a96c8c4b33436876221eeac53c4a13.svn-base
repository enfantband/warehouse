package com.samsbeauty.warehouse.picking.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import com.itextpdf.text.DocumentException;
import com.samsbeauty.old.model.OrderInfoForPicking;
import com.samsbeauty.old.model.OrderItem;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.picking.export.model.GroupPickingJobExport;
import com.samsbeauty.warehouse.picking.model.PickingGroup;
import com.samsbeauty.warehouse.picking.model.PickingJob;
import com.samsbeauty.warehouse.picking.model.PickingJobGroup;

@Secured("isAuthenticated()")
public interface PickingJobService {

	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	Page<PickingJob> getAll(Integer pageNumber, Integer pageSize, Sort sort);

	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	PickingJob getOne(Long pickingJobId);

	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	PickingJob save(PickingJob pickingJob);
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	void createMissingList(PickingJobGroup pickingJobGroup, WarehouseEmployee warehouseEmployee, Map<String, PickingGroup> pickingGroupTable) throws Exception;
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	List<PickingJob> getAssignedJobs(Long warehouseEmployeeId);
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	PickingJobGroup createPickingJob(Integer numberOfProcess, Map<String, PickingGroup> pickingGroupTable, String includingOrders, Integer amzhs, Integer amzeb, WarehouseEmployee regBy) throws Exception;

	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	void createPdfForPicking(Map<Long, GroupPickingJobExport> pickingJobExportMap, Integer pickingSet, WarehouseEmployee regBy) throws DocumentException, IOException;
	List<OrderInfoForPicking> sortOrderForPacking(List<OrderInfoForPicking> orderInfos, Map<String, List<OrderItem>> orders);
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	void delete(Long pickingJobId);
}