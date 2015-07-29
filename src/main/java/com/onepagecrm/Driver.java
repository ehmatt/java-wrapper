package com.onepagecrm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import com.onepagecrm.models.User;
import com.onepagecrm.net.ApiClient;

public class Driver {

	private static final Logger LOG = Logger.getLogger(Driver.class.getName());
	
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		InputStream input = null;
				
		try {
			input = new FileInputStream("config.properties");
	 
			// Load the properties file
			prop.load(input);
	 
		} catch (IOException e) {
			LOG.severe("Error loading the config.properties file");
			LOG.severe(e.toString());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOG.severe("Error closing the config.properties file");
					LOG.severe(e.toString());
				}
			}
		}

		User loggedInUser = User.login(
				prop.getProperty("username"), 
				prop.getProperty("password"));
		
		loggedInUser.actionStream();
		loggedInUser.contacts();
	}
}
