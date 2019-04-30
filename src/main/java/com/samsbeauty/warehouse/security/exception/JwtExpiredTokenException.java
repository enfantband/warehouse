package com.samsbeauty.warehouse.security.exception;

import org.springframework.security.core.AuthenticationException;

import com.samsbeauty.warehouse.security.token.JwtToken;

public class JwtExpiredTokenException extends AuthenticationException {

	private static final long serialVersionUID = 7552257716153336876L;
	
	private JwtToken token;
	
	public JwtExpiredTokenException(String msg) {
		super(msg);
	}
	
	public JwtExpiredTokenException(JwtToken token, String msg, Throwable t) {
		super(msg, t);
		this.token = token;
	}
	
	public String token() {
		return this.token.getToken();
	}
}
