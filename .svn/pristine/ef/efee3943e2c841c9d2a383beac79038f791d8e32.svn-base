package com.samsbeauty.warehouse.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.samsbeauty.warehouse.model.WarehouseOutbound;
import com.samsbeauty.warehouse.repository.WarehouseOutboundRepository;

@Service
@Transactional
public class WarehouseOutboundServiceImpl implements WarehouseOutboundService {
	@Autowired
	private WarehouseOutboundRepository warehouseOutboundRepository;

	public Page<WarehouseOutbound> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return warehouseOutboundRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));
	}
	public WarehouseOutbound getOne(Long outboundId) {
		return warehouseOutboundRepository.getOne(outboundId);
	}
	public WarehouseOutbound save(WarehouseOutbound warehouseOutbound) {
		return warehouseOutboundRepository.save(warehouseOutbound);
	}
	public void delete(Long outboundId) {
		warehouseOutboundRepository.delete(outboundId);
	}
}