package com.samsbeauty.warehouse.service;

import static com.samsbeauty.warehouse.specification.WarehouseSubgroupSpecifications.activated;
import static com.samsbeauty.warehouse.specification.WarehouseSubgroupSpecifications.groupCode;
import static com.samsbeauty.warehouse.specification.WarehouseSubgroupSpecifications.groupId;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.geo.repository.WarehouseGeoInfoRepository;
import com.samsbeauty.warehouse.model.WarehouseSubgroup;
import com.samsbeauty.warehouse.repository.WarehouseSubgroupRepository;
import com.samsbeauty.warehouse.tag.repository.WarehouseTagRepository;

@Service
@Transactional
public class WarehouseSubgroupServiceImpl implements WarehouseSubgroupService {
	@Autowired
	private WarehouseSubgroupRepository warehouseSubgroupRepository;
	@Autowired
	private WarehouseTagRepository warehouseTagrepository;
	@Autowired
	private WarehouseGeoInfoRepository warehouseGeoInfoRepository;

	public Page<WarehouseSubgroup> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return warehouseSubgroupRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));
	}
	public Page<WarehouseSubgroup> getAllByGroupId(Long groupId, Integer pageNumber, Integer pageSize, Sort sort) {
		return warehouseSubgroupRepository.findAll( where(activated()).and(groupId(groupId)), new PageRequest(pageNumber-1, pageSize, sort));
	}
	public List<WarehouseSubgroup> getAllByGroupId(Long groupId) {
		return warehouseSubgroupRepository.findAll( where(activated()).and(groupId(groupId)));
	}
	public List<WarehouseSubgroup> getAllByGroupCode(String groupCode) {
		return warehouseSubgroupRepository.findAll(where(activated()).and(groupCode(groupCode)));
	}
	public WarehouseSubgroup getOne(Long subgroupId) {
		return warehouseSubgroupRepository.getOne(subgroupId);
	}
	public WarehouseSubgroup save(WarehouseSubgroup warehouseSubgroup) {
		warehouseTagrepository.save(warehouseSubgroup.getWarehouseTag());
		warehouseGeoInfoRepository.save(warehouseSubgroup.getWarehouseGeoInfo());
		return warehouseSubgroupRepository.save(warehouseSubgroup);
	}
	public void delete(Long subgroupId) {
		warehouseSubgroupRepository.delete(subgroupId);
	}
	public Integer getMaxCode(Long groupId) {
		return warehouseSubgroupRepository.getMaxCode(groupId);
	}
}