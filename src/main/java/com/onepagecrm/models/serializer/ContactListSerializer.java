package com.onepagecrm.models.serializer;

import com.onepagecrm.exceptions.OnePageException;
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
    public static ContactList fromString(String responseBody) throws OnePageException {
        ContactList contacts = new ContactList();
        String parsedResponse;
        OnePageException exception;

        try {
            parsedResponse = (String) BaseSerializer.fromString(responseBody);
            JSONArray contactsArray = new JSONObject(parsedResponse).getJSONArray(CONTACTS_TAG);
            contacts = fromJsonArray(contactsArray);
            Paginator paginator = RequestMetadataSerializer.fromJsonObject(new JSONObject(parsedResponse));
            contacts.setPaginator(paginator);

        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;

        } catch (JSONException e) {
            LOG.severe("Error parsing contacts array from response body");
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
            LOG.severe("Error parsing contacts array from response body");
            LOG.severe(e.toString());
        }
        return new ContactList(contacts);
    }

    public static String toJsonObject(ContactList contacts) {
        JSONObject contactsObject = new JSONObject();
        try {
            JSONArray contactsArray = new JSONArray(ContactSerializer.toJsonArray(contacts));
            addJsonArray(contactsArray, contactsObject, CONTACTS_TAG);
            Paginator paginator = contacts.getPaginator();
            if (paginator != null) {
                addJsonIntegerValue(paginator.getTotalCount(), contactsObject, TOTAL_COUNT_TAG);
                addJsonIntegerValue(paginator.getCurrentPage(), contactsObject, PAGE_TAG);
                addJsonIntegerValue(paginator.getMaxPage(), contactsObject, MAX_PAGE_TAG);
                addJsonIntegerValue(paginator.getPerPage(), contactsObject, PER_PAGE_TAG);
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing contacts array from response body");
            LOG.severe(e.toString());
        }
        return contactsObject.toString();
    }
}
