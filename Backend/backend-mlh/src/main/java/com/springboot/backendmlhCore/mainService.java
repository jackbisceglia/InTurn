package com.springboot.backendmlhCore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("MainService")
public class mainService implements mainServiceInterface{
	
	@Autowired
	@Qualifier("MainDao")
	private mainDaoInterface MainDao;
	
	public int testAPI() {
		return MainDao.testAPI();
	}
}
