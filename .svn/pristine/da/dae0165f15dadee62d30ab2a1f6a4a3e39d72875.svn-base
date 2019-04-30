package com.samsbeauty.warehouse.adjustment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.samsbeauty.warehouse.adjustment.model.AdjustmentRequest;

public interface AdjustmentRequestService {
	Page<AdjustmentRequest> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	List<AdjustmentRequest> getAll();
	AdjustmentRequest getOne(Long requestId);
	AdjustmentRequest getOneByPickingItemId(Long pickingItemId);
	AdjustmentRequest save(AdjustmentRequest adjustmentRequest);
	void delete(Long requestId);
}