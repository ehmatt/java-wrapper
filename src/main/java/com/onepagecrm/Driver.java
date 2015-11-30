package com.onepagecrm;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.User;
import com.onepagecrm.net.request.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Driver {

    private static final Logger LOG = Logger.getLogger(Driver.class.getName());

    public static void main(String[] args) throws OnePageException {
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

        Request.SERVER = Request.DEV_SERVER;

        User loggedInUser = User.login(
                prop.getProperty("username"),
                prop.getProperty("password"));

        LOG.info("Logged in User : " + loggedInUser);

//        LOG.info("User's Team : " + loggedInUser.getAccount().team);
//        LOG.info("User's Statuses : " + loggedInUser.getAccount().statuses);
//        LOG.info("User's Lead Sources : " + loggedInUser.getAccount().leadSources);
//        LOG.info("User's Custom Fields : " + loggedInUser.getAccount().customFields);
//        LOG.info("User's Call Results : " + loggedInUser.getAccount().callResults);

        ContactList stream = loggedInUser.actionStream();
        Contact contact = stream.get(0);

        LOG.info(contact.toString());
    }
}
