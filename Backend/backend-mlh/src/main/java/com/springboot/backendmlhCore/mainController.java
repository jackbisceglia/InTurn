package com.springboot.backendmlhCore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class mainController {
	
	@Autowired
	@Qualifier("MainService")
	private mainServiceInterface MainService;
	
	@RequestMapping(value = "/test", produces = "application/json", method = RequestMethod.GET)
	public int testAPI() {
		return MainService.testAPI();
	}
}
