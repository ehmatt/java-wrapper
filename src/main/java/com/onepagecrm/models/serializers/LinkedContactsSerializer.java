package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.LinkedContact;
import com.onepagecrm.models.LinkedContactList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 15/12/2016.
 */
public class LinkedContactsSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(LinkedContactsSerializer.class.getName());

    public static LinkedContactList fromString(String responseBody) throws OnePageException {
        LinkedContactList linkedContacts = new LinkedContactList();
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
        return linkedContacts;
    }

    public static LinkedContactList fromJsonObject(JSONObject linkedContactsObject) {
        LinkedContactList linkedContacts = new LinkedContactList();

        try {

            if (linkedContactsObject.has(LINKED_CONTACTS_TAG)) {
                // Contacts.
                JSONArray linkedContactsArray = linkedContactsObject.getJSONArray(LINKED_CONTACTS_TAG);
                ContactList contacts = ContactListSerializer.fromJsonArray(linkedContactsArray);
                linkedContacts.setContactMap(contacts.getList());

                // LinkedContacts (links).
                List<LinkedContact> links = new ArrayList<>();
                for (int i = 0; i < linkedContactsArray.length(); i++) {
                    JSONObject linkedContactObject = linkedContactsArray.optJSONObject(i);
                    LinkedContact linkedContact = LinkedContactSerializer.fromJsonObject(linkedContactObject);
                    links.add(linkedContact);
                }
                linkedContacts.setLinks(links);
            }

        } catch (JSONException e) {
            LOG.severe("Error parsing Linked Contacts object");
            LOG.severe(e.toString());
        }

        return linkedContacts;
    }
}
