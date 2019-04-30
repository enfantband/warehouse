package com.samsbeauty.warehouse.exception.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Item already exists in the location")
public class ExistingWarehouseItemException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4916542046860934530L;

	public ExistingWarehouseItemException(String message) {
		super(message);
	}
}
