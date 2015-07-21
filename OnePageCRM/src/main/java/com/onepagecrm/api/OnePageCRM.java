package com.onepagecrm.api;

import com.onepagecrm.api.models.User;
import com.onepagecrm.api.net.ApiClient;


public final class OnePageCRM {

    private OnePageCRM() {

    }

    public static User login(String username, String password) {
    	ApiClient client = new ApiClient(false);
    	return client.postLogin(username, password);
    }
}
