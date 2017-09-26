package com.onepagecrm.samples;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Account;
import com.onepagecrm.models.CompanyList;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.Countries;
import com.onepagecrm.models.DealList;
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

        OnePageCRM.setServer(Request.DEV_SERVER);

        User loggedInUser = User.login(
                prop.getProperty("username"),
                prop.getProperty("password"));

        LOG.info("Logged in User : " + loggedInUser);

        LOG.info("Team : " + Account.team);
        LOG.info("Settings : " + Account.settings);
        LOG.info("Statuses : " + loggedInUser.getAccount().statuses);
        LOG.info("Lead Sources : " + loggedInUser.getAccount().leadSources);
        LOG.info("Deal Stages : " + Account.settings.getDealStages());
        LOG.info("Custom Fields : " + loggedInUser.getAccount().customFields);
        LOG.info("Company Fields : " + loggedInUser.getAccount().companyFields);
        LOG.info("Deal Fields : " + loggedInUser.getAccount().dealFields);
        LOG.info("Predefined Actions : " + loggedInUser.getAccount().predefinedActions);
        LOG.info("Call Results : " + loggedInUser.getAccount().callResults);
        LOG.info("Filters : " + loggedInUser.getAccount().filters);
        LOG.info("Contacts counts : " + loggedInUser.getAccount().contactsCount);
        LOG.info("Stream count : " + loggedInUser.getAccount().streamCount);
        LOG.info("Contact Titles : " + loggedInUser.getAccount().contactTitles);
        LOG.info("Account Rights : " + loggedInUser.getAccountRights());

        ContactList stream = loggedInUser.actionStream();
        ContactList contacts = loggedInUser.contacts();
        DealList pipeline = loggedInUser.pipeline();
        CompanyList companies = loggedInUser.companies();
        Countries countries = Countries.list();
    }
}
