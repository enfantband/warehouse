package com.samsbeauty.warehouse.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.geo.repository.WarehouseGeoInfoRepository;
import com.samsbeauty.warehouse.model.WarehouseAisle;
import com.samsbeauty.warehouse.repository.WarehouseAisleRepository;
import com.samsbeauty.warehouse.specification.WarehouseAisleSpecifications;
import com.samsbeauty.warehouse.tag.repository.WarehouseTagRepository;

@Service
@Transactional
public class WarehouseAisleServiceImpl implements WarehouseAisleService {
	@Autowired
	private WarehouseAisleRepository warehouseAisleRepository;
	
	@Autowired
	private WarehouseTagRepository warehouseTagRepository;
	
	@Autowired
	private WarehouseGeoInfoRepository warehouseGeoInfoRepository;
	public Page<WarehouseAisle> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		Specifications<WarehouseAisle> chainFilter = Specifications.where(WarehouseAisleSpecifications.activated());
		return warehouseAisleRepository.findAll(chainFilter, new PageRequest(pageNumber-1, pageSize, sort));
	}
	public Page<WarehouseAisle> getAllByWarehouseId(Long warehouseId, Integer pageNumber, Integer pageSize, Sort sort) {
		Specifications<WarehouseAisle> chainFilter = Specifications.where(WarehouseAisleSpecifications.activated()).and(WarehouseAisleSpecifications.warehouseId(warehouseId));
		return warehouseAisleRepository.findAll(chainFilter, new PageRequest(pageNumber-1, pageSize, sort));
	}
	public List<WarehouseAisle> getAllByWarehouseId(Long warehouseId) {
		Specifications<WarehouseAisle> chainFilter = Specifications.where(WarehouseAisleSpecifications.activated()).and(WarehouseAisleSpecifications.warehouseId(warehouseId));
		return warehouseAisleRepository.findAll(chainFilter);
	}
	public List<WarehouseAisle> getAllByWarehouseCode(String warehouseCode) {
		Specifications<WarehouseAisle> chainFilter = Specifications.where(WarehouseAisleSpecifications.activated()).and(WarehouseAisleSpecifications.warehouseCode(warehouseCode));
		return warehouseAisleRepository.findAll(chainFilter);
	}
	public WarehouseAisle getOne(Long aisleId) {
		return warehouseAisleRepository.getOne(aisleId);
	}
	public WarehouseAisle save(WarehouseAisle warehouseAisle) {
		warehouseTagRepository.save(warehouseAisle.getWarehouseTag());
		warehouseGeoInfoRepository.save(warehouseAisle.getWarehouseGeoInfo());
		
		return warehouseAisleRepository.save(warehouseAisle);
	}
	public Integer getMaxCode(Long warehouseId) {
		return warehouseAisleRepository.getMaxCode(warehouseId);
	}
	public void delete(Long aisleId) {
		warehouseAisleRepository.delete(aisleId);
	}
}