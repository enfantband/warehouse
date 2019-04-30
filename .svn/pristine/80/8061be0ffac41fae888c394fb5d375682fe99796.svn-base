package com.samsbeauty.warehouse.security.token;

import java.util.List;
import java.util.Optional;

import com.samsbeauty.warehouse.security.model.Scopes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class RefreshToken implements JwtToken {
	private Jws<Claims> claims;
	
	private RefreshToken(Jws<Claims> claims) {
		this.claims = claims;
	}
	
	/* 
	 * Create and validates Refresh token
	 * 
	 * @param token
	 * @param signingKey
	 * 
	 * @throws BadCredentialsException
	 * @throws JwtExpiredTokenException
	 * 
	 * @return
	 */
	public static Optional<RefreshToken> create(RawAccessJwtToken token, String signingKey) {
		Jws<Claims> claims = token.parseClaims(signingKey);

		String username = claims.getBody().get("username", String.class);
		
		if (username == null || username.isEmpty()) {
            return Optional.empty();
        }
		
		return Optional.of(new RefreshToken(claims));
	}
	
	@Override
	public String getToken() {
		return null;
	}
	
	public Jws<Claims> getClaims() {
		return claims;
	}

	public String getJti() {
		return claims.getBody().getId();
	}

	public String getSubject() {
		return claims.getBody().getSubject();
	}
}
