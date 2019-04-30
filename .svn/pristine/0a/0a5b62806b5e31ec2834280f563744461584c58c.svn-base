package com.samsbeauty.warehouse.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.samsbeauty.warehouse.model.WarehouseInbound;
import com.samsbeauty.warehouse.repository.WarehouseInboundRepository;

@Service
@Transactional
public class WarehouseInboundServiceImpl implements WarehouseInboundService {
	@Autowired
	private WarehouseInboundRepository warehouseInboundRepository;

	public Page<WarehouseInbound> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return warehouseInboundRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));
	}
	public WarehouseInbound getOne(Long inboundId) {
		return warehouseInboundRepository.getOne(inboundId);
	}
	public WarehouseInbound save(WarehouseInbound warehouseInbound) {
		return warehouseInboundRepository.save(warehouseInbound);
	}
	public void delete(Long inboundId) {
		warehouseInboundRepository.delete(inboundId);
	}
}