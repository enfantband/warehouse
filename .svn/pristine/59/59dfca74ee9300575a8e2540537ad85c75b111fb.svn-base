package com.samsbeauty.warehouse.picking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Company already exists in the picking group")
public class CompanyExistingInPickingGroupException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3070116605334649928L;
	public CompanyExistingInPickingGroupException(String message) {
		super(message);
	}
}
