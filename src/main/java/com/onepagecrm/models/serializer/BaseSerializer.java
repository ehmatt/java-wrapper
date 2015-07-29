package com.onepagecrm.models.serializer;

import org.json.JSONException;
import org.json.JSONObject;

public class BaseSerializer {

	// LOGIN TAGS
	protected static final String DATA_TAG = "data";
	protected static final String USER_ID_TAG = "user_id";
	protected static final String AUTH_KEY_TAG = "auth_key";
	protected static final String ACCOUNT_TYPE_TAG = "account_type";

	// LOGIN RESPONSE / USER TAGS
	protected static final String USER_TAG = "user";
	protected static final String FIRST_NAME_TAG = "first_name";
	protected static final String LAST_NAME_TAG = "last_name";
	protected static final String EMAIL_TAG = "email";
	protected static final String COMPANY_NAME_TAG = "company_name";
	protected static final String PHOTO_URL_TAG = "photo_url";
	protected static final String BCC_EMAIL_TAG = "bcc_email";

	// CONTACT TAGS
	protected static final String CONTACTS_TAG = "contacts";
	protected static final String CONTACT_TAG = "contact";
	protected static final String ID_TAG = "id";
	protected static final String OWNER_ID_TAG = "owner_id";
	protected static final String PHONES_TAG = "phones";
	protected static final String TYPE_TAG = "type";
	protected static final String VALUE_TAG = "value";
	protected static final String STARRED_TAG = "starred";
	
    // 201 RESPONSE TAGS
    private static final String STATUS_TAG = "status";
    private static final String MESSAGE_TAG = "message";

	public static boolean createResourceFromString(String responseBody) {
		boolean createdSuccessfully = false;
		try {
			JSONObject responseObject = new JSONObject(responseBody);
			int status = responseObject.getInt(STATUS_TAG);
			String message = responseObject.getString(MESSAGE_TAG);

			if (status == 0 && message.equalsIgnoreCase("Created")) {
				createdSuccessfully = true;
			}

		} catch (JSONException e) {
			
		}
		return createdSuccessfully;
	}
}
