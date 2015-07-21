package com.onepagecrm.models.builder;

import com.onepagecrm.models.User;


public class UserBuilder {
	
	private User user;
	
	public UserBuilder() {
		user = new User();
	}

	public User build() {
		return new User();
	}
}