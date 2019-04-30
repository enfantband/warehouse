package com.samsbeauty.warehouse.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.samsbeauty.warehouse.menu.model.Menu;
import com.samsbeauty.warehouse.menu.service.MenuService;
import com.samsbeauty.warehouse.role.model.Role;
import com.samsbeauty.warehouse.role.service.RoleService;

@RestController
@RequestMapping("/api/menu")
public class MenuRestController {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private RoleService roleService;

	@Value("${page.default.num}")
	protected Integer DEFAULT_NUM_PER_PAGE;

	// Retrieve All Resources
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<Menu>> getAll() throws WrongParameterException {
		List<Menu> menuList = menuService.getAll();
		return new ResponseEntity<List<Menu>>(menuList, HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.GET, value="/adminmenu")
	List<Menu> getAdminMenu(HttpServletRequest request) {
		return menuService.getAll((String) request.getAttribute("GID"));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{menuId}")
	Menu getOne(@PathVariable Long menuId) {
		return menuService.getOne(menuId);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{menu}/role/{roleId}")
	Menu addRole(@PathVariable Long warehouseEmployeeId, @PathVariable Long roleId) {
		Role role = roleService.getOne(roleId);
		Menu menu = menuService.getOne(warehouseEmployeeId);
		menu.addRole(role);
		return menuService.save(menu);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{menuId}/roles")
	Menu addRoles(@PathVariable Long menuId, @RequestBody @Valid final List<Long> roleIds) {
		return menuService.setRoles(menuId, roleIds);
	}

	@RequestMapping(method=RequestMethod.POST)
	Menu add(@RequestBody @Valid final Menu menu) {
		Menu newMenu = Menu.builder()
				.setName(menu.getName())
				.setDescription(menu.getDescription())
				.setUrl(menu.getUrl())
				.setIcon(menu.getIcon())
				.setOrdered(menu.getOrdered())
				.setActivated(true)
				.setRegDate(new Date())
				.createMenu();
		//Implement here
		return menuService.save(newMenu);
	}

	@RequestMapping(method=RequestMethod.PUT)
	Menu update(@RequestBody @Valid final Menu menu) {
		Menu updateMenu = menuService.getOne(menu.getMenuId());
		//Implement here
		updateMenu.update(
				menu.getName(), 
				menu.getDescription(), 
				menu.getUrl(), 
				menu.getIcon(), 
				menu.getOrdered()
			);
		menuService.save(updateMenu);
		return updateMenu;
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{menuId}")
	Menu delete(@PathVariable Long menuId) {
		Menu deleteMenu = menuService.getOne(menuId);
		deleteMenu.deactivate();
		menuService.save(deleteMenu);
		return  deleteMenu;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/ids/{menuIds}")
	ResponseEntity<List<Menu>> deleteByIds(@PathVariable String menuIds) {
		List<String> ids = Arrays.asList(menuIds.split(","));
		List<Menu> deletedList = new ArrayList<>();
		for(String id : ids) {
			Long menuId = Long.valueOf(id);
			Menu deletedMenu = menuService.getOne(menuId);
			deletedList.add(deletedMenu);
			deletedMenu.deactivate();
			menuService.save(deletedMenu);
		}
		return new ResponseEntity<List<Menu>> (deletedList, HttpStatus.OK);
	}
	
}