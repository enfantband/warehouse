package com.samsbeauty.warehouse.employee.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployeeDetails;
import com.samsbeauty.warehouse.employee.repository.WarehouseEmployeeRepository;
import com.samsbeauty.warehouse.role.model.Privilege;
import com.samsbeauty.warehouse.role.model.Role;

@Service
public class WarehouseEmployeeDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private WarehouseEmployeeRepository warehouseEmployeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		WarehouseEmployee warehouseEmployee = warehouseEmployeeRepository.getOneByGid(username);
		if(warehouseEmployee != null){
			List<GrantedAuthority> authList = getAuthorities(warehouseEmployee.getRoles());
			UserDetails userDetails = new WarehouseEmployeeDetails(warehouseEmployee, authList);
			return userDetails;
		}
		
		throw new UsernameNotFoundException("Cannot find username");
	}
	private List<GrantedAuthority> getAuthorities(Collection<Role> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}
	
	private List<String> getPrivileges(Collection<Role> roles) {
		List<String> privileges = new ArrayList<String>();
		List<Privilege> collection = new ArrayList<Privilege>();
		for(Role role : roles) {
			collection.addAll(role.getPrivileges());
		}
		
		for(Privilege item : collection) {
			privileges.add(item.getPrivilege());
		}
		return privileges;
	}
	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
}
