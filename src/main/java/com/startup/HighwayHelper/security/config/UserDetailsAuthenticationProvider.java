package com.startup.HighwayHelper.security.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.startup.HighwayHelper.exception.HHDomainException;
import com.startup.HighwayHelper.mongo.model.UserDBObject;
import com.startup.HighwayHelper.mongo.service.UserDomainService;

@Component
public class UserDetailsAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserDomainService userDomainService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		HHAuthenticationToken token = (HHAuthenticationToken) authentication;
		
		try {
			
			UserDBObject user = userDomainService.findByUserName(token.getName());
			
			if(encoder.matches(token.getCredentials().toString(), user.getPassword())) {
				//Password Matched
				List<GrantedAuthority> authorities = new ArrayList<>();
				
				if(user.getRoles()!=null){
					user.getRoles().forEach( (role) -> {
						authorities.add(new SimpleGrantedAuthority(role));
					});
				}
				return new HHAuthenticationToken(user,token.getPrincipal(),token.getCredentials(),authorities);
			}
			else {
				//Password did not match
				//Unauthorized token
				return token;
			}
			
		} catch (HHDomainException e) {
			
			return token;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return HHAuthenticationToken.class.isAssignableFrom(authentication);
	}

	
	
}
