package com.onepagecrm.models.serializer;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.onepagecrm.models.Action;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.Phone;

public class ContactSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ContactSerializer.class.getName());

    /**
     * Parse response from contacts/action_stream request to construct Contact
     * object(s).
     *
     * @param responseBody
     * @return
     */
    public static ContactList fromString(String responseBody) {

	Contact.nextIntId = 1;
	ArrayList<Contact> contacts = new ArrayList<>();

	try {
	    JSONObject responseObject = new JSONObject(responseBody);
	    JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
	    JSONArray contactsArray = dataObject.getJSONArray(CONTACTS_TAG);

	    for (int i = 0; i < contactsArray.length(); i++) {
		JSONObject contactObject = contactsArray.getJSONObject(i);
//		contacts.add(fromJson(contactObject.getJSONObject(CONTACT_TAG)));
		contacts.add(fromJson(contactObject));
	    }

	} catch (JSONException e) {
	    LOG.severe("Error parsing contacts array from response body");
	    LOG.severe(e.toString());
	}
	return new ContactList(contacts);
    }

    public static Contact fromJson(JSONObject contactsElementObject) {
	
	Contact contact = new Contact();

	try {
	    JSONObject contactObject = contactsElementObject.getJSONObject(CONTACT_TAG);

	    String id = contactObject.getString(ID_TAG);
	    String companyName = contactObject.getString(COMPANY_NAME_TAG);
	    String firstName = contactObject.getString(FIRST_NAME_TAG);
	    String lastName = contactObject.getString(LAST_NAME_TAG);
	    String ownerId = contactObject.getString(OWNER_ID_TAG);

	    JSONArray phonesArray = contactObject.getJSONArray(PHONES_TAG);
	    ArrayList<Phone> phones = PhoneSerializer.fromJSON(phonesArray);

	    boolean starred = contactObject.getBoolean(STARRED_TAG);
	    
	    if (contactsElementObject.has(NEXT_ACTIONS_TAG)) {
		JSONArray actionsArray = contactsElementObject.getJSONArray(NEXT_ACTIONS_TAG);
		ArrayList<Action> actions = ActionSerializer.fromJSONArray(actionsArray);
		contact.setActions(actions);
	    }

	    if (contactsElementObject.has(NEXT_ACTION_TAG)) {
		JSONObject nextActionObject = contactsElementObject.getJSONObject(NEXT_ACTION_TAG);
		Action nextAction = ActionSerializer.fromJSONObject(nextActionObject);
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
}
