package com.onepagecrm.models.serializer;

import org.json.JSONObject;

import com.onepagecrm.models.User;


public class UserSerializer {

	public User fromJSON(String userString) {
		
		return new User();
	}
	
	public JSONObject toJSON(User user) {
		
		return new JSONObject();
	}
}