package com.onepagecrm.models.serializer;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.logging.Logger;

public class ContactSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ContactSerializer.class.getName());

    public static Contact fromString(String responseBody) throws OnePageException {
        Contact contact = new Contact();
        try {
            JSONObject responseObject = new JSONObject(responseBody);
            JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);

            contact = fromJsonObject(dataObject);

        } catch (JSONException e) {
            LOG.severe("Error parsing contact object from response body");
            LOG.severe(e.toString());
        }
        return contact;
    }

    public static Contact fromJsonObject(JSONObject contactsElementObject) {
        Contact contact = new Contact();
        try {
            JSONObject contactObject = contactsElementObject.getJSONObject(CONTACT_TAG);

            String id = contactObject.getString(ID_TAG);
            String companyName = contactObject.getString(COMPANY_NAME_TAG);
            String firstName = contactObject.getString(FIRST_NAME_TAG);
            String lastName = contactObject.getString(LAST_NAME_TAG);
            String ownerId = contactObject.getString(OWNER_ID_TAG);

            // Add Custom Fields.
            JSONArray customFieldsArray = contactObject.getJSONArray(CUSTOM_FIELDS_TAG);
            List<CustomField> customFields = CustomFieldSerializer.fromJsonArray(customFieldsArray);
            if (!customFields.isEmpty()) contact.setCustomFields(customFields);

            // Add Phones.
            JSONArray phonesArray = contactObject.getJSONArray(PHONES_TAG);
            List<Phone> phones = PhoneSerializer.fromJsonArray(phonesArray);
            if (!phones.isEmpty()) contact.setPhones(phones);

            // Add Emails.
            JSONArray emailsArray = contactObject.getJSONArray(EMAILS_TAG);
            List<Email> emails = EmailSerializer.fromJsonArray(emailsArray);
            if (!emails.isEmpty()) contact.setEmails(emails);

            // Add Websites.
            JSONArray urlsArray = contactObject.getJSONArray(URLS_TAG);
            List<Url> urls = UrlSerializer.fromJsonArray(urlsArray);
            if (!urls.isEmpty()) contact.setUrls(urls);

            // Add Address.
            JSONArray addressArray = contactObject.getJSONArray(ADDRESS_LIST_TAG);
            Address address = AddressSerializer.fromJsonArray(addressArray);
            contact.setAddress(address);

            boolean starred = contactObject.getBoolean(STARRED_TAG);

            if (contactsElementObject.has(NEXT_ACTIONS_TAG)) {
                JSONArray actionsArray = contactsElementObject.getJSONArray(NEXT_ACTIONS_TAG);
                List<Action> actions = ActionSerializer.fromJsonArray(actionsArray);
                contact.setActions(actions);
            }

            if (contactsElementObject.has(NEXT_ACTION_TAG)) {
                JSONObject nextActionObject = contactsElementObject.getJSONObject(NEXT_ACTION_TAG);
                Action nextAction = ActionSerializer.fromJsonObject(nextActionObject);
                contact.setNextAction(nextAction);
            }

            return contact
                    .setId(id)
                    .setOwnerId(ownerId)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setCompanyName(companyName)
                    .setStarred(starred);

        } catch (JSONException e) {
            LOG.severe("Error parsing Contact object");
            LOG.severe(e.toString());
            return new Contact();
        }
    }

    /**
     * Serialize Contact object to JSON.
     *
     * @param contact
     * @return
     */
    public static String toJsonObject(Contact contact) {

        JSONObject contactObject = new JSONObject();

        addJsonStringValue(contact.getId(), contactObject, ID_TAG);
        addJsonStringValue(contact.getType(), contactObject, TYPE_TAG);
        addJsonStringValue(contact.getLastName(), contactObject, LAST_NAME_TAG);
        addJsonStringValue(contact.getFirstName(), contactObject, FIRST_NAME_TAG);
        addJsonStringValue(contact.getCompanyName(), contactObject, COMPANY_NAME_TAG);
        addJsonStringValue(contact.getCompanyId(), contactObject, COMPANY_ID_TAG);
        addJsonStringValue(contact.getJobTitle(), contactObject, JOB_TITLE_TAG);
        addJsonStringValue(contact.getStatusId(), contactObject, STATUS_ID_TAG);
        addJsonStringValue(contact.getStatus(), contactObject, STATUS_TAG);

//        addJsonStringValue(contact.getTags(), userObject, TAGS_TAG);
//        addJsonStringValue(contact.isStarred(), userObject, STARRED_TAG);

        addJsonStringValue(contact.getOwnerId(), contactObject, OWNER_ID_TAG);

        try {
            JSONArray customFieldsArray = new JSONArray(CustomFieldSerializer.toJsonArray(contact.getCustomFields()));
            addJsonArray(customFieldsArray, contactObject, CUSTOM_FIELDS_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating CustomField array while constructing Contact object");
            LOG.severe(e.toString());
        }

        try {
            JSONArray addressArray = new JSONArray(AddressSerializer.toJsonArray(contact.getAddress()));
            addJsonArray(addressArray, contactObject, ADDRESS_LIST_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating Address array while constructing Contact object");
            LOG.severe(e.toString());
        }

        addJsonStringValue(contact.getBackground(), contactObject, BACKGROUND_TAG);
        addJsonStringValue(contact.getLeadSourceId(), contactObject, LEAD_SOURCE_ID_TAG);

        try {
            JSONArray phonesArray = new JSONArray(PhoneSerializer.toJsonArray(contact.getPhones()));
            addJsonArray(phonesArray, contactObject, PHONES_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating Phone array while constructing Contact object");
            LOG.severe(e.toString());
        }

        try {
            JSONArray emailsArray = new JSONArray(EmailSerializer.toJsonArray(contact.getEmails()));
            addJsonArray(emailsArray, contactObject, EMAILS_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating Email array while constructing Contact object");
            LOG.severe(e.toString());
        }

        try {
            JSONArray urlsArray = new JSONArray(UrlSerializer.toJsonArray(contact.getUrls()));
            addJsonArray(urlsArray, contactObject, URLS_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating Url array while constructing Contact object");
            LOG.severe(e.toString());
        }

//        addJsonStringValue(contact.getEmails(), userObject, EMAILS_TAG);
//        addJsonStringValue(contact.getUrls(), userObject, URLS_TAG);
//        addJsonStringValue(contact.getCustomFields(), userObject, CUSTOM_FIELDS_TAG);

        return contactObject.toString();
    }

    public static String toJsonArray(ContactList contacts) {
        JSONArray contactsArray = new JSONArray();
        if (contacts != null && !contacts.isEmpty()) {
            for (int i = 0; i < contacts.size(); i++) {
                try {
                    contactsArray.put(new JSONObject(toJsonObject(contacts.get(i))));
                } catch (JSONException e) {
                    LOG.severe("Error creating JSONArray out of ContactList");
                    LOG.severe(e.toString());
                }
            }
        }
        return contactsArray.toString();
    }
}
