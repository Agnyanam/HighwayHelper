package com.startup.HighwayHelper.mongo.model;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "User")
public class UserDBObject {

	@Id
	private String id;
	
	private String userName;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private BigInteger mobileNumber;
	
	private String address;
	
	private String gender;
	
	private String dateOfBirth;
	
	private List<String> roles;
}


