package com.samsbeauty.warehouse.employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.employee.service.WarehouseEmployeeService;
import com.samsbeauty.warehouse.exception.rest.WrongParameterException;
import com.samsbeauty.warehouse.role.model.Role;
import com.samsbeauty.warehouse.role.service.RoleService;

@RestController
@RequestMapping("/api/employee")
public class WarehouseEmployeeController {

	@Autowired
	private WarehouseEmployeeService warehouseEmployeeService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${page.default.num}")
	protected Integer DEFAULT_NUM_PER_PAGE;

	// Retrieve All Resources
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<WarehouseEmployee>> getAll() throws WrongParameterException {	
		List<WarehouseEmployee> warehouseEmployeeList = warehouseEmployeeService.getAll();
		return new ResponseEntity<>(warehouseEmployeeList, HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.GET, value="/pickers")
	List<WarehouseEmployee> getAllPickers() {
		Role pickerRole = roleService.getOneByRoleName("Picker");
		return pickerRole.getWarehouseEmployees();
	}
	@RequestMapping(method=RequestMethod.GET, value="/{warehouseEmployeeId}")
	WarehouseEmployee getOne(@PathVariable Long warehouseEmployeeId) {
		return warehouseEmployeeService.getOne(warehouseEmployeeId);
	}

	@RequestMapping(method=RequestMethod.POST)
	WarehouseEmployee add(@RequestBody @Valid final WarehouseEmployee warehouseEmployee) {
		WarehouseEmployee newWarehouseEmployee = WarehouseEmployee.builder(
				warehouseEmployee.getGid(), 
				passwordEncoder.encode(warehouseEmployee.getPassword()))
				.setName(warehouseEmployee.getName())
				.setActivated(true)
				.createWarehouseEmployee();

		//Implement here
		return warehouseEmployeeService.save(newWarehouseEmployee);
	}

	@RequestMapping(method=RequestMethod.PUT)
	WarehouseEmployee update(@RequestBody @Valid final WarehouseEmployee warehouseEmployee) {
		System.out.println(warehouseEmployee.toString());
		WarehouseEmployee updateWarehouseEmployee = warehouseEmployeeService.getOne(warehouseEmployee.getWarehouseEmployeeId());
		//Implement here
		updateWarehouseEmployee.update(passwordEncoder.encode(warehouseEmployee.getPassword()), warehouseEmployee.getName(), warehouseEmployee.getReportColor());
		return warehouseEmployeeService.save(updateWarehouseEmployee);
	}	

	@RequestMapping(method=RequestMethod.DELETE, value="/{warehouseEmployeeId}")
	WarehouseEmployee delete(@PathVariable Long warehouseEmployeeId) {
		WarehouseEmployee deleteWarehouseEmployee = warehouseEmployeeService.getOne(warehouseEmployeeId);
		deleteWarehouseEmployee.deactivate();
		warehouseEmployeeService.save(deleteWarehouseEmployee);
		return  deleteWarehouseEmployee;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/ids/{warehouseEmployeeIds}")
	ResponseEntity<List<WarehouseEmployee>> deleteByIds(@PathVariable String warehouseEmployeeIds) {
		List<String> ids = Arrays.asList(warehouseEmployeeIds.split(","));
		List<WarehouseEmployee> deletedList = new ArrayList<>();
		for(String id : ids) {
			Long employeeId = Long.valueOf(id);
			WarehouseEmployee warehouseEmployee = warehouseEmployeeService.getOne(employeeId);
			warehouseEmployee.deactivate();
			deletedList.add(warehouseEmployee);
			warehouseEmployeeService.save(warehouseEmployee);			
		}
		return new ResponseEntity<List<WarehouseEmployee>>(deletedList, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{warehouseEmployeeId}/role/{roleId}")
	WarehouseEmployee addRole(@PathVariable Long warehouseEmployeeId, @PathVariable Long roleId) {
		Role role = roleService.getOne(roleId);
		WarehouseEmployee warehouseEmployee = warehouseEmployeeService.getOne(warehouseEmployeeId);
		warehouseEmployee.addRole(role);
		return warehouseEmployeeService.save(warehouseEmployee);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{warehouseEmployeeId}/roles")
	WarehouseEmployee addRoles(@PathVariable Long warehouseEmployeeId, @RequestBody @Valid final List<Long> roleIds) {
		return warehouseEmployeeService.setRoles(warehouseEmployeeId, roleIds);
	}
}