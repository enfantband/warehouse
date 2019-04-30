package com.samsbeauty.warehouse.picking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Matching item with the barcode not exist")
public class WrongItemScanException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4264313503087104232L;

	public WrongItemScanException(String message) {
		super(message);
	}
}
