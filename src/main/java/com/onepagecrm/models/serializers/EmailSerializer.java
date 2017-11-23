package com.onepagecrm.models.serializers;

import com.onepagecrm.models.Email;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 23/11/2017.
 */
public class EmailSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(EmailSerializer.class.getName());

    private static Email DEFAULT = new Email();

    public static Email fromJsonObject(JSONObject emailObject) {
        if (emailObject == null) {
            return DEFAULT;
        }
        if (emailObject.has(EMAIL_TAG)) {
            emailObject = emailObject.optJSONObject(EMAIL_TAG);
        }
        return new Email()
                .setType(emailObject.optString(TYPE_TAG))
                .setValue(emailObject.optString(VALUE_TAG));
    }

    public static List<Email> fromJsonArray(JSONArray emailsArray) {
        List<Email> emails = new ArrayList<>();
        if (emailsArray == null) return emails;
        for (int i = 0; i < emailsArray.length(); i++) {
            JSONObject emailObject = emailsArray.optJSONObject(i);
            emails.add(fromJsonObject(emailObject));
        }
        return emails;
    }

    public static JSONObject toJsonObject(Email email) {
        JSONObject emailObject = new JSONObject();
        if (email == null) return emailObject;
        addJsonStringValue(email.getType().toLowerCase(), emailObject, TYPE_TAG);
        addJsonStringValue(email.getValue(), emailObject, VALUE_TAG);
        return emailObject;
    }

    public static JSONArray toJsonArray(List<Email> emails) {
        JSONArray emailsArray = new JSONArray();
        if (emails == null) return emailsArray;
        for (Email email : emails) {
            emailsArray.put(toJsonObject(email));
        }
        return emailsArray;
    }

    public static String toJsonString(Email email) {
        return toJsonObject(email).toString();
    }

    public static String toJsonString(List<Email> emails) {
        return toJsonArray(emails).toString();
    }
}
