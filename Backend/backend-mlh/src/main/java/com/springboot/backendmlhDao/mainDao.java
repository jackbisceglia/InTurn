package com.springboot.backendmlhDao;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("MainDao")
public class mainDao implements mainDaoInterface{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String addUser(String email) {
		String sql = "INSERT INTO subscribers (email_address) VALUES (?)";
		jdbcTemplate.update(sql, new Object[] {email}, new int[] {Types.VARCHAR});
		return "SUCCESS";
	}
	
	public List<Map<String, Object>> getUsers(){
		String sql = "SELECT * from subscribers";
		return jdbcTemplate.queryForList(sql);
	}
	
	public String deleteUser(String email) {
		String sql = "DELETE FROM subscribers WHERE email_address = ?";
		jdbcTemplate.update(sql, email);
		return "SUCCESS";
	}
	
	public String addMRP(String company, String link, String role, String location) {
		String sql = "INSERT INTO most_recent_posting (company, link, role, location) VALUES (?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {company, link, role, location}, new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
		return "SUCCESS";
	}
	
	public List<Map<String, Object>> getMRP() {
		String sql = "SELECT * from most_recent_posting";
		return jdbcTemplate.queryForList(sql);
	}
	
	public String deleteMRP() {
		String sql = "DELETE FROM most_recent_posting";
		jdbcTemplate.update(sql);
		return "SUCCESS";
	}
}
