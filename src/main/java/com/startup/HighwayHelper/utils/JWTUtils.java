package com.startup.HighwayHelper.utils;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

@Component
public class JWTUtils {

	@Autowired
	JwtDecoder jwtDecoder;

	@Autowired
	JwtEncoder jwtEncoder;

	@Value("${jwt.validity}")
	int JWT_TOKEN_VALIDITY;

	public String createJWTToken(String username, String userDetails, List<String> scopes) {

		Instant issuedAt = Instant.now();
		Instant expiresAt = issuedAt.plusMillis(JWT_TOKEN_VALIDITY * 1000);

		JwtClaimsSet claims = JwtClaimsSet.builder().subject(username).issuer("HH").claims((map) -> {
			map.put("userDetails", userDetails);
			map.put("scope", scopes);
		}).issuedAt(issuedAt).expiresAt(expiresAt).build();
		JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();

		return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
	}

	public String getUserDetailsFromCContext() {

		String userDetails = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getClaim("userDetails");

		return userDetails;

	}

}
