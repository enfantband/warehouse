package com.samsbeauty.warehouse.security.token.verifier;

import org.springframework.stereotype.Component;

@Component
public class BloomFilterTokenVerifier implements TokenVerifier {

	@Override
	public boolean verify(String jti) {
		return true;
	}
	
}
