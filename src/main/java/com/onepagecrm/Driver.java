package com.onepagecrm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.onepagecrm.models.User;
import com.onepagecrm.net.ApiClient;

public class Driver {

	public static void main(String[] args) {
		
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
			input = new FileInputStream("config.properties");
	 
			// Load the properties file
			prop.load(input);
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		User loggedInUser = User.login(
				prop.getProperty("username"), 
				prop.getProperty("password"));
		
		ApiClient client = new ApiClient(true);
		
		client.getActionStream(loggedInUser, "");
		client.getContacts(loggedInUser, "");
	}
}
