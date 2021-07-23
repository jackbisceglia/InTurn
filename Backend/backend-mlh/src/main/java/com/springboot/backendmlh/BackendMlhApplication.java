package com.springboot.backendmlh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.springboot")
public class BackendMlhApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendMlhApplication.class, args);
	}

}
