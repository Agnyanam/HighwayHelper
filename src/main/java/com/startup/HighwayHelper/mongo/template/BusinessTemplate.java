package com.startup.HighwayHelper.mongo.template;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import com.startup.HighwayHelper.exception.HHDomainException;
import com.startup.HighwayHelper.mongo.model.BusinessDBObject;
import com.startup.HighwayHelper.utils.HighwayHelperConstants;

@Component
public class BusinessTemplate extends AbstractMongoTemplate {

	public BusinessDBObject save(BusinessDBObject businessDBObject) throws HHDomainException {
		BusinessDBObject businessDBObj = null;
		try {
			businessDBObj = mongoOps().save(businessDBObject);
		} catch (Exception e) {

			if (e instanceof DuplicateKeyException) {

				throw new HHDomainException(HighwayHelperConstants.USERNAME_ALREADY_EXISTS_CODE,
						HighwayHelperConstants.USERNAME_ALREADY_EXISTS_MESSAGE);
			}

			throw new HHDomainException(HighwayHelperConstants.INTERNAL_ERROR_CODE,
					HighwayHelperConstants.INTERNAL_ERROR_MESSAGE);
		}
		return businessDBObj;

	}

}
