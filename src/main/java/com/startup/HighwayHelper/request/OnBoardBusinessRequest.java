package com.startup.HighwayHelper.request;

import com.startup.HighwayHelper.mongo.model.Address;

import lombok.Data;

@Data
public class OnBoardBusinessRequest {

	private String type;

	private String name;

	private Address address;
}
