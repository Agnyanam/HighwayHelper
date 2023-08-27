package com.startup.HighwayHelper.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startup.HighwayHelper.exception.HHDomainException;
import com.startup.HighwayHelper.exception.UserSignUpException;
import com.startup.HighwayHelper.mongo.service.UserDomainService;
import com.startup.HighwayHelper.request.UserSignUpRequest;
import com.startup.HighwayHelper.response.UserSignUpResponse;
import com.startup.HighwayHelper.utils.HighwayHelperConstants;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/***
 * 
 *Responsible to add admins to the system
 */
@RestController
@RequestMapping("/superUser")
@Slf4j
@PreAuthorize("hasAuthority('SCOPE_SUPERUSER')")
public class SuperUserController {
	
	@Autowired
	UserDomainService userDomainService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping(path = "/addAdmin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserSignUpResponse addUser(@RequestBody @Valid UserSignUpRequest userSignUpRequest) throws UserSignUpException, HHDomainException {

		log.debug("Received a valid request for admin Signup with name: " + userSignUpRequest.getUserName());
		
		try {
			
			//Update the password to encrypted the password
			userSignUpRequest.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));
			userSignUpRequest.setRoles(Arrays.asList("ADMIN"));
			
			userDomainService.saveUser(userSignUpRequest);
			
			log.debug("Admin saved successfully, User :" + userSignUpRequest);
			
		} catch (HHDomainException e) {

			if(e.getCode().equals(HighwayHelperConstants.USERNAME_ALREADY_EXISTS_CODE)) {
				throw new UserSignUpException(e.getCode(), e.getMessage());
			}
			else {
				throw new HHDomainException(e.getCode(), e.getMessage());
			}
			
		}
		return new UserSignUpResponse(HighwayHelperConstants.HH_USER_SIGNUP_SUCCESSFUL_CODE, HighwayHelperConstants.HH_USER_SIGNUP_SUCCESSFUL_MESSAGE);

	}
	
}
