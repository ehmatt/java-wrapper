package com.onepagecrm.models.serializer;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

		ArrayList<Contact> contacts = new ArrayList<>();
		
		try {
			JSONObject responseObject = new JSONObject(responseBody);
			JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
			JSONArray contactsArray = dataObject.getJSONArray(CONTACTS_TAG);

			for (int i = 0; i < contactsArray.length(); i++) {
				JSONObject contactObject = contactsArray.getJSONObject(i);
				contacts.add(fromJson(contactObject.getJSONObject(CONTACT_TAG)));
			}

		} catch (JSONException e) {
			LOG.severe("Error parsing contacs JSON array");
			LOG.severe(e.toString());
		}
		return new ContactList(contacts);
	}

	public static Contact fromJson(JSONObject contactObject) {

		String id = contactObject.getString(ID_TAG);
		String ownerId = contactObject.getString(OWNER_ID_TAG);
		String firstName = contactObject.getString(FIRST_NAME_TAG);
		String lastName = contactObject.getString(LAST_NAME_TAG);

		JSONArray phonesArray = contactObject.getJSONArray(PHONES_TAG);
		ArrayList<Phone> phones = new ArrayList<>();

		for (int j = 0; j < phonesArray.length(); j++) {
			JSONObject phoneObject = phonesArray.getJSONObject(j);
			String type = phoneObject.getString(TYPE_TAG);
			String value = phoneObject.getString(VALUE_TAG);
			phones.add(new Phone(type, value));
		}

		String companyName = contactObject.getString(COMPANY_NAME_TAG);
		boolean starred = contactObject.getBoolean(STARRED_TAG);

		return new Contact()
				.setId(id).setOwnerId(ownerId)
				.setFirstName(firstName)
				.setLastName(lastName)
				.setPhones(phones)
				.setCompanyName(companyName)
				.setStarred(starred);
	}
}