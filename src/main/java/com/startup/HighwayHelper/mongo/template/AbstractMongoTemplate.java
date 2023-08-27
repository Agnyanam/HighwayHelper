package com.startup.HighwayHelper.mongo.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.WriteConcern;
import com.startup.HighwayHelper.mongo.config.MongoDBConfig;

public class AbstractMongoTemplate {

	@Autowired
	MongoDBConfig mongoDbConfig;

	private MongoOperations mongoOps = null;

	protected MongoOperations mongoOps() {

		try {

			if (mongoOps == null) {

				MongoTemplate template = mongoDbConfig.mongoTemplate(mongoDbConfig.mongoDbFactory(),
						mongoDbConfig.mappingMongoConverter(mongoDbConfig.mongoDbFactory(),
								mongoDbConfig.customConversions(), mongoDbConfig.mongoMappingContext(
										mongoDbConfig.customConversions(), mongoDbConfig.mongoManagedTypes())));
				template.setWriteConcern(WriteConcern.ACKNOWLEDGED);
				mongoOps = template;
			}
			return mongoOps;

		} catch (Exception e) {
			return null;
		}
	}
}
