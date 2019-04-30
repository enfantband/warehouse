package com.samsbeauty.warehouse.exception.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Warehouse group not find exception")
public class WarehouseGroupNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6235843899442926225L;

	public WarehouseGroupNotFoundException(String message) {
		super(message);
	}

}
