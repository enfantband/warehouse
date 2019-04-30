package com.samsbeauty.warehouse.security.token.verifier;

public interface TokenVerifier {
	public boolean verify(String jti);
}
