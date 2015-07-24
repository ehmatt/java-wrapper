package com.onepagecrm;

import java.util.List;

import com.onepagecrm.models.User;
import com.onepagecrm.net.ApiClient;


public final class OnePageCRM {
	
	private List<User> users;
	private User currentUser;

    private OnePageCRM() {

    }

    public static User login(String username, String password) {
    	ApiClient client = new ApiClient(false);
    	return client.postLogin(username, password);
    }
}
