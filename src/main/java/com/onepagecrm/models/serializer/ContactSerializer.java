package com.onepagecrm.models.serializer;

import com.onepagecrm.models.Action;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.Phone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ContactSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ContactSerializer.class.getName());

    public static Contact fromJsonObject(JSONObject contactsElementObject) {
        Contact contact = new Contact();
        try {
            JSONObject contactObject = contactsElementObject.getJSONObject(CONTACT_TAG);

            String id = contactObject.getString(ID_TAG);
            String companyName = contactObject.getString(COMPANY_NAME_TAG);
            String firstName = contactObject.getString(FIRST_NAME_TAG);
            String lastName = contactObject.getString(LAST_NAME_TAG);
            String ownerId = contactObject.getString(OWNER_ID_TAG);

            JSONArray phonesArray = contactObject.getJSONArray(PHONES_TAG);
            ArrayList<Phone> phones = PhoneSerializer.fromJsonArray(phonesArray);

            boolean starred = contactObject.getBoolean(STARRED_TAG);

            if (contactsElementObject.has(NEXT_ACTIONS_TAG)) {
                JSONArray actionsArray = contactsElementObject.getJSONArray(NEXT_ACTIONS_TAG);
                ArrayList<Action> actions = ActionSerializer.fromJsonArray(actionsArray);
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
                    .setPhones(phones)
                    .setCompanyName(companyName)
                    .setStarred(starred);

        } catch (JSONException e) {
            LOG.severe("Error parsing contact object");
            LOG.severe(e.toString());
            return new Contact();
        }
    }

    public static String toJsonObject(Contact contact) {
        JSONObject userObject = new JSONObject();
        addJsonValue(contact.getType(), userObject, TYPE_TAG);
        addJsonValue(contact.getLastName(), userObject, LAST_NAME_TAG);
        addJsonValue(contact.getFirstName(), userObject, FIRST_NAME_TAG);
        addJsonValue(contact.getCompanyName(), userObject, COMPANY_NAME_TAG);
        addJsonValue(contact.getCompanyId(), userObject, COMPANY_ID_TAG);
        addJsonValue(contact.getJobTitle(), userObject, JOB_TITLE_TAG);
        addJsonValue(contact.getStatusId(), userObject, STATUS_ID_TAG);
        addJsonValue(contact.getStatus(), userObject, STATUS_TAG);

//        addJsonValue(contact.getTags(), userObject, TAGS_TAG);
//        addJsonValue(contact.isStarred(), userObject, STARRED_TAG);

        addJsonValue(contact.getOwnerId(), userObject, OWNER_ID_TAG);

//        addJsonValue(contact.getAddressLines(), userObject, ADDRESS_LIST_TAG);

        addJsonValue(contact.getBackground(), userObject, BACKGROUND_TAG);
        addJsonValue(contact.getLeadSourceId(), userObject, LEAD_SOURCE_ID_TAG);

        addJsonValue(PhoneSerializer.toJsonArray(contact.getPhones()), userObject, PHONES_TAG);
//        addJsonValue(contact.getEmails(), userObject, EMAILS_TAG);
//        addJsonValue(contact.getUrls(), userObject, URLS_TAG);
//        addJsonValue(contact.getCustomFields(), userObject, CUSTOM_FIELDS_TAG);

//        LOG.info("CONTACT INFO : " + userObject.toString());

        return userObject.toString();
    }
}
