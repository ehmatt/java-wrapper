package com.onepagecrm.models.serializer;

import com.onepagecrm.models.Action;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.Phone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
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
//        Map<String, String> params = new HashMap<>();
        JSONObject params = new JSONObject();
        addJsonValue(contact.getType(), params, TYPE_TAG);
        addJsonValue(contact.getLastName(), params, LAST_NAME_TAG);
        addJsonValue(contact.getFirstName(), params, FIRST_NAME_TAG);
        addJsonValue(contact.getCompanyName(), params, COMPANY_NAME_TAG);
        addJsonValue(contact.getCompanyId(), params, COMPANY_ID_TAG);
        addJsonValue(contact.getJobTitle(), params, JOB_TITLE_TAG);
        addJsonValue(contact.getStatusId(), params, STATUS_ID_TAG);
        addJsonValue(contact.getStatus(), params, STATUS_TAG);

//        addJsonValue(contact.getTags(), params, TAGS_TAG);
//        addJsonValue(contact.isStarred(), params, STARRED_TAG);

        addJsonValue(contact.getOwnerId(), params, OWNER_ID_TAG);

//        addJsonValue(contact.getAddressLines(), params, ADDRESS_LIST_TAG);

        addJsonValue(contact.getBackground(), params, BACKGROUND_TAG);
        addJsonValue(contact.getLeadSourceId(), params, LEAD_SOURCE_ID_TAG);

//        addJsonValue(contact.getPhones(), params, PHONES_TAG);
//        addJsonValue(contact.getEmails(), params, EMAILS_TAG);
//        addJsonValue(contact.getUrls(), params, URLS_TAG);
//        addJsonValue(contact.getCustomFields(), params, CUSTOM_FIELDS_TAG);

        LOG.info("CONTACT INFO : " + params.toString());

        return params.toString();
    }

    private static void checkStringValueSet(String value, Map<String, String> params, String key) {
        if ((value != null) && (!value.equals(""))) {
            params.put(key, value);
        }
    }
}
