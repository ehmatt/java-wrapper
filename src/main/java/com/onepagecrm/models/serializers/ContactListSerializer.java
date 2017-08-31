package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.internal.Paginator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ContactListSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ContactListSerializer.class.getName());

    /**
     * Parse response from contacts/action_stream request to construct Contact
     * object(s).
     *
     * @param responseBody
     * @return
     */
    public static ContactList fromString(String responseBody) throws APIException {
        ContactList contacts = new ContactList();

        try {
            String dataString = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(dataString);
            JSONArray contactsArray = responseObject.getJSONArray(CONTACTS_TAG);
            contacts = fromJsonArray(contactsArray);
            Paginator paginator = RequestMetadataSerializer.fromJsonObject(responseObject);
            contacts.setPaginator(paginator);

        } catch (JSONException e) {
            LOG.severe("Error parsing Contacts array from response body");
            LOG.severe(e.toString());
        }

        return contacts;
    }

    /**
     * Parse response from contacts/action_stream request to construct Contact
     * object(s).
     *
     * @param contactsArray
     * @return
     */
    public static ContactList fromJsonArray(JSONArray contactsArray) {
        Contact.nextIntId = 1;
        ArrayList<Contact> contacts = new ArrayList<>();
        try {
            for (int i = 0; i < contactsArray.length(); i++) {
                JSONObject contactObject = contactsArray.getJSONObject(i);
                contacts.add(ContactSerializer.fromJsonObject(contactObject));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing Contacts array from response body");
            LOG.severe(e.toString());
        }
        return new ContactList(contacts);
    }

    public static String toJsonObject(ContactList contacts) {
        JSONObject contactsListObject = new JSONObject();
        try {
            JSONArray contactsArray = new JSONArray(ContactSerializer.toJsonArray(contacts));
            addJsonArray(contactsArray, contactsListObject, CONTACTS_TAG);
            Paginator paginator = contacts.getPaginator();
            if (paginator != null) {
                addJsonIntegerValue(paginator.getTotalCount(), contactsListObject, TOTAL_COUNT_TAG);
                addJsonIntegerValue(paginator.getCurrentPage(), contactsListObject, PAGE_TAG);
                addJsonIntegerValue(paginator.getMaxPage(), contactsListObject, MAX_PAGE_TAG);
                addJsonIntegerValue(paginator.getPerPage(), contactsListObject, PER_PAGE_TAG);
            }
        } catch (JSONException e) {
            LOG.severe("Error serializing Contacts array from ContactList object");
            LOG.severe(e.toString());
        }
        return contactsListObject.toString();
    }

    public static ContactList fromJsonObject(JSONObject contactsObject) {
        ContactList contacts = new ContactList();
        try {
            JSONArray contactsArray = contactsObject.getJSONArray(CONTACTS_TAG);
            contacts = fromJsonArray(contactsArray);
            Paginator paginator = RequestMetadataSerializer.fromJsonObject(contactsObject);
            contacts.setPaginator(paginator);

        } catch (JSONException e) {
            LOG.severe("Error parsing Contacts array from Contacts object");
            LOG.severe(e.toString());
        }
        return contacts;
    }
}
