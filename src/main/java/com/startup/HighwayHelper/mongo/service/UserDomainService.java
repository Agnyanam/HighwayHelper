package com.startup.HighwayHelper.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startup.HighwayHelper.exception.HHDomainException;
import com.startup.HighwayHelper.mongo.model.UserDBObject;
import com.startup.HighwayHelper.mongo.template.UserTemplate;
import com.startup.HighwayHelper.request.UserSignUpRequest;

@Service
public class UserDomainService {

	@Autowired
	UserTemplate userTemplate;

	public UserDBObject saveUser(UserSignUpRequest signUpRequest) throws HHDomainException {

		try {

			return userTemplate.save(transformToUserDBObject(signUpRequest));

		} catch (HHDomainException e) {

			throw e;
		}
	}
	
	public UserDBObject findByUserName(String userName) throws HHDomainException {

		try {

			return userTemplate.findByUsername(userName);

		} catch (HHDomainException e) {

			throw e;
		}
	}

	private UserDBObject transformToUserDBObject(UserSignUpRequest signUpRequest) {

		UserDBObject userDBObj = new UserDBObject();
		userDBObj.setAddress(signUpRequest.getAddress());
		userDBObj.setDateOfBirth(signUpRequest.getDateOfBirth());
		userDBObj.setFirstName(signUpRequest.getFirstName());
		userDBObj.setGender(signUpRequest.getGender());
		userDBObj.setLastName(signUpRequest.getLastName());
		userDBObj.setMobileNumber(signUpRequest.getMobileNumber());
		userDBObj.setPassword(signUpRequest.getPassword());
		userDBObj.setRoles(signUpRequest.getRoles());
		userDBObj.setUserName(signUpRequest.getUserName());

		return userDBObj;

	}
}
