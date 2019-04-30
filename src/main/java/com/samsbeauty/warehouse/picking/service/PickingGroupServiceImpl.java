package com.samsbeauty.warehouse.picking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samsbeauty.old.model.Company;
import com.samsbeauty.warehouse.old.helper.CompanyRestHelper;
import com.samsbeauty.warehouse.picking.model.PickingGroup;
import com.samsbeauty.warehouse.picking.model.PickingGroupCompany;
import com.samsbeauty.warehouse.picking.repository.PickingGroupRepository;

@Service
@Transactional
public class PickingGroupServiceImpl implements PickingGroupService {
	@Autowired
	private PickingGroupRepository pickingGroupRepository;
	@Autowired
	private CompanyRestHelper companyRestHelper;

	public List<PickingGroup> getAll() {
		return pickingGroupRepository.findAll();
	}
	public Page<PickingGroup> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return pickingGroupRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));
	}
	public PickingGroup getOne(Long pickingGroupId) {
		return pickingGroupRepository.getOne(pickingGroupId);
	}
	public PickingGroup save(PickingGroup pickingGroup) {
		return pickingGroupRepository.save(pickingGroup);
	}
	public void delete(Long pickingGroupId) {
		pickingGroupRepository.delete(pickingGroupId);
	}

	public Map<String, PickingGroup> getPickingGroupTable() {
		// Put each company into appropriate picking group.
		List<Company> companyList = companyRestHelper.getList();
		List<PickingGroup> pickingGroups = pickingGroupRepository.findAll();
		
		Map<String, PickingGroup> companyGroups = new HashMap<>();
		PickingGroup etcGroup = null;
		for(PickingGroup pickingGroup: pickingGroups) {
			List<PickingGroupCompany> groupCompanies = pickingGroup.getPickingGroupCompanies();

			// custom filter
			if(pickingGroup.getName().equals("AMZEB")){
				companyGroups.put("AMZEB", pickingGroup);
			} else if(pickingGroup.getName().equals("GM")){
				companyGroups.put("GM", pickingGroup);
			} else if(groupCompanies.size() == 0) {
				etcGroup = pickingGroup;
			} else {
				for(PickingGroupCompany company : groupCompanies) {
					if(!companyGroups.containsKey(company.getCompanyCode())) {
						companyGroups.put(company.getCompanyCode(), pickingGroup);
					}
				}
			}
		}
		if(etcGroup != null) {
			// set every else companies as a key for the etc group
			for(Company company : companyList) {
				if(!companyGroups.containsKey(company.getCode())) {
					companyGroups.put(company.getCode(), etcGroup);
				}
			}
		}
		return companyGroups;
	}
}