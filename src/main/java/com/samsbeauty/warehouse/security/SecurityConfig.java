package com.samsbeauty.warehouse.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsbeauty.warehouse.security.filter.JwtTokenAuthenticationProcessingFilter;
import com.samsbeauty.warehouse.security.filter.RestLoginProcessingFilter;
import com.samsbeauty.warehouse.security.token.JwtSettings;
import com.samsbeauty.warehouse.security.token.extractor.TokenExtractor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/api/auth/login";
    public static final String FORM_BASED_LOGOUT_ENTRY_POINT = "/api/auth/logout";
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/api/**";
    public static final String TOKEN_REFRESH_ENTRY_POINT = "/api/auth/token";
	
	@Autowired 
	private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired 
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private SimpleUrlLogoutSuccessHandler restLogoutSuccessHandler;
    @Autowired 
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private JwtSettings jwtSettings;
    
    @Autowired
    private TokenExtractor tokenExtractor;
    
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private UserDetailsService userDetailsService;	
        
	@Bean
	protected RestLoginProcessingFilter buildRestLoginProcessingFilter() throws Exception {
		RestLoginProcessingFilter filter = new RestLoginProcessingFilter(FORM_BASED_LOGIN_ENTRY_POINT, successHandler, failureHandler, objectMapper);
		filter.setAuthenticationManager(super.authenticationManagerBean());
		return filter;
	}
	
	@Bean
    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        List<String> pathsToSkip = Arrays.asList(TOKEN_REFRESH_ENTRY_POINT, FORM_BASED_LOGIN_ENTRY_POINT);
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
        JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(super.authenticationManagerBean());
        return filter;
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		RestAuthenticationProvider restAuthenticationProvider = new RestAuthenticationProvider();
		restAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		restAuthenticationProvider.setUserDetailsService(userDetailsService);
		auth.authenticationProvider(restAuthenticationProvider);
		
		JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider(jwtSettings);
		jwtAuthenticationProvider.setUserDetailsService(userDetailsService);
		auth.authenticationProvider(jwtAuthenticationProvider);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		 http
		 	.logout()
		 	.logoutSuccessHandler(restLogoutSuccessHandler)
		 	.logoutUrl("/logout")
		 .and()
		 	.csrf().disable() // don't need it for JWT based authentication
		 	.exceptionHandling().authenticationEntryPoint(this.authenticationEntryPoint)
//		 .and()
//		 	.sessionManagement()
//		 	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)		 
		 .and()
		 	.authorizeRequests()
		 	.antMatchers("/loginpage", "/css/**", "/js/**", "/img/**", "/fonts/**")
		 	.permitAll()
		 .and()
			.authorizeRequests()
				.antMatchers(FORM_BASED_LOGIN_ENTRY_POINT).permitAll()
				.antMatchers(TOKEN_REFRESH_ENTRY_POINT).permitAll()
		 .and()
		 	.authorizeRequests()
		 	.antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated()
		 .and()
		 	.addFilterBefore(buildRestLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
		 	.addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
		 	;
	}
	
}
