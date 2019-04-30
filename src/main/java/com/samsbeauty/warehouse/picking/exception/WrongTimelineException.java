package com.samsbeauty.warehouse.picking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Wrong pickingjob timeline exception")
public class WrongTimelineException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 414422590401621804L;

	public WrongTimelineException(String message) {
		super(message);
	}
}
