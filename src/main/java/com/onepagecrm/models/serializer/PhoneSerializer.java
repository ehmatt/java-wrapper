package com.onepagecrm.models.serializer;

import com.onepagecrm.models.Phone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Logger;

public class PhoneSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(PhoneSerializer.class.getName());

    public static ArrayList<Phone> fromJsonArray(JSONArray phonesArray) {
        ArrayList<Phone> phones = new ArrayList<>();
        for (int j = 0; j < phonesArray.length(); j++) {
            JSONObject phoneObject;
            try {
                phoneObject = phonesArray.getJSONObject(j);
                phones.add(fromJsonObject(phoneObject));
            } catch (JSONException e) {
                LOG.severe("Error parsing phone array");
                LOG.severe(e.toString());
            }
        }
        return phones;
    }

    public static Phone fromJsonObject(JSONObject phoneObject) {
        Phone phone = new Phone();
        try {
            String type = phoneObject.getString(TYPE_TAG);
            String value = phoneObject.getString(VALUE_TAG);
            return phone
                    .setType(type)
                    .setNumber(value);
        } catch (JSONException e) {
            LOG.severe("Error parsing phone object");
            LOG.severe(e.toString());
        }
        return phone;
    }
}
