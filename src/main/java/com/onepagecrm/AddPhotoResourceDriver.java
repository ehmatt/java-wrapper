package com.onepagecrm;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.User;
import com.onepagecrm.models.internal.Utilities;
import com.onepagecrm.net.request.Request;

import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

public class AddPhotoResourceDriver {

    private static final Logger LOG = Logger.getLogger(AddPhotoResourceDriver.class.getName());

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

        ContactList stream = loggedInUser.actionStream();
        Contact contact = stream.get(0);

        String imagePath = "src/test/res/cillian.jpg";

        String resource = Utilities.getResourceContents(imagePath);
        LOG.info("RAW : " + resource);

        File imageFile = new File(imagePath);

        LOG.info("Base64 encoded String : " + Utilities.encodeImage(imagePath));

//        try {
//            // Reading Image file from file system.
//            FileInputStream imageInFile = new FileInputStream(imageFile);
//            byte[] imageData = new byte[(int) imageFile.length()];
//            imageInFile.read(imageData);
//
//            // Converting Image byte array to Base64 encoded String.
//            String imageDataString = Utilities.encodeImage(imageData);
//            LOG.info("Base64 encoded String : " + imageDataString);
//
//            contact.addPhoto(imageDataString);
//            imageInFile.close();
//
//            // ******** BELOW COMMENTED AS WE DON'T WANT TO ADD EXTRA FILES TO REPO ********** //
////            // Converting a Base64 String into Image byte array
////            byte[] imageByteArray = Utilities.decodeImage(imageDataString);
////
////            // Write a image byte array into file system
////            FileOutputStream imageOutFile = new FileOutputStream("src/test/res/cillian-output.jpg");
////
////            // Write the image date (array of bytes) to a file.
////            imageOutFile.write(imageByteArray);
////
////            imageOutFile.close();
//
//            LOG.info("Image Successfully Manipulated!");
//
//        } catch (FileNotFoundException e) {
//            LOG.severe("Image not found\n" + e);
//        } catch (IOException e) {
//            LOG.severe("Exception while reading the Image\n" + e);
//        }
    }
}
