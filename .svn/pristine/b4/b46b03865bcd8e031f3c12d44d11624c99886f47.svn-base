package com.samsbeauty.warehouse.exception.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid Barcode")
public class InvalidProductBarcodeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9166770599257630934L;

	public InvalidProductBarcodeException(String message) {
		super(message);
	}
}