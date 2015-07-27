package com.onepagecrm;

import com.onepagecrm.models.User;
import com.onepagecrm.net.ApiClient;


public class Driver {

	public static void main(String[] args) {

		User loggedInUser = User.login("cillian.college@gmail.com", "qwerty");
//		User loggedInUser = User.login("cillian@xap.ie", "qwerty");
//		User loggedInUser = OnePageCRM.login("cillian.college@gmail.com", "qwerty");
//		User loggedInUser = OnePageCRM.login("cillian@xap.ie", "qwerty");
		
		ApiClient client = new ApiClient(true);
		
		client.getActionStream(loggedInUser, "");
		client.getContacts(loggedInUser, "");
	}
}
