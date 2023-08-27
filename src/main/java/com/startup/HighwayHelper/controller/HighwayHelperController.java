package com.startup.HighwayHelper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HighwayHelperController {

	@GetMapping(path = "/ping")
	public String ping() {
		return "pong";
	}
}
