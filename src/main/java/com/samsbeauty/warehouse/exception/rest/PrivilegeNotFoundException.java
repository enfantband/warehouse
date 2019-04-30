package com.samsbeauty.warehouse.exception.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="No privilege")
public class PrivilegeNotFoundException extends Exception{
	
	private static final long serialVersionUID = 7127714816723338642L;

	public PrivilegeNotFoundException(String message) {
		super(message);
	}
}
