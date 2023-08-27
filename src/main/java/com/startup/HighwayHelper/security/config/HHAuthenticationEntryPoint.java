package com.startup.HighwayHelper.security.config;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.startup.HighwayHelper.response.LoginResponse;
import com.startup.HighwayHelper.utils.HighwayHelperConstants;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class HHAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		if (authException instanceof InvalidBearerTokenException) {
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			ObjectMapper mapper = new ObjectMapper();
			String res = mapper.writeValueAsString(new LoginResponse(HighwayHelperConstants.HH_AUTHENTICATION_ERROR_CODE,
					HighwayHelperConstants.HH_AUTHENTICATION_ERROR_INVALIDJWT));

			response.getOutputStream().println(res);
		}
		else if(authException instanceof InsufficientAuthenticationException) {
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);

			ObjectMapper mapper = new ObjectMapper();
			String res = mapper.writeValueAsString(new LoginResponse(HighwayHelperConstants.HH_NO_ENOUGH_AUTHENTICATION_ERROR_CODE,
					HighwayHelperConstants.HH_NO_ENOUGH_AUTHENTICATION_ERROR_MESSAGE));

			response.getOutputStream().println(res);
		}
		else {
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			ObjectMapper mapper = new ObjectMapper();
			String res = mapper.writeValueAsString(new LoginResponse(HighwayHelperConstants.HH_UNAUTHORIZED_ERROR_CODE,
					HighwayHelperConstants.HH_UNAUTHORIZED_ERROR_ERROR_MESSAGE));
			response.getOutputStream().println(res);
		}
		return;
	}

}
