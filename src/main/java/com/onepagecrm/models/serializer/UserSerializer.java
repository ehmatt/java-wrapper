package com.onepagecrm.models.serializer;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.onepagecrm.models.CallResult;
import com.onepagecrm.models.User;


public class UserSerializer extends BaseSerializer {

	
	public static User fromString(String userString) {

		JSONObject responseObject = new JSONObject(userString);
		// Log.d(TAG, "responseObject=" + responseObject);

		JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
		// Log.d(TAG, "dataObject=" + dataObject);
		
		JSONObject outsideUserObject = dataObject.getJSONObject(USER_TAG);
		// Log.d(TAG, "outsideUserObject=" + outsideUserObject);

		JSONObject userObject = outsideUserObject.getJSONObject(USER_TAG);
		// Log.d(TAG, "userObject=" + userObject);

		return fromJson(userObject);
	}

	
	public static User fromJson(JSONObject userObject) {

		User user = new User();

		try {
			String userId = userObject.getString(USER_ID_TAG);
			String firstName = userObject.getString(FIRST_NAME_TAG);
			String lastName = userObject.getString(LAST_NAME_TAG);
			String email = userObject.getString(EMAIL_TAG);
			String companyName = userObject.getString(COMPANY_NAME_TAG);
			String photoUrl = userObject.getString(PHOTO_URL_TAG);
			String bccEmail = userObject.getString(BCC_EMAIL_TAG);

			user.setId(userId);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setCompany(companyName);
			user.setPhotoUrl(photoUrl);
			user.setBccEmail(bccEmail);
					
//					(userId, authKey, accountType, firstName, lastName, email, companyName, photoUrl, bccEmail,
//					callResults);
			// Log.d(TAG, "Parsed login details to User object successfully");

		} catch (JSONException e) {
			// Log.e(TAG, "Failed to parse login details to User object
			// successfully", e);
		}

		return user;
	}

	public JSONObject toJSON(User user) {

		return new JSONObject();
	}
}