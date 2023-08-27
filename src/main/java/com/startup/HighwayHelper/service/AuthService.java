package com.startup.HighwayHelper.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.startup.HighwayHelper.request.LoginRequest;
import com.startup.HighwayHelper.response.LoginResponse;
import com.startup.HighwayHelper.security.config.HHAuthenticationToken;
import com.startup.HighwayHelper.security.config.User;
import com.startup.HighwayHelper.utils.HighwayHelperConstants;
import com.startup.HighwayHelper.utils.JWTUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

	@Autowired
	AuthenticationProvider authProvider;
	
	@Autowired
	JWTUtils jwtUtils;
	
	public Object processLogin(LoginRequest loginRequest) {

		try {
				
			HHAuthenticationToken token = new HHAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());
			
			HHAuthenticationToken updatedToken = (HHAuthenticationToken) authProvider.authenticate(token);
			
			if(updatedToken.isAuthenticated()) {
				
				//Create JWT Token and send it back
				ObjectMapper mapper = new ObjectMapper();
				User user = new User();
				user.setUserName(loginRequest.getUsername());
				String userDetails = mapper.writeValueAsString(user);
				
				List<String> scopesList = updatedToken.getAuthorities().stream().map( (authority) -> authority.getAuthority()).collect(Collectors.toList());
				
				LoginResponse response = new LoginResponse();
				response.setToken(jwtUtils.createJWTToken(updatedToken.getPrincipal().toString(), userDetails, scopesList));
				
				return ResponseEntity.ok(response);
			}
			else {
				LoginResponse response = new LoginResponse(HighwayHelperConstants.HH_LOGIN_INVALID_CODE , HighwayHelperConstants.HH_LOGIN_INVALID_MESSAGE);
				return ResponseEntity.badRequest().body(response);
			}
		} catch (Exception e) {
			
			log.error("Exception occured in User validation, Exception " + e.getMessage());
			LoginResponse response = new LoginResponse(HighwayHelperConstants.INTERNAL_ERROR_CODE , HighwayHelperConstants.INTERNAL_ERROR_MESSAGE);
			return ResponseEntity.badRequest().body(response);
		}

	}
}
