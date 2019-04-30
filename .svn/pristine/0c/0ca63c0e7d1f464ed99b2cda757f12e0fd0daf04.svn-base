package com.samsbeauty.warehouse.exception.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Too many results for your keyword")
public class TooManyResultsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2203358865397057420L;

	public TooManyResultsException(String message) {
		super(message);
	}
}
