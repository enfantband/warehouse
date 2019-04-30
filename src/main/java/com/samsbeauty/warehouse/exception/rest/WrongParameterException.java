package com.samsbeauty.warehouse.exception.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid Parameter")
public class WrongParameterException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1267409633868858070L;

	public WrongParameterException(String message) {
		super(message);
	}
}