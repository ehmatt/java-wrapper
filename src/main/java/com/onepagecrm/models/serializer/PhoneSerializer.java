package com.onepagecrm.models.serializer;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.onepagecrm.models.Phone;

public class PhoneSerializer extends BaseSerializer {
    
    private static final Logger LOG = Logger.getLogger(PhoneSerializer.class.getName());

    public static ArrayList<Phone> fromJSON(JSONArray phonesArray) {
	
	ArrayList<Phone> phones = new ArrayList<>();
	for (int j = 0; j < phonesArray.length(); j++) {
	    JSONObject phoneObject;
	    try {
		phoneObject = phonesArray.getJSONObject(j);
		String type = phoneObject.getString(TYPE_TAG);
		String value = phoneObject.getString(VALUE_TAG);
		phones.add(new Phone(type, value));
	    } catch (JSONException e) {
		LOG.severe("Error parsing phone array from contact object");
		LOG.severe(e.toString());
	    }
	}
	return phones;
    }
}
