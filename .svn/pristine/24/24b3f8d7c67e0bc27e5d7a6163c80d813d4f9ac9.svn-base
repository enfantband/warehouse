package com.samsbeauty.warehouse.role.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.role.model.Privilege;
import com.samsbeauty.warehouse.role.repository.PrivilegeRepository;

@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {
	@Autowired
	private PrivilegeRepository privilegeRepository;

	public Page<Privilege> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return privilegeRepository.findByActivated(true, new PageRequest(pageNumber-1, pageSize, sort));
	}
	public List<Privilege> getAll() {
		return privilegeRepository.findAll();
	}
	public Privilege getOne(Long privilegeId) {
		return privilegeRepository.getOne(privilegeId);
	}
	public Privilege save(Privilege privilege) {
		return privilegeRepository.save(privilege);
	}
	public void delete(Long privilegeId) {
		privilegeRepository.delete(privilegeId);
	}
}