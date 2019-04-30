package com.samsbeauty.warehouse.role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samsbeauty.warehouse.exception.rest.WrongParameterException;
import com.samsbeauty.warehouse.role.model.Role;
import com.samsbeauty.warehouse.role.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Value("${page.default.num}")
	protected Integer DEFAULT_NUM_PER_PAGE;

	// Retrieve All Resources
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<Role>> getAll() throws WrongParameterException {				
		List<Role> roleList = roleService.getAll();
		return new ResponseEntity<>(roleList, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST)
	Role add(@RequestBody @Valid final Role role) {
		Role newRole = new Role(role.getRoleName());
		return roleService.save(newRole);
	}

	@RequestMapping(method=RequestMethod.PUT)
	Role update(@RequestBody @Valid final Role role) {
		Role updateRole = roleService.getOne(role.getRoleId());
		updateRole.update(role.getRoleName());
		return roleService.save(updateRole);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{roleId}")
	Role delete(@PathVariable Long roleId) {
		Role deleteRole = roleService.getOne(roleId);
		return  roleService.save(deleteRole.deactivate());
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/ids/{roleIds}")
	ResponseEntity<List<Role>> deleteByIds(@PathVariable String roleIds) {
		List<String> ids = Arrays.asList(roleIds.split(","));
		List<Role> deletedList = new ArrayList<>();
		for(String id : ids) {
			Long roleId = Long.valueOf(id);
			Role role = roleService.getOne(roleId);
			deletedList.add(role);
			role.deactivate();
			roleService.save(role);
		}
		
		return new ResponseEntity<List<Role>> (deletedList, HttpStatus.OK);
	}

}