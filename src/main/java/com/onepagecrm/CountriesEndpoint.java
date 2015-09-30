package com.onepagecrm;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Countries;
import com.onepagecrm.models.User;
import com.onepagecrm.net.request.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class CountriesEndpoint {

    private static final Logger LOG = Logger.getLogger(CountriesEndpoint.class.getName());

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

        Request.SERVER = Request.ORION_SERVER;
        User loggedInUser = User.login(
                prop.getProperty("username"),
                prop.getProperty("password"));

//        Request.SERVER = Request.NETWORK_DEV_SERVER;
//        User loggedInUser = User.login("fkae@fasdfasdfasdf.com", "easy2remember");

        LOG.info("Logged in User : " + loggedInUser);

//        Request.format = ".xml";

        LOG.info("Countries : " + Countries.list());

    }
}
