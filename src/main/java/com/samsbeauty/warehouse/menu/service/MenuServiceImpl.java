package com.samsbeauty.warehouse.menu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.employee.repository.WarehouseEmployeeRepository;
import com.samsbeauty.warehouse.menu.MenuSpecifications;
import com.samsbeauty.warehouse.menu.model.Menu;
import com.samsbeauty.warehouse.menu.repository.MenuRepository;
import com.samsbeauty.warehouse.role.model.Role;
import com.samsbeauty.warehouse.role.repository.RoleRepository;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private WarehouseEmployeeRepository warehouseEmployeeRepository;
	
	public Page<Menu> getAll(Integer pageNumber, Integer pageSize, Sort sort, String searchTerm) {
		Specifications<Menu> chainFilter = Specifications.where(MenuSpecifications.activatedMenu(true)).and(MenuSpecifications.searchTerm(searchTerm));
		return menuRepository.findAll(chainFilter, new PageRequest(pageNumber-1, pageSize, sort));		
	}
	public Page<Menu> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return menuRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));		
	}
	public List<Menu> getAll() {
		return menuRepository.findAll(Specifications.where(MenuSpecifications.activatedMenu(true)));
	}
	public List<Menu> getAll(String gid) {
		WarehouseEmployee employee = warehouseEmployeeRepository.getOneByGid(gid);
		Set<Role> roles = employee.getRoles();
		List<Menu> menuList = menuRepository.findAll(Specifications.where(MenuSpecifications.activatedMenu(true)));
		List<Menu> authorizedList = new ArrayList<>();
		for(Menu menu : menuList) {
			for(Role role : menu.getRoles()) {
				if(roles.contains(role)) {
					authorizedList.add(menu);
					break;
				}
			}
		}
		
		return authorizedList;
	}
	public Menu getOne(Long menuId) {
		return menuRepository.getOne(menuId);
	}
	public Menu save(Menu menu) {
		return menuRepository.save(menu);
	}
	public void delete(Long menuId) {
		menuRepository.delete(menuId);
	}
	public Menu setRoles(Long menuId, List<Long> roleIds) {
		Menu menu = menuRepository.getOne(menuId);
		menu.removeAllRoles();
		
		for(Long roleId : roleIds) {
			Role role = roleRepository.getOne(roleId);
			menu.addRole(role);
		}
		System.out.println("save");
		return menuRepository.save(menu);
	}
}