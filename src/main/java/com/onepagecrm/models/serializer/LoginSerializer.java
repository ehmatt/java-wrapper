package com.onepagecrm.models.serializer;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Account;
import com.onepagecrm.models.Tag;
import com.onepagecrm.models.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.logging.Logger;

public class LoginSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(LoginSerializer.class.getName());

    public static User fromString(String responseBody) throws OnePageException {
        Account.loginResponse = responseBody;
        String parsedResponse;
        OnePageException exception;

        try {
            parsedResponse = (String) BaseSerializer.fromString(responseBody);
            Account.loggedInUser = UserSerializer.fromString(parsedResponse);
            addTagsToAccount(responseBody);
            return Account.loggedInUser;

        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;

        }
    }

    public static String toJsonObject(String username, String password) {
        JSONObject loginObject = new JSONObject();
        try {
            loginObject.put(LOGIN_TAG, username);
            loginObject.put(PASSWORD_TAG, password);
        } catch (JSONException e) {
            LOG.severe("Error creating User object");
            LOG.severe(e.toString());
        }
        return loginObject.toString();
    }

    private static void addTagsToAccount(String responseBody) {
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            if (responseObject.has(TAGS_TAG)) {
                addTags(responseObject.getJSONObject(TAGS_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing tags array");
            LOG.severe(e.toString());
        }
    }

    private static void addTags(JSONObject tagsObject) throws JSONException {
        List<Tag> tags = TagSerializer.fromJsonArray(
                tagsObject.getJSONArray(TAGS_TAG)
        );
        List<Tag> systemTags = TagSerializer.fromJsonArray(
                tagsObject.getJSONArray(SYSTEM_TAGS_TAG)
        );
        // Add system tags to list of tags
        for (Tag systemTag : systemTags) tags.add(systemTag);
        Account.loggedInUser.getAccount().setTags(tags);
    }
}
