package com.samsbeauty.warehouse.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsbeauty.warehouse.security.token.JwtToken;
import com.samsbeauty.warehouse.security.token.JwtTokenFactory;

@Component
public class RestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private final ObjectMapper mapper;
	private final JwtTokenFactory tokenFactory;
	
	@Autowired
	public RestAwareAuthenticationSuccessHandler(final ObjectMapper mapper, final JwtTokenFactory tokenFactory) {
		this.mapper = mapper;
		this.tokenFactory = tokenFactory;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
		JwtToken accessToken = tokenFactory.createAccessJwtToken(userDetails);
		JwtToken refreshToken = tokenFactory.createRefreshToken(userDetails);
		
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", accessToken.getToken());
		tokenMap.put("refreshToken", refreshToken.getToken());
		tokenMap.put("memberGid", userDetails.getUsername());

		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		mapper.writeValue(response.getWriter(), tokenMap);

        request.setAttribute("GID", userDetails.getUsername());
		clearAuthenticationAttributes(request);
	}
	
	/*
	 * Remove temporary authentication-related data which may have been stored in the session during the authentication process.
	 */
	protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
