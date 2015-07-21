package com.onepagecrm;

import com.onepagecrm.models.User;
import com.onepagecrm.net.ApiClient;


public final class OnePageCRM {

    private OnePageCRM() {

    }

    public static User login(String username, String password) {
    	ApiClient client = new ApiClient(false);
    	return client.postLogin(username, password);
    }
}
