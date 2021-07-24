package com.springboot.backendmlhController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backendmlhService.mainServiceInterface;


@RestController
public class mainController {
	
	@Autowired
	@Qualifier("MainService")
	private mainServiceInterface MainService;
	
	@RequestMapping(value = "/addUser", produces = "application/json", method = RequestMethod.POST)
	public String addUser(@RequestBody Map<String, Object> userData) {
		if (userData.containsKey("email_address")) {
			String email = userData.get("email_address").toString();
			return MainService.addUser(email);
		}
		else {
			return "ERROR";
		}
	}
	
	@RequestMapping(value = "/getUsers", produces = "application/json", method = RequestMethod.GET) 
	public List<Map<String, Object>> getUsers() {
		return MainService.getUsers();
	}
	
	@RequestMapping(value = "/deleteUser", produces = "application/json", method = RequestMethod.DELETE) 
	public String deleteUser(@RequestBody Map<String, Object> email_address) {
		if (email_address.containsKey("email_address")) {
			String email = email_address.get("email_address").toString();
			return MainService.deleteUser(email);
		}
		else {
			return "ERROR";
		}
	}
	
	@RequestMapping(value = "/addMRP", produces = "application/json", method = RequestMethod.POST)
	public String addMRP(@RequestBody Map<String, Object> posting) {
		if (posting.containsKey("company") && posting.containsKey("link") && posting.containsKey("role") && posting.containsKey("location")) {
			String company = posting.get("company").toString();
			String link = posting.get("link").toString();
			String role = posting.get("role").toString();
			String location = posting.get("location").toString();
			return MainService.addMRP(company, link, role, location);
		}
		else {
			return "ERROR";
		}
	}
	
	@RequestMapping(value = "/getMRP", produces = "application/json", method = RequestMethod.GET)
	public List<Map<String, Object>> getMRP() {
		return MainService.getMRP();
	}
	
	@RequestMapping(value = "/deleteMRP", produces = "application/json", method = RequestMethod.DELETE)
	public String deleteMRP() {
		return MainService.deleteMRP();
	}
}
