package com.onepagecrm.samples;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Attachment;
import com.onepagecrm.models.User;
import com.onepagecrm.net.request.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class EnumsDriver {

    private static final Logger LOG = Logger.getLogger(EnumsDriver.class.getName());

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

        Attachment.Provider provider = Attachment.Provider.fromString("test");

        switch (provider) {
            case AMAZON: {
                LOG.info("AMAZON : " + provider.toString());
                break;
            }
            case DRIVE: {
                LOG.info("DRIVE : " + provider.toString());
                break;
            }
            case DROPBOX: {
                LOG.info("DROPBOX : " + provider.toString());
                break;
            }
            case EVERNOTE: {
                LOG.info("EVERNOTE : " + provider.toString());
                break;
            }
            case OTHER: {
                LOG.info("OTHER : " + provider.toString());
                break;
            }
        }

    }
}
