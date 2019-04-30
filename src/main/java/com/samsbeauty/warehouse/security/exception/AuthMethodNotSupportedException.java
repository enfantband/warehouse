package com.samsbeauty.warehouse.security.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthMethodNotSupportedException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8176268334169772077L;

	public AuthMethodNotSupportedException (String message) {
		super(message);
	}
}
