package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.*;
import com.onepagecrm.models.internal.ContactsCount;
import com.onepagecrm.models.internal.PredefinedAction;
import com.onepagecrm.models.internal.PredefinedActionList;
import com.onepagecrm.models.internal.Settings;
import com.onepagecrm.models.internal.StreamCount;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static com.onepagecrm.models.Account.loggedInUser;

@SuppressWarnings("WeakerAccess")
public class LoginSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(LoginSerializer.class.getName());

    public static User fromString(String responseBody) throws OnePageException {
        return getLoggedInUser(responseBody);
    }

    public static StartupObject fromString(String responseBody, boolean fullResponse) throws OnePageException {
        User user = getLoggedInUser(responseBody);
        ContactList actionStream = null;
        ContactList contacts = null;
        DealList deals = null;
        if (fullResponse) {
            String parsedResponse = (String) BaseSerializer.fromString(responseBody);
            try {
                JSONObject responseObject = new JSONObject(parsedResponse);
                if (responseObject.has(ACTION_STREAM_DATA_TAG)) {
                    actionStream = ContactListSerializer.fromJsonObject(responseObject.getJSONObject(ACTION_STREAM_DATA_TAG));
                }
                if (responseObject.has(CONTACT_DATA_TAG)) {
                    contacts = ContactListSerializer.fromJsonObject(responseObject.getJSONObject(CONTACT_DATA_TAG));
                }
                if(responseObject.has(DEAL_DATA_TAG)) {
                    deals = DealListSerializer.fromJsonObject(responseObject.getJSONObject(DEAL_DATA_TAG));
                }
            } catch (JSONException e) {
                LOG.severe("Error parsing Login object");
                LOG.severe(e.toString());
            }
        }
        return new StartupObject(user, actionStream, contacts, deals, fullResponse);
    }

    public static void updateLoginOnlyResources(String responseBody) {
        addFiltersToAccount(responseBody);
        addSettingsToAccount(responseBody);
        addPredefinedActionsToAccount(responseBody);
        addContactTitlesToAccount(responseBody);
    }

    public static void updateDynamicResources(String responseBody) {
        addLeadSourcesToAccount(responseBody);
        addStatusesToAccount(responseBody);
        addTagsToAccount(responseBody);
        addContactsCountToAccount(responseBody);
        addStreamCountToAccount(responseBody);
        updateTeamCounters();
    }

    private static void addSettingsToAccount(String responseBody) {
        try {
            JSONObject responseObject = new JSONObject(responseBody);
            JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
            if (dataObject.has(BOOTSTRAP_TAG)) dataObject = dataObject.getJSONObject(BOOTSTRAP_TAG);
            addSettings(dataObject);
        } catch (Exception e) {
            LOG.severe("Error parsing Settings array");
            LOG.severe(e.toString());
        }
    }

    private static void addSettings(JSONObject dataObject) {
        Settings settings = SettingsSerializer.fromJsonObject(dataObject);
        loggedInUser.getAccount().setSettings(settings);
    }

    public static String toJsonObject(String username, String password, boolean fullResponse) {
        JSONObject loginObject = new JSONObject();
        try {
            loginObject.put(LOGIN_TAG, username);
            loginObject.put(PASSWORD_TAG, password);
            loginObject.put(FULL_RESPONSE_TAG, fullResponse);
        } catch (JSONException e) {
            LOG.severe("Error creating JSONObject from login values");
            LOG.severe(e.toString());
        }
        return loginObject.toString();
    }

    private static User getLoggedInUser(String responseBody) throws OnePageException {
        String parsedResponse;
        OnePageException exception;

        try {
            parsedResponse = (String) BaseSerializer.fromString(responseBody);
            loggedInUser = UserSerializer.fromString(parsedResponse);
            updateLoginOnlyResources(responseBody);
            updateDynamicResources(responseBody);
        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;
        }

        return loggedInUser;
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
        loggedInUser.getAccount().setTags(tags);
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
        loggedInUser.getAccount().setStatuses(statuses);
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
        loggedInUser.getAccount().setLeadSources(leadSources);
    }

    private static void addFiltersToAccount(String responseBody) {
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
            if (dataObject.has(BOOTSTRAP_TAG)) dataObject = dataObject.getJSONObject(BOOTSTRAP_TAG);
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
        loggedInUser.getAccount().setFilters(filters);
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
        loggedInUser.getAccount().setContactsCount(contactsCounts);
    }

    private static void addStreamCountToAccount(String responseBody) {
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            if (responseObject.has(TEAM_STREAM_TAG)) {
                addStreamCount(responseObject.getJSONObject(TEAM_STREAM_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing StreamCount object");
            LOG.severe(e.toString());
        }
    }

    private static void addStreamCount(JSONObject streamCountObject) throws JSONException {
        StreamCount streamCount = StreamCountSerializer.fromJsonObject(streamCountObject);
        loggedInUser.getAccount().setStreamCount(streamCount);
    }

    private static void addPredefinedActionsToAccount(String responseBody) {
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
            if (dataObject.has(BOOTSTRAP_TAG)) dataObject = dataObject.getJSONObject(BOOTSTRAP_TAG);
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
        loggedInUser.getAccount().setPredefinedActions(new PredefinedActionList(predefinedAction));
    }

    private static void addContactTitlesToAccount(String responseBody) {
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
            if (dataObject.has(BOOTSTRAP_TAG)) dataObject = dataObject.getJSONObject(BOOTSTRAP_TAG);
            if (dataObject.has(CONTACT_TITLES_TAG)) {
                addContactTitles(dataObject.getJSONArray(CONTACT_TITLES_TAG));
                Account.settings.setContactTitleEnabled(true);
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing contact titles array");
            LOG.severe(e.toString());
        }
    }

    private static void addContactTitles(JSONArray contactTitlesArray) throws JSONException {
        String[] titlesArray = BaseSerializer.toArrayOfStrings(contactTitlesArray);
        loggedInUser.getAccount().setContactTitles(Arrays.asList(titlesArray));
    }

    @SuppressWarnings("AccessStaticViaInstance")
    private static void updateTeamCounters() {
        if (loggedInUser != null && loggedInUser.getAccount() != null && loggedInUser.getAccount().getTeam() != null) {
            final List<User> team = loggedInUser.getAccount().getTeam();
            final StreamCount streamCount = loggedInUser.getAccount().getStreamCount();
            final ContactsCount contactsCount = loggedInUser.getAccount().getContactsCount();
            final boolean streamOk = streamCount != null && streamCount.getCounts().get(Account.USER_ID) != null;
            final boolean contactsOk = contactsCount != null && contactsCount.getCounts().get(Account.USER_ID) != null;
            if (streamOk && contactsOk) {
                int totalAccountContacts = contactsCount.getCounts().get(Account.USER_ID).getTotalCount();
                for (User user : team) {
                    user.setAllCount(totalAccountContacts);
                    user.setStreamCount(
                            (streamCount.getCounts().get(user.getId()) == null) ? null :
                                    streamCount.getCounts().get(user.getId()).getCount());
                    user.setContactsCount(
                            (contactsCount.getCounts().get(user.getId()) == null) ? null :
                                    contactsCount.getCounts().get(user.getId()).getTotalCount());
                }
            }
        }
    }
}
