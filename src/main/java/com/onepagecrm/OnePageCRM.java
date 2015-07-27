package com.onepagecrm;

import com.onepagecrm.models.Account;
import com.onepagecrm.models.User;
import com.onepagecrm.net.ApiClient;


public final class OnePageCRM {
	
	public static Account account;

    private OnePageCRM() {

    }

    public static User login(String username, String password) {
    	ApiClient client = new ApiClient(false);
    	return client.postLogin(username, password);
    }
}
