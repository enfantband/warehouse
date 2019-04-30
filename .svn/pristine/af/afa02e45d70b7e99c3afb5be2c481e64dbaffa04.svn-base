package com.samsbeauty.warehouse.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.samsbeauty.old.model.ReturnMessage;
import com.samsbeauty.old.model.ReturnMessageWithObject;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.employee.service.WarehouseEmployeeService;
import com.samsbeauty.warehouse.exception.rest.AuthorizationFailedException;

@Controller
public class LoginController {	
	

    @RequestMapping(value="/loginpage", method=RequestMethod.GET)
    public String login() {
    	return "login";
    }
    
    @RequestMapping(value="/loginCheck", method=RequestMethod.GET)
    public @ResponseBody String loginCheck(HttpServletRequest request) throws AuthorizationFailedException {
    	Principal principal = request.getUserPrincipal();
		if(principal == null || StringUtils.isEmpty(principal.getName())) {
			throw new AuthorizationFailedException("Authorization Failed");
		}
		return "Success";
    }
    
    @Autowired
    private WarehouseEmployeeService warehouseEmployeeService;
    
	@RequestMapping(value="/loginsuccess")
	public @ResponseBody ReturnMessageWithObject<WarehouseEmployee> loginSuccess(HttpServletRequest request, 
			HttpServletResponse response) {
		ReturnMessageWithObject<WarehouseEmployee> message = new ReturnMessageWithObject<WarehouseEmployee>();
		String employeeGid = (String) request.getSession().getAttribute("MEMBER_GID");
		WarehouseEmployee employeeInfo = warehouseEmployeeService.getOneByGid(employeeGid);
		message.setObject(employeeInfo);
		message.setMessage("SUCCESS");
		message.setSuccess(true);
		return message;
	}
	
	@RequestMapping(value="/loginfailed")
	public @ResponseBody ReturnMessage loginFailed(HttpServletRequest request, HttpServletResponse response) {
		ReturnMessage message = new ReturnMessage();
		message.setMessage("Login Failed.");
		message.setSuccess(false);
		return message;
	}
}
