package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.ContactList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 15/12/2016.
 */
public class LinkedContactsSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(LinkedContactsSerializer.class.getName());

    public static ContactList fromString(String responseBody) throws APIException {
        ContactList contactList = new ContactList();
        try {
            String parsedResponse = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(parsedResponse);
            return fromJsonObject(responseObject);

        } catch (JSONException e) {
            LOG.severe("Error parsing Company from JSON.");
            LOG.severe(e.toString());
        }
        return contactList;
    }

    public static ContactList fromJsonObject(JSONObject linkedContactsObject) {
        ContactList contactList = new ContactList();
        try {

            if (linkedContactsObject.has(LINKED_CONTACTS_TAG)) {
                JSONArray contactsObject = linkedContactsObject.getJSONArray(LINKED_CONTACTS_TAG);
                return ContactListSerializer.fromJsonArray(contactsObject);
            }

        } catch (JSONException e) {
            LOG.severe("Error parsing Linked Contacts object");
            LOG.severe(e.toString());
            return contactList;
        }

        return contactList;
    }
}
