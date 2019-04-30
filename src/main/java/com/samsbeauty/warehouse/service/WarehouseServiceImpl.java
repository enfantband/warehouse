package com.samsbeauty.warehouse.service;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;

import static com.samsbeauty.warehouse.specification.WarehouseSpecifications.*;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.model.Warehouse;
import com.samsbeauty.warehouse.repository.WarehouseRepository;
import com.samsbeauty.warehouse.tag.repository.WarehouseTagRepository;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {
	@Autowired
	private WarehouseRepository warehouseRepository;
	
	@Autowired
	private WarehouseTagRepository warehouseTagRepository;

	public Integer getMaxCode() {
		return warehouseRepository.getMaxCode();
	}
	public Page<Warehouse> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return warehouseRepository.findAll(where(activated()), new PageRequest(pageNumber-1, pageSize, sort));
	}
	public List<Warehouse> getAll() {
		return warehouseRepository.findAll(where(activated()));
	}
	public Warehouse getOne(Long warehouseId) {
		return warehouseRepository.getOne(warehouseId);
	}
	public Warehouse save(Warehouse warehouse) {
		warehouseTagRepository.save(warehouse.getWarehouseTag());
		return warehouseRepository.save(warehouse);
	}
	public void delete(Long warehouseId) {
		warehouseRepository.delete(warehouseId);
	}
}