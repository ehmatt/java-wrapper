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

        LOG.info("Logged in User : " + loggedInUser);

        LOG.info("User's Team : " + loggedInUser.account.team);
        LOG.info("User's Statuses : " + loggedInUser.account.statuses);
        LOG.info("User's Lead Sources : " + loggedInUser.account.leadSources);

        ContactList stream = loggedInUser.actionStream();
        LOG.info("Contacts : " + stream);

//        ContactList contacts = loggedInUser.contacts();
//        LOG.info("Contacts : " + contacts);

        Contact contact = stream.get(0);
        LOG.info("Contact : " + contact);

        if (contact.isValid()) {

            contact.setFirstName("Java");
            contact.setLastName("Wrapper");
            contact.setCompanyName("OnePageCRM");

            Address address = new Address();
            address.setAddress("Unit 5, Business Innovation Center, NUIG");
            address.setCity("Galway");
            address.setState("Connaught");
            address.setZipCode("HJ12WE3");
            address.setCountryCode("IE");
            contact.setAddress(address);

            List<CustomField> customFields = contact.getCustomFields();
            LOG.info("Custom Fields : " + customFields);

//            CustomField singleLineText = customFields.get(0);
//            customFields.setAddress("Unit 5, Business Innovation Center, NUIG");
//            customFields.setCity("Galway");
//            customFields.setState("Connaught");
//            customFields.setZipCode("HJ12WE3");
//            customFields.setCountryCode("IE");
//            contact.setAddress(customFields);

            contact = contact.update();
            LOG.info("Updated Contact : " + contact);

//            List<Action> actions = contact.getActions();
//            LOG.info("Actions : " + actions);
//
//            Action nextAction = contact.getNextAction();
//            LOG.info("NextAction : " + nextAction);
//
//            Contact newContact = new Contact()
//                    .setLastName("Arabacus")
//                    .setCompanyName("Myles Inc.")
//                    .setFirstName("Martin");
//
//            newContact = newContact.save();
//            LOG.info("CONTACT SAVED : " + newContact);
//
//            Call newCall = new Call()
//                    .setCallResult(new CallResult()
//                            .setId("other")
//                            .setText("ABACUS"));
//
//            newCall = newCall.save(newContact);
//            LOG.info("CALL SAVED : " + newCall);
//
//            LOG.info("Custom Fields : " + loggedInUser.getAccount().customFields);
//            LOG.info("Tags : " + loggedInUser.getAccount().tags);
//            LOG.info("Phones : " + contact.getPhones());
//            LOG.info("Emails : " + contact.getEmails());
//            LOG.info("Urls : " + contact.getUrls());
//
//            LOG.info("Image saved : " + contact.addPhoto());
        }
    }
}
