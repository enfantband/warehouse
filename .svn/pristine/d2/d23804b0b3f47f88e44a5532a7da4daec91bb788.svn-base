package com.samsbeauty.warehouse.picking.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.picking.model.PickingJobGroup;
import com.samsbeauty.warehouse.picking.repository.PickingJobGroupRepository;
import com.samsbeauty.warehouse.picking.specification.PickingJobGroupSpecifications;

import static com.samsbeauty.warehouse.picking.specification.PickingJobGroupSpecifications.*;
import static org.springframework.data.jpa.domain.Specifications.where;

@Service
@Transactional
public class PickingJobGroupServiceImpl implements PickingJobGroupService {
	@Autowired
	private PickingJobGroupRepository pickingJobGroupRepository;

	public Page<PickingJobGroup> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return pickingJobGroupRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));
	}
	public Page<PickingJobGroup> getAll(Integer pageNumber, Integer pageSize, Sort sort, Date searchDateFrom, Date searchDateTo) {		
		Page<PickingJobGroup> groups = null;
		try {
			groups = pickingJobGroupRepository.findAll(where(activatedPickingJobGroup(true)).and(searchByDate(searchDateFrom, searchDateTo)), new PageRequest(pageNumber-1, pageSize, sort));	
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
		return groups;

	}
	public List<PickingJobGroup> getAll(Date searchDateFrom, Date searchDateTo) {
		return pickingJobGroupRepository.findAll(where(activatedPickingJobGroup(true)).and(searchByDate(searchDateFrom, searchDateTo)));
	}
	public PickingJobGroup getOne(Long pickingJobGroupId) {
		return pickingJobGroupRepository.getOne(pickingJobGroupId);
	}
	public PickingJobGroup save(PickingJobGroup pickingJobGroup) {
		return pickingJobGroupRepository.save(pickingJobGroup);
	}
	public void delete(Long pickingJobGroupId) {
		pickingJobGroupRepository.delete(pickingJobGroupId);
	}
	public Integer getMaxPickingSet() {
		return pickingJobGroupRepository.getMaxPickingSet();
	}
}