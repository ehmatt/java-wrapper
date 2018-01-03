package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.DealList;
import com.onepagecrm.models.StartupData;
import com.onepagecrm.models.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * Created by Cillian Myles on 03/01/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */
public class StartupDataSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(StartupDataSerializer.class.getSimpleName());

    private final static StartupData DEFAULT = new StartupData(null, null);

//    public static StartupData fromResponse(Response response) throws APIException {
//        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
//        return fromJsonObject(dataObject);
//    }

    public static StartupData fromString(String response) throws OnePageException {
        try {
            String dataString = (String) BaseSerializer.fromString(response);
            JSONObject dataObject = new JSONObject(dataString);
            return fromJsonObject(dataObject);

        } catch (JSONException e) {
            LOG.severe("Error parsing startup data JSON.");
            LOG.severe(e.toString());
            e.printStackTrace();
            return DEFAULT;
        }
    }

    public static StartupData fromJsonObject(JSONObject dataObject) throws OnePageException {
        if (dataObject == null) {
            return DEFAULT;
        }

        User user = LoginSerializer.getLoggedInUser(dataObject.toString());
        ContactList stream = null;
        ContactList contacts = null;
        DealList deals = null;
        boolean fullResponse = false;

        if (dataObject.has(ACTION_STREAM_DATA_TAG)) {
            fullResponse = true;
            stream = ContactListSerializer.fromJsonObject(dataObject.optJSONObject(ACTION_STREAM_DATA_TAG));
        }
        if (dataObject.has(CONTACT_DATA_TAG)) {
            fullResponse = true;
            contacts = ContactListSerializer.fromJsonObject(dataObject.optJSONObject(CONTACT_DATA_TAG));
        }
        if (dataObject.has(DEAL_DATA_TAG)) {
            fullResponse = true;
            deals = DealListSerializer.fromJsonObject(dataObject.optJSONObject(DEAL_DATA_TAG));
        }

        return !fullResponse ?
                new StartupData(null, user) :
                new StartupData(null, user, stream, contacts, deals);  // TODO: don't send null !?
    }
}
