package com.startup.HighwayHelper.response;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonRootName(value = "YathraSevakErrorResponse")
public class TTErrorResponse {
	
	String errorCode;
	String errorMessage;
}
