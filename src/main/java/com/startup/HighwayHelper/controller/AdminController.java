package com.startup.HighwayHelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.startup.HighwayHelper.exception.HHDomainException;
import com.startup.HighwayHelper.exception.UserSignUpException;
import com.startup.HighwayHelper.mongo.service.BusinessDomainService;
import com.startup.HighwayHelper.request.OnBoardBusinessRequest;
import com.startup.HighwayHelper.response.OnBoardBusinessResponse;
import com.startup.HighwayHelper.utils.HighwayHelperConstants;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/***
 * Responsible to onboard business etc..
 *
 */
@RestController
@Slf4j
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
public class AdminController {

	@Autowired
	BusinessDomainService businessDomainService;


	@PostMapping(path = "/onBoardBusiness", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public OnBoardBusinessResponse onboardBusiness(@RequestBody @Valid OnBoardBusinessRequest onBoardBusinessRequest) throws UserSignUpException, HHDomainException {

		log.debug("Received a valid request for Business onboard with name: " + onBoardBusinessRequest.getName());

		try {

			businessDomainService.saveBusiness(onBoardBusinessRequest);

			log.debug("Restraunt saved successfully, Name :" + onBoardBusinessRequest);

		} catch (HHDomainException e) {

			throw new HHDomainException(e.getCode(), e.getMessage());
		}
		return new OnBoardBusinessResponse(HighwayHelperConstants.HH_RESTRAUNT_ONBOARD_SUCCESSFUL_CODE, HighwayHelperConstants.HH_RESTRAUNT_ONBOARD_SUCCESSFUL_MESSAGE);

	}



}
