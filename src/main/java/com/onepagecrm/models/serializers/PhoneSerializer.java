package com.onepagecrm.models.serializers;

import com.onepagecrm.models.Phone;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 23/11/2017.
 */
public class PhoneSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(PhoneSerializer.class.getName());

    private static Phone DEFAULT = new Phone();

    public static Phone fromJsonObject(JSONObject phoneObject) {
        if (phoneObject == null) {
            return DEFAULT;
        }
        if (phoneObject.has(PHONE_TAG)) {
            phoneObject = phoneObject.optJSONObject(PHONE_TAG);
        }
        return new Phone()
                .setType(phoneObject.optString(TYPE_TAG))
                .setValue(phoneObject.optString(VALUE_TAG));
    }

    public static List<Phone> fromJsonArray(JSONArray phonesArray) {
        List<Phone> phones = new ArrayList<>();
        if (phonesArray == null) return phones;
        for (int i = 0; i < phonesArray.length(); i++) {
            JSONObject phoneObject = phonesArray.optJSONObject(i);
            phones.add(fromJsonObject(phoneObject));
        }
        return phones;
    }

    public static JSONObject toJsonObject(Phone phone) {
        JSONObject phoneObject = new JSONObject();
        if (phone == null) return phoneObject;
        addJsonStringValue(phone.getType().toLowerCase(), phoneObject, TYPE_TAG);
        addJsonStringValue(phone.getValue(), phoneObject, VALUE_TAG);
        return phoneObject;
    }

    public static JSONArray toJsonArray(List<Phone> phones) {
        JSONArray phonesArray = new JSONArray();
        if (phones == null) return phonesArray;
        for (Phone phone : phones) {
            phonesArray.put(toJsonObject(phone));
        }
        return phonesArray;
    }

    public static String toJsonString(Phone phone) {
        return toJsonObject(phone).toString();
    }

    public static String toJsonString(List<Phone> phones) {
        return toJsonArray(phones).toString();
    }
}
