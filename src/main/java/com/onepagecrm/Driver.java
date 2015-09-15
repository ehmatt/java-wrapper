package com.onepagecrm;

import com.onepagecrm.exceptions.OnePageException;
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

    public static void main(String[] args) throws OnePageException{
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

        Request.SERVER = Request.APP_SERVER;

        User loggedInUser = User.login(
                prop.getProperty("username"),
                prop.getProperty("password"));

        ContactList stream = loggedInUser.actionStream();
        LOG.info("Contacts : " + stream);

        ContactList contacts = loggedInUser.contacts();
        LOG.info("Contacts : " + contacts);

        Contact contact = stream.get(0);
        LOG.info("Contact : " + contact);

        if (contact.isValid()) {

            List<Action> actions = contact.getActions();
            LOG.info("Actions : " + actions);

            Action nextAction = contact.getNextAction();
            LOG.info("NextAction : " + nextAction);

            Contact newContact = new Contact()
                    .setLastName("Arabacus")
                    .setCompanyName("Myles Inc.")
                    .setFirstName("Martin");

            newContact = newContact.save();
            LOG.info("CONTACT SAVED : " + newContact);

            Call newCall = new Call()
                    .setCallResult(new CallResult()
                            .setId("other")
                            .setText("ABACUS"));

            newCall = newCall.save(newContact);
            LOG.info("CALL SAVED : " + newCall);

            LOG.info("Custom Fields : " + loggedInUser.getAccount().customFields);

//            LOG.info("Image saved : " + contact.addPhoto());
        }
    }
}
