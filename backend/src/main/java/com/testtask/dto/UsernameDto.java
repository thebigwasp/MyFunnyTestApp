package com.testtask.dto;

import javax.validation.constraints.NotEmpty;

public class UsernameDto {
	
	@NotEmpty
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
