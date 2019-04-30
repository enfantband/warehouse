package com.samsbeauty.warehouse.exception.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Picking Job Timeline not find exception")
public class PickingJobTimelineNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3854776303792839688L;

	public PickingJobTimelineNotFoundException(String message) {
		super(message);
	}
}
