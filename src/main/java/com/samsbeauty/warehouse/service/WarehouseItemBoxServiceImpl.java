package com.samsbeauty.warehouse.service;

import static com.samsbeauty.warehouse.specification.WarehouseItemBoxSpecifications.activated;
import static com.samsbeauty.warehouse.specification.WarehouseItemBoxSpecifications.levelId;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.model.WarehouseItemBox;
import com.samsbeauty.warehouse.repository.WarehouseItemBoxRepository;
@Service
@Transactional
public class WarehouseItemBoxServiceImpl implements WarehouseItemBoxService {
	@Autowired
	private WarehouseItemBoxRepository warehouseItemBoxRepository;

	public Page<WarehouseItemBox> getAll(Integer pageNumber, Integer pageSize, Sort sort) {		
		return warehouseItemBoxRepository.findAll(where(activated()), new PageRequest(pageNumber-1, pageSize, sort));
	}
	public Page<WarehouseItemBox> getAllByLevelId(Long levelId, Integer pageNumber, Integer pageSize, Sort sort) {
		return warehouseItemBoxRepository.findAll(where(activated()).and(levelId(levelId)), new PageRequest(pageNumber-1, pageSize, sort));
	}
	public WarehouseItemBox getOneByBoxPrefixAndBoxCodeAndLevelId(String boxCode, Long levelId) {
		return warehouseItemBoxRepository.getOneByBoxPrefixAndBoxCode(boxCode, levelId);
	}
	public WarehouseItemBox getOne(Integer itemBoxId) {
		return warehouseItemBoxRepository.getOne(itemBoxId);
	}
	public WarehouseItemBox save(WarehouseItemBox warehouseItemBox) {
		return warehouseItemBoxRepository.save(warehouseItemBox);
	}
	public List<WarehouseItemBox> getAllByLevelId(Long levelId) {
		return warehouseItemBoxRepository.findAll(where(activated()).and(levelId(levelId)));
	}
	public void delete(Integer itemBoxId) {
		warehouseItemBoxRepository.delete(itemBoxId);
	}
	public Integer getMaxCode(String boxPrefix) {
		return warehouseItemBoxRepository.getMaxCode(boxPrefix);
	}
}