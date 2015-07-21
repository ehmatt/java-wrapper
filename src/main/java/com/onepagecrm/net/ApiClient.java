package com.onepagecrm.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//import android.net.Uri;
//import android.text.TextUtils;
//import android.util.Log;
//
//import org.apache.commons.lang3.StringUtils;

import com.onepagecrm.models.Call;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.User;


public class ApiClient {

    private static final String TAG = ApiClient.class.getSimpleName();

    private static final String FORMAT = "json";
    private static final String BASE_URL = "https://app.onepagecrm.com";
    private static final String DEV_BASE_URL = "http://dev.onepagecrm.com";
    private static final String API_V3_BASE_URL = BASE_URL + "/api/v3";

    public static final String POST_LOGIN_URL = API_V3_BASE_URL + "/login." + FORMAT;
    public static final String GET_BOOTSTRAP_URL = API_V3_BASE_URL + "/bootstrap." + FORMAT;
    private static final String GET_CONTACTS_URL = API_V3_BASE_URL + "/contacts." + FORMAT;
    public static final String GET_ACTION_STREAM_URL = API_V3_BASE_URL + "/action_stream." + FORMAT;

    public static String POST_CALL_URL = API_V3_BASE_URL + "/calls." + FORMAT + "?contact_id=";

    private MakeRequest apiRequest;
    private ResponseHandler responseHandler;

    private boolean loggedIn = false;
    private User mUser;

    public ApiClient(boolean loggedIn) {
        this.loggedIn = loggedIn;
        apiRequest = new MakeRequest();
        responseHandler = new ResponseHandler();
    }

    /**
     * Encode request parameters.
     *
     * @param params
     * @return
     */
    public String encodeParams(HashMap<String, String> params) {
    	String[] entries = new String[] {null, null, null, null, null, null};
    	int i = 0;
        for (Map.Entry<String, String> param: params.entrySet()) {
            try {
				entries[i] = (String.format("%s=%s", 
						URLEncoder.encode(param.getKey(), "UTF-8"), 
						URLEncoder.encode(param.getValue(), "UTF-8")));
			} catch (UnsupportedEncodingException e) {
//				  Log.d(TAG, "Could not encode params to UTF-8");
//				e.printStackTrace();
			} finally {
	            i++;
			}
        }
        return Arrays.stream(entries).collect(Collectors.joining("&"));
    }

    /**
     * Makes API request [POST] 'login.format' as defined in API v3 documentation.
     *
     * Logs user in using credentials provided (email & password).
     * Items received in the response from this request are used subsequently for
     * authentication and authorisation on subsequent requests.
     *
     * @param email
     * @param password
     * @return
     */
    public User postLogin(String email, String password) {

        if (!loggedIn) {
//            Log.d(TAG, "Attempting to log in");

            HashMap<String, String> params = new HashMap<>();
            params.put("login", email);
            params.put("password", password);
            String loginParams = encodeParams(params);

            Authentication loginSignature = new Authentication("POST", POST_LOGIN_URL, "");
            Response response = apiRequest.postRequest(loginSignature, loginParams);

            if (response != null && response.getResponseCode() < 300) {

                mUser = responseHandler.parseLoginResponse(response.getResponseBody());
                if (mUser != null) {
                    loggedIn = true;
//                    Log.d(TAG, "Login attempt successful");
//                    Log.d(TAG, "Logged in as " + mUser.getFirstName() + " " + mUser.getLastName());
                    return mUser;
                } else {
//                    Log.e(TAG, "Login attempt unsuccessful");
                }
            }
        }
        return new User();
    }

    /**
     * Makes API request [POST] 'login.format' as defined in API v3 documentation.
     *
     * Logs user in using credentials provided (email & password).
     * Items received in the response from this request are used subsequently for
     * authentication and authorisation on subsequent requests.
     *
     * @param user
     * @param requestBody
     * @return
     */
    public User getBootstrap(User user, String requestBody) {

        if (loggedIn) {
//            Log.v(TAG, "Attempting to retrieve user info");

            Authentication bootstrapAuth = new Authentication(user, "GET", GET_BOOTSTRAP_URL, requestBody);
            Response response = apiRequest.getRequest(bootstrapAuth);

            if (response != null && response.getResponseCode() < 300) {

                mUser = responseHandler.parseLoginResponse(response.getResponseBody());
                if (mUser != null) {
                    loggedIn = true;
//                    Log.d(TAG, "Login attempt successful");
//                    Log.d(TAG, "Logged in as " + mUser.getFirstName() + " " + mUser.getLastName());
                    return mUser;
                } else {
//                    Log.e(TAG, "Login attempt unsuccessful");
                }
            }
        }
        return new User();
    }

    /**
     * Makes API request [GET] 'contacts.format' as defined in API v3 documentation.
     *
     * Returns a list of the logged in user's Contacts.
     *
     * @param user
     * @param requestBody
     * @return
     */
    public ArrayList<Contact> getContacts(User user, String requestBody) {

        if (loggedIn) {
//            Log.d(TAG, "Requesting list of Contacts");

            Authentication getContactsSig = new Authentication(user, "GET", GET_CONTACTS_URL, requestBody);
            Response response = apiRequest.getRequest(getContactsSig);

            if (response != null && response.getResponseCode() < 300) {
                ArrayList<Contact> contacts = responseHandler.parseGetContactsResponse(response.getResponseBody());
                if (!contacts.isEmpty()) {
//                    Log.d(TAG, "Received Contact information");
                    return contacts;
                } else {
//                    Log.d(TAG, "Did not receive any Contact information");
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * Makes API request [GET] 'action_stream.format' as defined in API v3 documentation.
     *
     * Returns a list of the logged in user's Contacts, like above in getContacts.
     * These are ordered as they are in the Action Stream part of the web app.
     *
     * @param user
     * @param requestBody
     * @return
     */
    public ArrayList<Contact> getActionStream(User user, String requestBody) {

        if (loggedIn) {
//            Log.d(TAG, "Requesting list of Contacts from Action Stream");

            Authentication getActionStreamSignature = new Authentication(user, "GET", GET_ACTION_STREAM_URL, requestBody);
            Response response = apiRequest.getRequest(getActionStreamSignature);

            if (response != null && response.getResponseCode() < 300) {
                ArrayList<Contact> contacts = responseHandler.parseGetContactsResponse(response.getResponseBody());
                if (!contacts.isEmpty()) {
//                    Log.d(TAG, "Received Action Stream (Contact info)");
                    return contacts;
                } else {
//                    Log.d(TAG, "No Contacts in Action Stream, according to response");
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * Makes API request [POST] 'contact.format' as defined in API v3 documentation.
     *
     * Adds a call record for a particular user.
     * Call object contains a Result (text) and a Note (text).
     *
     * @param contact
     * @param call
     */
    public boolean postCallResult(User user, Contact contact, Call call) {

        boolean saveSuccessful = false;
        if (loggedIn) {
//            Log.d(TAG, "Attempting to add Call result");

            HashMap<String, String> params = new HashMap<>();
            params.put("call_result", call.getCallResult());
            params.put("text", call.getNote());
            String callParams = encodeParams(params);

            POST_CALL_URL = API_V3_BASE_URL + "/calls." + FORMAT + "?contact_id=" + contact.getId();
            Authentication postCallSignature = new Authentication(user, "POST", POST_CALL_URL, callParams);
            Response response = apiRequest.postRequest(postCallSignature, callParams);

            if (response != null && response.getResponseCode() < 300) {
                if (responseHandler.parseCreateResourceResponse(response.getResponseBody())) {
                    saveSuccessful = true;
//                    Log.d(TAG, "Successfully created Call result resource in OnePageCRM DB via API");
                } else {
//                    Log.e(TAG, "Call result not created successfully");
                }
            }
        }
        return saveSuccessful;
    }
}
