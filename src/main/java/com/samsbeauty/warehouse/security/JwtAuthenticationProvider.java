package com.samsbeauty.warehouse.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.samsbeauty.warehouse.security.token.JwtAuthenticationToken;
import com.samsbeauty.warehouse.security.token.JwtSettings;
import com.samsbeauty.warehouse.security.token.RawAccessJwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class JwtAuthenticationProvider implements AuthenticationProvider {
	private final JwtSettings jwtSettings;
	
	private UserDetailsService userDetailsService;
	
	public JwtAuthenticationProvider(JwtSettings jwtSettings) {
		this.jwtSettings = jwtSettings;
	}
	
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	public Authentication authenticate (Authentication authentication) throws AuthenticationException {
		RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();
		Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
		String username = jwsClaims.getBody().get("username", String.class);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		return new JwtAuthenticationToken(userDetails, userDetails.getAuthorities());
	}
	
    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
