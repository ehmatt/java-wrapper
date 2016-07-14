package com.onepagecrm.samples;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Action;
import com.onepagecrm.models.User;
import com.onepagecrm.net.request.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

public class ActionDateTimeDriver {

    private static final Logger LOG = Logger.getLogger(ActionDateTimeDriver.class.getName());

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

        OnePageCRM.setServer(Request.APP_SERVER);

        User loggedInUser = User.login(
                prop.getProperty("username"),
                prop.getProperty("password"));

        LOG.info("Logged in User : " + loggedInUser);

        new Action()
                .setText("This one will be added")
                .setStatus("date_time")
                .setDate(new Date())
                .setContactId("5774e13c00d4afe3fb314f58")
                .setAssigneeId(loggedInUser.getId())
                .save();

        new Action()
                .setText("Should throw exception")
                .setStatus("time")
                .setContactId("5774e13c00d4afe3fb314f58")
                .setAssigneeId(loggedInUser.getId())
                .save();
    }
}
