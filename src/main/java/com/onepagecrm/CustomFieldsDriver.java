package com.onepagecrm;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.CustomField;
import com.onepagecrm.models.User;
import com.onepagecrm.net.request.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class CustomFieldsDriver {

    private static final Logger LOG = Logger.getLogger(CustomFieldsDriver.class.getName());

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
        LOG.info("User's Custom Fields : " + loggedInUser.getAccount().customFields);

        CustomField singleLineText = new CustomField();
        singleLineText.setName("TestSingleLine");
        singleLineText.setType(CustomField.TYPE_SINGLE_LINE_TEXT);
        singleLineText.save();

        CustomField multiLineText = new CustomField();
        multiLineText.setName("TestMultiLine");
        multiLineText.setType(CustomField.TYPE_MULTI_LINE_TEXT);
        multiLineText.save();

        CustomField anniversary = new CustomField();
        anniversary.setName("TestAnniversary");
        anniversary.setType(CustomField.TYPE_ANNIVERSARY);
        singleLineText.setReminderDays(2);
        anniversary.save();

        CustomField date = new CustomField();
        date.setName("TestDate");
        date.setType(CustomField.TYPE_DATE);
        date.save();

        CustomField number = new CustomField();
        number.setName("TestNumber");
        number.setType(CustomField.TYPE_NUMBER);
        number.save();

        CustomField dropdown = new CustomField();
        dropdown.setName("TestDropdown");
        dropdown.setType(CustomField.TYPE_DROPDOWN);
        List<String> dropdownChoices = new ArrayList<>();
        dropdownChoices.add("First");
        dropdownChoices.add("Second");
        dropdownChoices.add("Third");
        dropdown.setChoices(dropdownChoices);
        dropdown.save();

        CustomField multipleChoice = new CustomField();
        multipleChoice.setName("TestMultipleChoice");
        multipleChoice.setType(CustomField.TYPE_MULTIPLE_CHOICE);
        List<String> multipleChoiceChoices = new ArrayList<>();
        multipleChoiceChoices.add("First");
        multipleChoiceChoices.add("Second");
        multipleChoiceChoices.add("Third");
        multipleChoice.setChoices(multipleChoiceChoices);
        multipleChoice.save();
    }
}
