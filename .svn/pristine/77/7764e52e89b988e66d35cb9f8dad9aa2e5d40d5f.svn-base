package com.samsbeauty.warehouse.exception.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Role already has the privilege")
public class DuplicatePrivilegeToRoleException extends Exception {
	private static final long serialVersionUID = -8075158903957656379L;
	
	public DuplicatePrivilegeToRoleException(String message) {
		super(message);
	}
}
