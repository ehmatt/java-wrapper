package com.onepagecrm.api;

import com.onepagecrm.api.models.User;
import com.onepagecrm.api.net.ApiClient;


public class Driver {

	public static void main(String[] args) {

		User loggedInUser = OnePageCRM.login("cillian.college@gmail.com", "qwerty");
		
		ApiClient client = new ApiClient(loggedInUser.isValid());
		
		client.getActionStream(loggedInUser, "");
		client.getContacts(loggedInUser, "");
	}
}
