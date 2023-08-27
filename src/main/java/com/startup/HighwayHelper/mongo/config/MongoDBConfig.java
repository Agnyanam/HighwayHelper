package com.startup.HighwayHelper.mongo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoDBConfig extends AbstractMongoClientConfiguration {

	@Value("${mongodb.url}")
	private String connectionStr;

	@Value("${mongodb.name}")
	private String dbName;

	@Override
	protected String getDatabaseName() {
		return dbName;
	}

	@Bean
	public MongoClient mongoClient() {

		ConnectionString connStr = new ConnectionString(connectionStr);
		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connStr).build();
		return MongoClients.create(settings);
	}

}
