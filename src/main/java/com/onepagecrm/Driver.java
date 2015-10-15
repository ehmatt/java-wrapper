package com.onepagecrm;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.*;
import com.onepagecrm.net.request.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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

        Request.SERVER = Request.APP_SERVER;

        User loggedInUser = User.login(
                prop.getProperty("username"),
                prop.getProperty("password"));

        LOG.info("Logged in User : " + loggedInUser);

        LOG.info("User's Team : " + loggedInUser.getAccount().team);
        LOG.info("User's Statuses : " + loggedInUser.getAccount().statuses);
        LOG.info("User's Lead Sources : " + loggedInUser.getAccount().leadSources);
        LOG.info("User's Custom Fields : " + loggedInUser.getAccount().customFields);
        LOG.info("User's Call Results : " + loggedInUser.getAccount().callResults);
//        LOG.info("Countries List : " + Countries.list());

        ContactList stream = loggedInUser.actionStream();
//        LOG.info("Stream Contacts : " + stream);

//        ContactList contacts = loggedInUser.contacts();
//        LOG.info("Contacts : " + contacts);

        Contact contact = stream.get(0);
//        LOG.info("Contact : " + contact);
//
        if (contact.isValid()) {

//            contact.setFirstName("Java");
//            contact.setLastName("Wrapper");
//            contact.setCompanyName("OnePageCRM");

            Address address = new Address();
            address.setAddress("Unit 5, Business Innovation Center, NUIG");
            address.setCity("Galway");
            address.setState("Connaught");
            address.setZipCode("HJ12WE3");
            address.setCountryCode("IE");
            contact.setAddress(address);

            List<Phone> phones = new ArrayList<>();
            Phone phone = new Phone();
            phone.setType("Fax");
            phone.setValue("0858187423");
            phones.add(phone);
            contact.setPhones(phones);

            List<Email> emails = new ArrayList<>();
            Email email = new Email();
            email.setType("Work");
            email.setValue("example@domain.com");
            emails.add(email);
            contact.setEmails(emails);

            List<Url> urls = new ArrayList<>();
            Url url = new Url();
            url.setType("Google+");
            url.setValue("www.blog.com");
            urls.add(url);
            contact.setUrls(urls);

//            List<CustomField> customFields = contact.getCustomFields();
//            LOG.info("Contact's Custom Fields : " + customFields);

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
//            newCall = newCall.save(contact);
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
