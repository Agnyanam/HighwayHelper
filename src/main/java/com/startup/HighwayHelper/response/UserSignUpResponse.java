package com.startup.HighwayHelper.response;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;

@JsonRootName("UserSignUpResponse")
@Data
@AllArgsConstructor
public class UserSignUpResponse {

	String code;
	String message;
}
