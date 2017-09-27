package com.onepagecrm.samples;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.User;
import com.onepagecrm.models.internal.S3File;
import com.onepagecrm.models.internal.Utilities;
import com.onepagecrm.net.request.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class AttachmentsPh2Driver {

    private static final Logger LOG = Logger.getLogger(AttachmentsPh2Driver.class.getName());

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

        String contactId = "56fa81eb9007ba07fc000080";
        S3File s3File = S3File.form(contactId);

        LOG.info("S3 File : " + s3File);

        String endpoint = s3File.getUrl();
        OnePageCRM.setCustomUrl(endpoint);
        OnePageCRM.setServer(Request.CUSTOM_URL_SERVER);
        Request.format = "";

        String fileName = "cillian.jpg";
        String contentType = "image/jpeg";
        String filePath = "./src/test/res/image_encode/" + fileName;
        String fileContents = Utilities.getResourceContents(filePath);

        s3File.upload(contactId, filePath, fileName, contentType, fileContents);
    }
}
