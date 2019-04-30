package com.samsbeauty.warehouse.employee.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class WarehouseEmployeeDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 991004339099163907L;
	
	private WarehouseEmployee warehouseEmployee;
	private Collection<? extends GrantedAuthority> authorities;
	
	public WarehouseEmployeeDetails(WarehouseEmployee warehouseEmployee, Collection<? extends GrantedAuthority> authorities) {
		this.warehouseEmployee = warehouseEmployee;
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return warehouseEmployee.getPassword();
	}
	
	@Override
	public String getUsername() {
		return warehouseEmployee.getGid();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return warehouseEmployee.getActivated();
	}

}
