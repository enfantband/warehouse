package com.samsbeauty.warehouse.service;

import static com.samsbeauty.warehouse.specification.WarehouseLevelSpecifications.activated;
import static com.samsbeauty.warehouse.specification.WarehouseLevelSpecifications.locationCode;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.exception.rest.LocationNotFoundException;
import com.samsbeauty.warehouse.model.WarehouseLevel;
import com.samsbeauty.warehouse.repository.WarehouseLevelRepository;
import com.samsbeauty.warehouse.tag.repository.WarehouseTagRepository;

@Service
@Transactional
public class WarehouseLevelServiceImpl implements WarehouseLevelService {
	@Autowired
	private WarehouseLevelRepository warehouseLevelRepository;
	
	@Autowired
	private WarehouseTagRepository warehouseTagRespository;

	public Page<WarehouseLevel> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return warehouseLevelRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));
	}
	public Page<WarehouseLevel> getAllByLocationBarcode(String locationBarcode, Integer pageNumber, Integer pageSize, Sort sort) {
		return warehouseLevelRepository.findAll(where(activated()).and(locationCode(locationBarcode)), new PageRequest(pageNumber-1, pageSize, sort));
	}
	public Page<WarehouseLevel> getAllByTag(Long warehouseId, Long aisleId, Long groupId, Long subgroupId, String tag, Integer pageNumber, Integer pageSize, Sort sort) {
		return warehouseLevelRepository.getAllByTag(warehouseId, aisleId, groupId, subgroupId, tag, new PageRequest(pageNumber-1, pageSize, sort));
	}
	public List<WarehouseLevel> getAllByLocationBarcode(String locationBarcode) {
		return warehouseLevelRepository.findAll(where(activated()).and(locationCode(locationBarcode)));
	}
	public List<WarehouseLevel> getAllByGeneratedBarcode(String generatedBarcode) {
		return warehouseLevelRepository.getAllByGeneratedBarcode(generatedBarcode);
	}
	public List<WarehouseLevel> getAllByGeneratedBarcodeAndType(String generatedBarcode, String levelType) {
		return warehouseLevelRepository.getAllByGeneratedBarcodeAndLevelType(generatedBarcode, levelType);
	}
	public WarehouseLevel getOne(Long levelId) {
		return warehouseLevelRepository.getOne(levelId);
	}
	public WarehouseLevel getOneByLocationBarcode(String locationBarcode) throws LocationNotFoundException {
		List<WarehouseLevel> levels = warehouseLevelRepository.findAll(where(activated()).and(locationCode(locationBarcode)));
		if(levels == null || levels.size() == 0) {
			throw new LocationNotFoundException("Cannot find a location");
		}
		return levels.get(0);
	}
	public WarehouseLevel save(WarehouseLevel warehouseLevel) {
		warehouseTagRespository.save(warehouseLevel.getWarehouseTag());
		return warehouseLevelRepository.save(warehouseLevel);
	}
	public void delete(Long levelId) {
		warehouseLevelRepository.delete(levelId);
	}
	public Integer getMaxCode(Long subgroupId) {
		return warehouseLevelRepository.getMaxLevel(subgroupId);
	}
}