package com.onepagecrm;

import com.onepagecrm.models.*;
import com.onepagecrm.net.request.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

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

        Request.isProdApp = true;

        User loggedInUser = User.login(
                prop.getProperty("username"),
                prop.getProperty("password"));

        ContactList stream = loggedInUser.actionStream();
        LOG.info("Contacts : " + stream);

        ContactList contacts = loggedInUser.contacts();
        LOG.info("Contacts : " + contacts);

        Contact contact = stream.get(0);
        LOG.info("Contact : " + contact);

        List<Action> actions = contact.getActions();
        LOG.info("Actions : " + actions);

        Action nextAction = contact.getNextAction();
        LOG.info("NextAction : " + nextAction);

        Call newCall = new Call()
                .setCallResult(new CallResult()
                .setId("interested")
                .setText("JAVA"));
        LOG.info("CALL SAVED : " + newCall.save(contact));

        Contact newContact = new Contact()
                .setLastName("Myles")
                .setCompanyName("Myles Inc.")
                .setFirstName("Cillian");
        LOG.info("CONTACT SAVED : " + newContact.save());
    }
}
