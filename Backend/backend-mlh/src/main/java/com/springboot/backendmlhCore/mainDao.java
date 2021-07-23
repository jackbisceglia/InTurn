package com.springboot.backendmlhCore;

import org.springframework.stereotype.Component;

@Component("MainDao")
public class mainDao implements mainDaoInterface{
	public int testAPI() {
		return 1;
	}
}
