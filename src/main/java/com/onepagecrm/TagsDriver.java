package com.onepagecrm;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.Tag;
import com.onepagecrm.models.User;
import com.onepagecrm.models.internal.TagsComparator;
import com.onepagecrm.net.request.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class TagsDriver {

    private static final Logger LOG = Logger.getLogger(TagsDriver.class.getName());

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

        List<Tag> accountTags = copyList(loggedInUser.getAccount().getTags());
        LOG.info("accountTags *0 : " + copyListNames(accountTags));

        if (contact.isValid()) {

            List<Tag> contactsTags = contact.getTags();
            LOG.info("contactsTags : " + copyListNames(contactsTags));

            Tag iOsTag = accountTags.get(0);
            accountTags.remove(0);
            LOG.info("accountTags : " + copyListNames(accountTags));

            accountTags.add(iOsTag);
            LOG.info("accountTags : " + copyListNames(accountTags));

            Collections.sort(accountTags, new TagsComparator());
            LOG.info("accountTags : " + copyListNames(accountTags));
        }

        for (int i = 0; i < accountTags.size(); i++) {
            LOG.info("[" + i + "] : " + accountTags.get(i).delete());
        }

        Tag androidTagTest = new Tag().setName("AndroidTagTest");
        LOG.info("AndroidTagTest : " + androidTagTest.save());

        Tag iOSTagTest = new Tag().setName("iOSTagTest");
        LOG.info("iOSTagTest : " + iOSTagTest.save());

        Tag vip = new Tag().setName("VIP");
        LOG.info("VIP : " + vip.save());

        List<Tag> tags = Tag.list();
        LOG.info("Tags.list() : " + tags);
    }

    private static List<Tag> copyList(List<Tag> tags) {
        List<Tag> copy = new ArrayList<>();
        if (tags != null && !tags.isEmpty()) {
            for (int i = 0; i < tags.size(); i++) {
                copy.add(new Tag(tags.get(i)));
            }
        }
        return copy;
    }

    private static List<String> copyListNames(List<Tag> tags) {
        List<String> copy = new ArrayList<>();
        if (tags != null && !tags.isEmpty()) {
            for (int i = 0; i < tags.size(); i++) {
                copy.add(new Tag(tags.get(i)).getName());
            }
        }
        return copy;
    }
}