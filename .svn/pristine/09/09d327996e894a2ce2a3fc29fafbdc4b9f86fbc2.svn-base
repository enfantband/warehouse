package com.samsbeauty.warehouse.tag.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.samsbeauty.warehouse.tag.model.WarehouseTag;
import com.samsbeauty.warehouse.tag.repository.WarehouseTagRepository;

public class WarehouseTagServiceImpl implements WarehouseTagService {
	@Autowired
	private WarehouseTagRepository warehouseTagRepository;
	
	public WarehouseTag save(WarehouseTag warehouseTag) {
		return warehouseTagRepository.save(warehouseTag);
	}
}
