package com.samsbeauty.warehouse.security.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.samsbeauty.warehouse.security.SecurityConfig;
import com.samsbeauty.warehouse.security.exception.InvalidJwtToken;
import com.samsbeauty.warehouse.security.token.JwtSettings;
import com.samsbeauty.warehouse.security.token.JwtToken;
import com.samsbeauty.warehouse.security.token.JwtTokenFactory;
import com.samsbeauty.warehouse.security.token.RawAccessJwtToken;
import com.samsbeauty.warehouse.security.token.RefreshToken;
import com.samsbeauty.warehouse.security.token.extractor.TokenExtractor;
import com.samsbeauty.warehouse.security.token.verifier.TokenVerifier;

@RestController
public class RefreshTokenEndpoint {
	@Autowired
	private JwtTokenFactory tokenFactory;
	@Autowired
	private JwtSettings jwtSettings;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private TokenVerifier tokenVerifier;
	@Autowired @Qualifier("jwtHeaderTokenExtractor")
	private TokenExtractor tokenExtractor;
	
	@RequestMapping(value="/api/auth/token", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String tokenPayload = tokenExtractor.extract(request.getHeader(SecurityConfig.JWT_TOKEN_HEADER_PARAM));
		
		RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
		RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());
		
		String jti = refreshToken.getJti();
		if(!tokenVerifier.verify(jti)) {
			throw new InvalidJwtToken();
		}
		
		String subject = refreshToken.getSubject();
		UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
		
		return tokenFactory.createAccessJwtToken(userDetails);
	}
	
}
