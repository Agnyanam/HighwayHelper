package com.startup.HighwayHelper.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "Business")
public class BusinessDBObject {

	@Id
	private String id;
	
	//Type of Business:  Restraunt, Theme park etc..
	private String type;
	
	private String name;
	
	private Address address;
	
}


