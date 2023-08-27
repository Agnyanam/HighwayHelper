package com.startup.HighwayHelper.response;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;

@JsonRootName("OnBoardBusinessResponse")
@Data
@AllArgsConstructor
public class OnBoardBusinessResponse {

	String code;
	String message;
}
