package com.testtask.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class MyFunnyAuthentication implements Authentication {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private boolean isAuthenticated;
	
	public MyFunnyAuthentication(String name, boolean isAuthenticated) {
		this.name = name;
		this.isAuthenticated = isAuthenticated;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return name;
	}

	@Override
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated = isAuthenticated;
	}
}
