package com.startup.HighwayHelper.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.startup.HighwayHelper.exception.HHDomainException;
import com.startup.HighwayHelper.mongo.model.BusinessDBObject;
import com.startup.HighwayHelper.mongo.template.BusinessTemplate;
import com.startup.HighwayHelper.request.OnBoardBusinessRequest;

@Service
public class BusinessDomainService {

	@Autowired
	BusinessTemplate businessTemplate;

	public BusinessDBObject saveBusiness(OnBoardBusinessRequest signUpRequest) throws HHDomainException {

		try {

			return businessTemplate.save(transformToBusinessDBObject(signUpRequest));

		} catch (HHDomainException e) {

			throw e;
		}
	}

	private BusinessDBObject transformToBusinessDBObject(OnBoardBusinessRequest onBoardBusinessReq) {

		BusinessDBObject businessObj = new BusinessDBObject();
		businessObj.setType(onBoardBusinessReq.getType());
		businessObj.setName(onBoardBusinessReq.getName());
		businessObj.setAddress(onBoardBusinessReq.getAddress());

		return businessObj;

	}
}
