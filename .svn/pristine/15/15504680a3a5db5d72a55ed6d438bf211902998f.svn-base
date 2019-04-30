package com.samsbeauty.warehouse.picking.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.samsbeauty.warehouse.picking.model.PickingGroupCompany;
import com.samsbeauty.warehouse.picking.repository.PickingGroupCompanyRepository;

@Service
@Transactional
public class PickingGroupCompanyServiceImpl implements PickingGroupCompanyService {
	@Autowired
	private PickingGroupCompanyRepository pickingGroupCompanyRepository;

	public Page<PickingGroupCompany> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return pickingGroupCompanyRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));
	}
	public PickingGroupCompany getOneByCompanyCodeAndPickingGroupId(String companyCode, Long pickingGroupId) {
		return pickingGroupCompanyRepository.getByCompanyCodeAndPickingGroupId(companyCode, pickingGroupId);
	}
	public boolean existsByCompanyCodeAndPickingGroupId(String companyCode, Long pickingGroupId) {
		return pickingGroupCompanyRepository.existsByCompanyCodeAndPickingGroupId(companyCode, pickingGroupId);
	}
	public PickingGroupCompany getOne(Long pickingGroupCompanyId) {
		return pickingGroupCompanyRepository.getOne(pickingGroupCompanyId);
	}
	public PickingGroupCompany save(PickingGroupCompany pickingGroupCompany) {
		return pickingGroupCompanyRepository.save(pickingGroupCompany);
	}
	public void delete(Long pickingGroupCompanyId) {
		pickingGroupCompanyRepository.delete(pickingGroupCompanyId);
	}
}