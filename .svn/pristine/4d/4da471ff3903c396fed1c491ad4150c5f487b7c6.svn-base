package com.samsbeauty.warehouse.role.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.samsbeauty.warehouse.role.model.Role;
import com.samsbeauty.warehouse.role.repository.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	public List<Role> getAll() {
		return roleRepository.findAll();
	}
	public Page<Role> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return roleRepository.findByActivated(true, new PageRequest(pageNumber-1, pageSize, sort));
	}
	public Role getOne(Long roleId) {
		return roleRepository.getOne(roleId);
	}
	public Role getOneByRoleName(String roleName) {
		return roleRepository.getOneByRoleName(roleName);
		
	}
	public Role save(Role role) {
		return roleRepository.save(role);
	}
	public void delete(Long roleId) {
		roleRepository.delete(roleId);
	}
}