package com.springboot.backendmlhDao;

import java.util.List;
import java.util.Map;

public interface mainDaoInterface {
	
	public String addUser(String email);
	
	public List<Map<String, Object>> getUsers();
	
	public String deleteUser(String email);
	
	public String addMRP(String company, String link, String role, String location);
	
	public List<Map<String, Object>> getMRP();
	
	public String deleteMRP();
}
