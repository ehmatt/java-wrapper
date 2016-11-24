package com.onepagecrm.models.serializers;

import com.onepagecrm.models.Account;
import com.onepagecrm.models.CallResult;
import com.onepagecrm.models.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CallResultSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CallResultSerializer.class.getName());

    public static String toJsonKeyValuePair(CallResult callResult) {
        if (callResult.getId() != null && callResult.getDisplay() != null) {
            return "\"" + callResult.getId() + "\":\"" + callResult.getDisplay() + "\"";
        }
        return null;
    }

    public static String toJsonObject(CallResult callResult) {
        JSONObject callObject = new JSONObject();
        addJsonStringValue(callResult.getDisplay(), callObject, callResult.getId());
        return callObject.toString();
    }

    public static String toJsonArray(List<CallResult> results) {
        JSONArray resultsArray = new JSONArray();
        for (int i = 0; i < results.size(); i++) {
            try {
                resultsArray.put(new JSONObject(toJsonObject(results.get(i))));
            } catch (JSONException e) {
                LOG.severe("Error creating JSONObject out of CallResult");
                LOG.severe(e.toString());
            }
        }
        return resultsArray.toString();
    }

    public static List<CallResult> getFromLoggedUser() {
        final User loggedUser = Account.loggedInUser;
        final boolean callResultsExist = loggedUser != null && loggedUser.isValid() &&
                loggedUser.getAccount() != null && loggedUser.getAccount().getCallResults() != null;
        return (callResultsExist) ?
                loggedUser.getAccount().getCallResults() : new ArrayList<CallResult>();
    }
}
