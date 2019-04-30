package com.samsbeauty.warehouse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.samsbeauty.warehouse.employee.service.WarehouseEmployeeDetailsServiceImpl;

@Configuration
public class Config {
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetailsService userDetailsService = new WarehouseEmployeeDetailsServiceImpl();
		return userDetailsService;
	}

}
