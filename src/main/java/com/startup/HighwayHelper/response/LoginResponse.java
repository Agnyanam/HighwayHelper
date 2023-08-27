package com.startup.HighwayHelper.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonRootName(value = "LoginResponse")
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	String code ; 
	String message;
	String token;
	
	public LoginResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	
}
