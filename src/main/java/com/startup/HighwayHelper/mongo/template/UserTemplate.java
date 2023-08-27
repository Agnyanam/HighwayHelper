package com.startup.HighwayHelper.mongo.template;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.startup.HighwayHelper.exception.HHDomainException;
import com.startup.HighwayHelper.mongo.model.UserDBObject;
import com.startup.HighwayHelper.utils.HighwayHelperConstants;

@Component
public class UserTemplate extends AbstractMongoTemplate {

	public UserDBObject save(UserDBObject userObject) throws HHDomainException {
		UserDBObject savedObject = null;
		try {
			savedObject = mongoOps().save(userObject);
		} catch (Exception e) {

			if (e instanceof DuplicateKeyException) {

				throw new HHDomainException(HighwayHelperConstants.USERNAME_ALREADY_EXISTS_CODE,
						HighwayHelperConstants.USERNAME_ALREADY_EXISTS_MESSAGE);
			}

			throw new HHDomainException(HighwayHelperConstants.INTERNAL_ERROR_CODE,
					HighwayHelperConstants.INTERNAL_ERROR_MESSAGE);
		}
		return savedObject;

	}
	
	public UserDBObject findByUsername(String username) throws HHDomainException {
		UserDBObject userDbObject = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("userName").is(username));
			userDbObject = mongoOps().findOne(query,UserDBObject.class);
			
		} catch (Exception e) {

			throw new HHDomainException(HighwayHelperConstants.INTERNAL_ERROR_CODE,
					HighwayHelperConstants.INTERNAL_ERROR_MESSAGE);
		}
		
		if(userDbObject == null) {
			throw new HHDomainException(HighwayHelperConstants.USERNAME_NOT_FOUND_CODE , HighwayHelperConstants.USERNAME_NOT_FOUND_MESSAGE);
		}
		return userDbObject;

	}

}
