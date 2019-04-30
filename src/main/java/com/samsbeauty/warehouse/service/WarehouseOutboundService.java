package com.samsbeauty.warehouse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import com.samsbeauty.warehouse.model.WarehouseOutbound;

public interface WarehouseOutboundService {
	Page<WarehouseOutbound> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	WarehouseOutbound getOne(Long outboundId);
	WarehouseOutbound save(WarehouseOutbound warehouseOutbound);
	void delete(Long outboundId);
}