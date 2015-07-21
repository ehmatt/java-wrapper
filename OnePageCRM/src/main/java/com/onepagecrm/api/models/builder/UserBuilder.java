package com.onepagecrm.api.models.builder;

import com.onepagecrm.api.models.User;


public class UserBuilder {
	
	private User user;
	
	public UserBuilder() {
		user = new User();
	}

	public User build() {
		return new User();
	}
}