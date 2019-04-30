package com.samsbeauty.warehouse.security.token;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenFactory {
	private final JwtSettings settings;
	
	@Autowired
	public JwtTokenFactory(JwtSettings settings) {
		this.settings = settings;
	}
	
	/*
	 * Factory method for issuing new JWT Tokens.
	 * 
	 * @param username
	 * @param roles
	 * @return
	 */
	public AccessJwtToken createAccessJwtToken(UserDetails userDetails) {
		if(StringUtils.isBlank(userDetails.getUsername())) {
			throw new IllegalArgumentException("Cannot create JWT Token without username");
		}
		
		if(userDetails.getAuthorities() == null || userDetails.getAuthorities().isEmpty()) {
			throw new IllegalArgumentException("User doesn't have any privileges");
		}
		
		Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
		claims.put("username", userDetails.getUsername());

        DateTime currentTime = new DateTime();

        String token = Jwts.builder()
          .setClaims(claims)
          .setIssuer(settings.getTokenIssuer())
          .setIssuedAt(currentTime.toDate())
          .setExpiration(currentTime.plusMinutes(settings.getTokenExpirationTime()).toDate())
          .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
        .compact();

        return new AccessJwtToken(token, claims);
	}
	
	public JwtToken createRefreshToken(UserDetails userDetails) {
		if(StringUtils.isBlank(userDetails.getUsername())) {
			throw new IllegalArgumentException("Cannot create JWT Token without username");
		}
		
		DateTime currentTime = new DateTime();
		
		Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
		claims.put("username", userDetails.getUsername());
		
		String token = Jwts.builder()
				.setClaims(claims)
				.setIssuer(settings.getTokenIssuer())
				.setId(UUID.randomUUID().toString())
				.setIssuedAt(currentTime.toDate())
				.setExpiration(currentTime.plusMinutes(settings.getRefreshTokenExpTime()).toDate())
				.signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
				.compact();
		
		return new AccessJwtToken(token, claims);
	}
	
}
