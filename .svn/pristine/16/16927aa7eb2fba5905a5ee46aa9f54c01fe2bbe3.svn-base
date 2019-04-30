package com.samsbeauty.warehouse.service;

import static com.samsbeauty.warehouse.specification.WarehouseGroupSpecifications.activated;
import static com.samsbeauty.warehouse.specification.WarehouseGroupSpecifications.aisleCode;
import static com.samsbeauty.warehouse.specification.WarehouseGroupSpecifications.aisleId;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.geo.repository.WarehouseGeoInfoRepository;
import com.samsbeauty.warehouse.model.WarehouseGroup;
import com.samsbeauty.warehouse.repository.WarehouseGroupRepository;
import com.samsbeauty.warehouse.tag.repository.WarehouseTagRepository;

@Service
@Transactional
public class WarehouseGroupServiceImpl implements WarehouseGroupService {
	@Autowired
	private WarehouseGroupRepository warehouseGroupRepository;
	
	@Autowired
	private WarehouseTagRepository warehouseTagRepository;
	
	@Autowired
	private WarehouseGeoInfoRepository warehouseGeoInfoRepository;

	public Page<WarehouseGroup> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return warehouseGroupRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));
	}
	public List<WarehouseGroup> getAll() {
		return warehouseGroupRepository.findAll(where(activated()));
	}
	public Page<WarehouseGroup> getAllByAisleId(Long aisleId, Integer pageNumber, Integer pageSize, Sort sort) {
		return warehouseGroupRepository.findAll(				
				where(activated())
				.and(aisleId(aisleId)),
				new PageRequest(pageNumber-1, pageSize, sort)
			);
	}
	public List<WarehouseGroup> getAllByAisleId(Long aisleId) {
		return warehouseGroupRepository.findAll(where(activated())
				.and(aisleId(aisleId)));
	}
	public List<WarehouseGroup> getAllByAisleCode(String aisleCode) {
		return warehouseGroupRepository.findAll(
				where(activated())
				.and(aisleCode(aisleCode))
				);
	}
	public WarehouseGroup getOne(Long groupId) {
		return warehouseGroupRepository.getOne(groupId);
	}
	public WarehouseGroup save(WarehouseGroup warehouseGroup) {
		warehouseTagRepository.save(warehouseGroup.getWarehouseTag());
		warehouseGeoInfoRepository.save(warehouseGroup.getWarehouseGeoInfo());
		return warehouseGroupRepository.save(warehouseGroup);
	}
	public void delete(Long groupId) {
		warehouseGroupRepository.delete(groupId);
	}
	public Integer getMaxCode(Long aisleId) {
		return warehouseGroupRepository.getMaxCode(aisleId);
	}
}