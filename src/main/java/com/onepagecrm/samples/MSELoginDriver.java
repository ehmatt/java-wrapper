package com.onepagecrm.samples;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.StartupData;
import com.onepagecrm.models.internal.LoginData;
import com.onepagecrm.models.internal.Utilities;
import com.onepagecrm.net.API;
import com.onepagecrm.net.request.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by Cillian Myles on 02/01/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */
public class MSELoginDriver {

    private static final Logger LOG = Logger.getLogger(MSELoginDriver.class.getName());

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

        OnePageCRM.setDebug(true);
        OnePageCRM.setServer(Request.CUSTOM_URL_SERVER);
        OnePageCRM.setCustomUrl("http://auth.devpc.onepagecrm.eu/api/v3/");

        LoginData loginData = API.Auth.authenticate(
                prop.getProperty("username"),
                prop.getProperty("password"),
                true);

        LOG.info("LOGIN DATA: " + loginData);

        OnePageCRM.setCustomUrl(loginData.getEndpointUrl());

//        loginData.setFullResponse(false);
//        User loggedUser = API.Auth.simpleLogin(loginData);
//        User bootstrapUser = API.Auth.bootstrap();

        loginData.setPassword(null); // TODO: validate password incorrect here not failing!?
        loginData.setUsername(null);
        loginData.setFullResponse(true);
        StartupData startupData = API.Auth.startup(loginData);

        loginData.setFullResponse(true);
        StartupData startupDataOld = API.Auth.startup();

//        LOG.info(Utilities.repeatedString("*", 40));
//        LOG.info("USER *1*: " + loggedUser);
//        LOG.info("USER *2*: " + bootstrapUser);
//        LOG.info("USER equal: " + loggedUser.equals(bootstrapUser));

        LOG.info(Utilities.repeatedString("*", 40));
        LOG.info("STARTUP *1*: " + startupData);
        LOG.info("STARTUP *2*: " + startupDataOld);
        LOG.info("STARTUP equal: " + startupData.equals(startupDataOld));

        LOG.info(Utilities.repeatedString("*", 40));
    }
}
