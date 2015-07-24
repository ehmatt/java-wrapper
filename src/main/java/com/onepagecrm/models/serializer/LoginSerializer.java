package com.onepagecrm.models.serializer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.onepagecrm.models.User;


public class LoginSerializer extends BaseSerializer {
	
	
	public static List<User> parseLogin(String response) {
		
		List<User> users = new ArrayList<>();
		
		JSONObject responseObject = new JSONObject(response);
		JSONObject dataObject = responseObject.getJSONObject("data");
		
		JSONObject outsideUserObject = dataObject.getJSONObject(USER_TAG);
		// Log.d(TAG, "outsideUserObject=" + outsideUserObject);

		JSONObject userObject = outsideUserObject.getJSONObject(USER_TAG);
		
		User loggedInUser = UserSerializer.fromJson(userObject);

		loggedInUser.setAuthKey(dataObject.getString(AUTH_KEY_TAG));
//		String accountType = dataObject.getString(ACCOUNT_TYPE_TAG);
		
		users.add(loggedInUser);
		return users;

		// -----------------------------------------------------------------
		// TODO : Need to parse Call Results here
		// -----------------------------------------------------------------
//		ArrayList<CallResult> callResults = new ArrayList<>();
//		int index = 0;
//
//		try {
//			JSONObject callResultsObject = dataObject.getJSONObject("call_results");
//			// Log.d(TAG, "callResultsObject=" + callResultsObject);
//
//			Iterator<String> iterator = callResultsObject.keys();
//			while (iterator.hasNext()) {
//				String key = iterator.next();
//				try {
//					Object value = callResultsObject.get(key);
//					callResults.add(new CallResult(index, key, value.toString()));
//				} catch (JSONException e) {
//					// Log.e(TAG, "Failed to parse all values in
//					// call_results object");
//				}
//				index++;
//			}
//		} catch (JSONException e) {
//			// Log.e(TAG, "No call_results JSON object in response", e);
//
//			// TODO : remove this fabricated call results list
//			callResults.add(new CallResult(0, "interested", "Interested"));
//			callResults.add(new CallResult(1, "not_interested", "Not interested"));
//			callResults.add(new CallResult(2, "left_message", "Left message"));
//			callResults.add(new CallResult(3, "no_answer", "No answer"));
//			callResults.add(new CallResult(4, "other", "Other"));
//		}
		// -----------------------------------------------------------------

	}
	
}
