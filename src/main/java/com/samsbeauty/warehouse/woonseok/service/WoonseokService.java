package com.samsbeauty.warehouse.woonseok.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.samsbeauty.warehouse.adjustment.model.AdjustmentRequest;
import com.samsbeauty.warehouse.woonseok.model.Woonseok;

public interface WoonseokService {
	
	List<Woonseok> getAll();

}