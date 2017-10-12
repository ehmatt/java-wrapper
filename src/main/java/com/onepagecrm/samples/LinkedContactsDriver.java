package com.onepagecrm.samples;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Company;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.LinkedContactList;
import com.onepagecrm.models.User;
import com.onepagecrm.net.request.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 12/10/2017.
 */
public class LinkedContactsDriver {

    private static final Logger LOG = Logger.getLogger(LinkedContactsDriver.class.getName());

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

        ContactList contacts = loggedInUser.searchContacts("OnePageCRM");
        Contact first = contacts.get(0);
        Company company = Company.byId(first.getCompanyId());
        LinkedContactList linkedContacts = company.getLinkedContacts();

        LOG.info("Found " + linkedContacts.getLinks().size() + " linked contacts!");
        LOG.info("Paginator: " + linkedContacts.getPaginator());

        for (int i = 0; i < linkedContacts.size(); i++) {
            LOG.info("Link[" + i + "]: " + linkedContacts.get(i));
            LOG.info("Contact[" + i + "]: " + linkedContacts.getContact(i));
            LOG.info("Company[" + i + "]: " + linkedContacts.getCompany(i));
        }
    }
}
