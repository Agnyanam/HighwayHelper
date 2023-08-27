package com.startup.HighwayHelper.security.config;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import com.startup.HighwayHelper.mongo.model.UserDBObject;

public class HHAuthenticationToken extends UsernamePasswordAuthenticationToken {

	
	private static final long serialVersionUID = 1L;
	
	private UserDBObject userObject;

	public HHAuthenticationToken(UserDBObject userObject , Object principal, Object credentials) {
		//Initializes the token with Authorized as false
		super(principal, credentials);
		this.userObject = userObject;
	}
	
	public HHAuthenticationToken(Object principal, Object credentials) {
		//Initializes the token with Authorized as false
		super(principal, credentials);
	}
	
	public HHAuthenticationToken(UserDBObject userObject, Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		//Initializes the token with Authorized as true
		super(principal, credentials,authorities);
		this.userObject= userObject;
	}

	public UserDBObject getUserObject() {
		return userObject;
	}
	
}
