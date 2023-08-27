package com.startup.HighwayHelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.startup.HighwayHelper.request.LoginRequest;
import com.startup.HighwayHelper.service.AuthService;

@RestController
public class AuthController {

	@Autowired
	AuthService authService;

	@PostMapping(path = "/login" , consumes =  MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public Object login(@RequestBody LoginRequest loginRequesst) {

		return authService.processLogin(loginRequesst);
	}

}
