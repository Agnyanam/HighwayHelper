package com.startup.HighwayHelper.security.config;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class JWTConfig {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.algorithm}")
	private String algorithm;

	@Bean
	public JwtEncoder jwtEncoder() {

		SecretKey key = new SecretKeySpec(secret.getBytes(), algorithm);
		JWKSource<SecurityContext> immutableSecret = new ImmutableSecret<>(key);
		return new NimbusJwtEncoder(immutableSecret);
	}

	@Bean
	public JwtDecoder jwtDecoder() {

		SecretKey originalKey = new SecretKeySpec(secret.getBytes(), algorithm);
		NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(originalKey).build();
		return jwtDecoder;
	}
}
