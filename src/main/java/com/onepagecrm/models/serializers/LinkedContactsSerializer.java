package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Company;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.CustomField;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.onepagecrm.models.internal.Utilities.nullChecks;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/12/2016.
 */
public class LinkedContactsSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(LinkedContactsSerializer.class.getName());

    public static ContactList fromString(String responseBody) throws OnePageException {
        ContactList contactList = new ContactList();
        try {
            String parsedResponse = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(parsedResponse);
            return fromJsonObject(responseObject);

        } catch (ClassCastException e) {
            throw (OnePageException) BaseSerializer.fromString(responseBody);

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
