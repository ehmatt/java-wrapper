package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.*;
import com.onepagecrm.models.internal.ContactsCount;
import com.onepagecrm.models.internal.PredefinedAction;
import com.onepagecrm.models.internal.PredefinedActionList;
import com.onepagecrm.models.internal.Settings;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.logging.Logger;

public class LoginSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(LoginSerializer.class.getName());

    public static User fromString(String responseBody) throws OnePageException {
        String parsedResponse;
        OnePageException exception;

        try {
            parsedResponse = (String) BaseSerializer.fromString(responseBody);
            Account.loggedInUser = UserSerializer.fromString(parsedResponse);
            addTagsToAccount(responseBody);
            addStatusesToAccount(responseBody);
            addLeadSourcesToAccount(responseBody);
            addFiltersToAccount(responseBody);
            addContactsCountToAccount(responseBody);
            addSettingsToAccount(responseBody);
            addPredefinedActionsToAccount(responseBody);
            return Account.loggedInUser;

        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;
        }
    }

    private static void addSettingsToAccount(String responseBody) {
        try {
            JSONObject responseObject = new JSONObject(responseBody);
            JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
            if (dataObject.has(SETTINGS_TAG)) {
                addSettings(dataObject.getJSONObject(SETTINGS_TAG));
            }
        } catch (Exception e) {
            LOG.severe("Error parsing Settings array");
            LOG.severe(e.toString());
        }
    }

    private static void addSettings(JSONObject settingsObject) {
        Settings settings = SettingsSerializer.fromJsonObject(settingsObject);
        Account.loggedInUser.getAccount().setSettings(settings);
    }

    public static String toJsonObject(String username, String password) {
        JSONObject loginObject = new JSONObject();
        try {
            loginObject.put(LOGIN_TAG, username);
            loginObject.put(PASSWORD_TAG, password);
        } catch (JSONException e) {
            LOG.severe("Error creating JSONObject from login values");
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
            LOG.severe("Error parsing Tags array");
            LOG.severe(e.toString());
        }
    }

    private static void addTags(JSONObject tagsObject) throws JSONException {
        List<Tag> tags = TagSerializer.fromJsonArray(
                tagsObject.getJSONArray(TAGS_TAG)
        );
        Account.loggedInUser.getAccount().setTags(tags);
    }

    private static void addStatusesToAccount(String responseBody) {
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            if (responseObject.has(STATUSES_TAG)) {
                addStatuses(responseObject.getJSONArray(STATUSES_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing Status array");
            LOG.severe(e.toString());
        }
    }

    private static void addStatuses(JSONArray statusesArray) throws JSONException {
        List<Status> statuses = StatusSerializer.fromJsonArray(statusesArray);
        Account.loggedInUser.getAccount().setStatuses(statuses);
    }

    private static void addLeadSourcesToAccount(String responseBody) {
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            if (responseObject.has(LEAD_SOURCES_TAG)) {
                addLeadSources(responseObject.getJSONArray(LEAD_SOURCES_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing LeadSource array");
            LOG.severe(e.toString());
        }
    }

    private static void addLeadSources(JSONArray leadSourceArray) throws JSONException {
        List<LeadSource> leadSources = LeadSourceSerializer.fromJsonArray(leadSourceArray);
        Account.loggedInUser.getAccount().setLeadSources(leadSources);
    }

    private static void addFiltersToAccount(String responseBody) {
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
            if (dataObject.has(FILTERS_TAG)) {
                addFilters(dataObject.getJSONArray(FILTERS_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing Filters array");
            LOG.severe(e.toString());
        }
    }

    private static void addFilters(JSONArray filterArray) throws JSONException {
        List<Filter> filters = FilterSerializer.fromJsonArray(filterArray);
        Account.loggedInUser.getAccount().setFilters(filters);
    }

    private static void addContactsCountToAccount(String responseBody) {
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            if (responseObject.has(CONTACTS_COUNT_TAG)) {
                addContactsCount(responseObject.getJSONObject(CONTACTS_COUNT_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing ContactsCount object");
            LOG.severe(e.toString());
        }
    }

    private static void addContactsCount(JSONObject contactsCountObject) throws JSONException {
        ContactsCount contactsCounts = ContactsCountSerializer.fromJsonObject(contactsCountObject);
        Account.loggedInUser.getAccount().setContactsCount(contactsCounts);
    }

    private static void addPredefinedActionsToAccount(String responseBody) {
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
            if (dataObject.has(PREDEFINED_ACTIONS_TAG)) {
                addPredefinedActions(dataObject.getJSONArray(PREDEFINED_ACTIONS_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing PredefinedActions array");
            LOG.severe(e.toString());
        }
    }

    private static void addPredefinedActions(JSONArray predefinedActionsArray) throws JSONException {
        List<PredefinedAction> predefinedAction = PredefinedActionSerializer.fromJsonArray(predefinedActionsArray);
        Account.loggedInUser.getAccount().setPredefinedActions(new PredefinedActionList(predefinedAction));
    }
}
