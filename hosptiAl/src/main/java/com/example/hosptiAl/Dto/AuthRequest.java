package com.example.hosptiAl.Dto;

import com.example.hosptiAl.Model.User.Role;

public class AuthRequest {

    private String username ;
    private String password;
    private Role role;
    
	public String getUsername() {
		return username;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
    
	public AuthRequest() {}
	
	
    
    
}
