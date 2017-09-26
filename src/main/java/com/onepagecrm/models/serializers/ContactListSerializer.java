package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.internal.Paginator;
import com.onepagecrm.net.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 18/09/2016.
 */
public class ContactListSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ContactListSerializer.class.getName());

    private static ContactList DEFAULT = new ContactList();

    public static ContactList fromResponse(Response response) throws APIException {
        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
        return fromJsonObject(dataObject);
    }

    // TODO: delete
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

    public static ContactList fromJsonObject(JSONObject dataObject) {
        if (dataObject == null) {
            return DEFAULT;
        }

        JSONArray contactsArray = dataObject.optJSONArray(CONTACTS_TAG);
        ContactList contacts = fromJsonArray(contactsArray);
        Paginator paginator = RequestMetadataSerializer.fromJsonObject(dataObject);
        contacts.setPaginator(paginator);
        return contacts;
    }

    public static ContactList fromJsonArray(JSONArray contactsArray) {
        if (contactsArray == null) {
            return DEFAULT;
        }

        List<Contact> contacts = new ArrayList<>();
        Contact.nextIntId = 1;

        for (int i = 0; i < contactsArray.length(); i++) {
            JSONObject contactObject = contactsArray.optJSONObject(i);
            contacts.add(ContactSerializer.fromJsonObject(contactObject));
        }

        return new ContactList(contacts);
    }

    public static JSONObject toJsonObject(ContactList contacts) {
        JSONObject contactsListObject = new JSONObject();
        if (contacts == null) return contactsListObject;

        JSONArray contactsArray = ContactSerializer.toJsonArray(contacts);
        addJsonArray(contactsArray, contactsListObject, CONTACTS_TAG);

        Paginator paginator = contacts.getPaginator();
        if (paginator != null) {
            addJsonIntegerValue(paginator.getTotalCount(), contactsListObject, TOTAL_COUNT_TAG);
            addJsonIntegerValue(paginator.getCurrentPage(), contactsListObject, PAGE_TAG);
            addJsonIntegerValue(paginator.getMaxPage(), contactsListObject, MAX_PAGE_TAG);
            addJsonIntegerValue(paginator.getPerPage(), contactsListObject, PER_PAGE_TAG);
        }

        return contactsListObject;
    }

    public static String toJsonString(ContactList contacts) {
        return toJsonObject(contacts).toString();
    }
}
