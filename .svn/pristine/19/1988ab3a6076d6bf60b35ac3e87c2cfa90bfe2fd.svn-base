package com.samsbeauty.warehouse.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.model.WarehouseSubgroup;

@PreAuthorize("isAuthenticated()")
public interface WarehouseSubgroupService {
	Page<WarehouseSubgroup> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	Page<WarehouseSubgroup> getAllByGroupId(Long groupId, Integer pageNumber, Integer pageSize, Sort sort);
	List<WarehouseSubgroup> getAllByGroupId(Long groupId);
	List<WarehouseSubgroup> getAllByGroupCode(String groupCode);
	WarehouseSubgroup getOne(Long subgroupId);
	WarehouseSubgroup save(WarehouseSubgroup warehouseSubgroup);
	void delete(Long subgroupId);
	Integer getMaxCode(Long groupId);
}