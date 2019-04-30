package com.samsbeauty.warehouse.exception.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Authorization Failed")
public class AuthorizationFailedException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -565020327641097362L;

	public AuthorizationFailedException(String message) {
		super(message);
	}
}
