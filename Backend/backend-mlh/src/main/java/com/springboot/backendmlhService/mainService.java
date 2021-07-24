package com.springboot.backendmlhService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springboot.backendmlhDao.mainDaoInterface;

@Service("MainService")
public class mainService implements mainServiceInterface{
	
	@Autowired
	@Qualifier("MainDao")
	private mainDaoInterface MainDao;
	
	public String addUser(String email) {
		return MainDao.addUser(email);
	}
	
	public List<Map<String, Object>> getUsers() {
		return MainDao.getUsers();
	}
	
	public String deleteUser(String email) {
		return MainDao.deleteUser(email);
	}
	
	public String addMRP(String company, String link, String role, String location) {
		return MainDao.addMRP(company, link, role, location);
	}
	
	public List<Map<String, Object>> getMRP() {
		return MainDao.getMRP();
	}
	
	public String deleteMRP() {
		return MainDao.deleteMRP();
	}
}
