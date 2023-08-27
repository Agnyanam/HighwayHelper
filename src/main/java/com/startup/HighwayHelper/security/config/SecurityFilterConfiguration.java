package com.startup.HighwayHelper.security.config;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityFilterConfiguration {

	@Autowired
	CustomCorsFilter customCorsFilter;

	@Autowired
	HHAuthenticationEntryPoint entryPoint;

	@Autowired
	JwtDecoder jwtDecoder;

	private static final String[] AUTH_WHITELIST = { "/login", "/User/signup" };

	private static final String[] AUTH_USER = { "/ping" };

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http
				.authorizeHttpRequests((authorizehttpRequests) -> authorizehttpRequests.requestMatchers(AUTH_WHITELIST)
						.permitAll().requestMatchers(AUTH_USER).hasAuthority("SCOPE_USER").anyRequest().authenticated())
				.addFilterBefore(customCorsFilter, ChannelProcessingFilter.class)
				.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf((httpSecurity) -> httpSecurity.disable()).httpBasic(httpBasic -> httpBasic.disable())
				.oauth2ResourceServer((oauth2) -> oauth2.authenticationEntryPoint(entryPoint)
						.jwt(jwtconfigurer -> jwtconfigurer.decoder(jwtDecoder)))
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4, new SecureRandom());
	}
}
