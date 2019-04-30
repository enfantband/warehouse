package com.samsbeauty.warehouse.exception.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Wrong date format exception !!")
public class WrongDateFormatException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3050766321616681732L;
	
	public WrongDateFormatException(String message) {
		super(message);
	}

}
