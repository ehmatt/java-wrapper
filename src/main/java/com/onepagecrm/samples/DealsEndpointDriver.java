package com.onepagecrm.samples;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.Deal;
import com.onepagecrm.models.DealList;
import com.onepagecrm.models.User;
import com.onepagecrm.models.serializer.DateSerializer;
import com.onepagecrm.net.request.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by Cillian Myles (cillian@onepagecrm.com) on 11/24/15.
 */
public class DealsEndpointDriver {

    private static final Logger LOG = Logger.getLogger(DealsEndpointDriver.class.getName());

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
        List<User> team = loggedInUser.account.team;
        ContactList stream = loggedInUser.actionStream();

        // Create a deal with me as Owner.
        Deal newDeal = new Deal()
                .setOwnerId(team.get(0).getId()) // Owner = Me (when created).
                .setContactId(stream.get(0).getId())
                .setAmount(27.99d)
                .setText("Shouldn't be seen")
                .setName("Wrapper Deal")
                .setExpectedCloseDate(DateSerializer.fromFormattedString("2014-01-01"));

        Deal newDealResult = newDeal.save();

        // Update a deal to have different owner than me.
        DealList deals;
        LOG.info("Deals : " + (deals = loggedInUser.pipeline()));

        Deal first = deals.get(0);
        first.setText("Should be seen!!!");
        first.setOwnerId(team.get(1).getId()); // Owner = Second Person (after update).
        first.setAmount(99.99d);
        Deal firstUpdateResult = first.save();

        if (newDealResult.getOwnerId().equalsIgnoreCase(team.get(0).getId())
                && firstUpdateResult.getOwnerId().equalsIgnoreCase(team.get(1).getId())) {
            if (newDealResult.getId().equalsIgnoreCase(firstUpdateResult.getId())) {
                newDealResult.delete();
                LOG.info("Success");
            }
        }
    }
}
