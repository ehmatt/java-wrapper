package com.onepagecrm.models.serializer;

import com.onepagecrm.models.Email;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EmailSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(EmailSerializer.class.getName());

    public static ArrayList<Email> fromJsonArray(JSONArray emailsArray) {
        ArrayList<Email> emails = new ArrayList<>();
        for (int j = 0; j < emailsArray.length(); j++) {
            JSONObject emailObject;
            try {
                emailObject = emailsArray.getJSONObject(j);
                emails.add(fromJsonObject(emailObject));
            } catch (JSONException e) {
                LOG.severe("Error parsing email array");
                LOG.severe(e.toString());
            }
        }
        return emails;
    }

    public static Email fromJsonObject(JSONObject emailObject) {
        Email email = new Email();
        try {
            String type = emailObject.getString(TYPE_TAG);
            String value = emailObject.getString(VALUE_TAG);
            return email
                    .setType(type)
                    .setValue(value);
        } catch (JSONException e) {
            LOG.severe("Error parsing email object");
            LOG.severe(e.toString());
        }
        return email;
    }

    public static String toJsonObject(Email email) {
        JSONObject emailObject = new JSONObject();
        addJsonStringValue(email.getType(), emailObject, TYPE_TAG);
        addJsonStringValue(email.getValue(), emailObject, VALUE_TAG);
        return emailObject.toString();
    }

    public static String toJsonArray(List<Email> emails) {
        JSONArray emailsArray = new JSONArray();
        if (emails != null && !emails.isEmpty()) {
            for (int i = 0; i < emails.size(); i++) {
                emailsArray.put(toJsonObject(emails.get(i)));
            }
        }
        return emailsArray.toString();
    }
}
