package com.samsbeauty.warehouse.woonseok.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.adjustment.model.AdjustmentRequest;
import com.samsbeauty.warehouse.adjustment.repository.AdjustmentRequestRepository;
import com.samsbeauty.warehouse.adjustment.specification.AdjustmentRequestSpecifications;
import com.samsbeauty.warehouse.woonseok.model.Woonseok;
import com.samsbeauty.warehouse.woonseok.repository.WoonseokRepository;
import com.samsbeauty.warehouse.woonseok.specification.WoonseokSpecification;

@Service
@Transactional
public class WoonseokServiceImpl implements WoonseokService {
	
	@Autowired
	private WoonseokRepository woonseokRepository;

	public List<Woonseok> getAll() {
		
		Specifications<Woonseok> chainFilter = Specifications.where(WoonseokSpecification.activated(true));
		return woonseokRepository.findAll();
	}

}