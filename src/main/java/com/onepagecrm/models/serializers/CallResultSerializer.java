package com.onepagecrm.models.serializers;

import com.onepagecrm.models.Account;
import com.onepagecrm.models.CallResult;
import com.onepagecrm.models.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 01/08/2017.
 */
@SuppressWarnings({"WeakerAccess", "ForLoopReplaceableByForEach", "unused", "unchecked"})
public class CallResultSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CallResultSerializer.class.getName());

    private static CallResult DEFAULT = new CallResult();

    public static List<CallResult> fromJsonObject(JSONObject dataObject) {
        if (dataObject == null) {
            return defaultCallResults();
        }

        JSONObject callResultsObject = dataObject.optJSONObject(CALL_RESULTS_TAG);
        if (callResultsObject == null) {
            return defaultCallResults();
        }

        Map<String, CallResult> resultMap = new HashMap<>();
        List<CallResult> resultList = new LinkedList<>();
        Iterator<String> iterator = callResultsObject.keys();
        int index = 0;

        while (iterator.hasNext()) {
            String key = iterator.next();
            try {
                String value = callResultsObject.getString(key);
                CallResult callResult = new CallResult()
                        .setId(key)
                        .setDisplay(value)
                        .setPosition(index);
                resultMap.put(key, callResult);
                resultList.add(callResult);

            } catch (JSONException e) {
                LOG.severe("Failed to parse all values in call_results object");
                LOG.severe(e.toString());
            }
            index++;
        }

        JSONArray callResultsOrderArray = dataObject.optJSONArray(CALL_RESULTS_ORDER_TAG);
        if (callResultsOrderArray != null) {
            List<CallResult> orderedList = new LinkedList<>();
            String id;
            for (int i = 0; i < callResultsOrderArray.length(); i++) {
                try {
                    id = callResultsOrderArray.getString(i);
                    CallResult result = resultMap.get(id);
                    result.setPosition(i);
                    orderedList.add(result);

                } catch (JSONException e) {
                    LOG.severe("Failed to parse all values in call_results_order array");
                    LOG.severe(e.toString());
                }
            }
            return orderedList;
        }

        return resultList;
    }

    public static JSONObject toJsonObject(CallResult callResult) {
        JSONObject callObject = new JSONObject();
        if (callResult == null) return callObject;
        addJsonStringValue(callResult.getDisplay(), callObject, callResult.getId());
        return callObject;
    }

    public static JSONArray toJsonArray(List<CallResult> callResults) {
        JSONArray callResultsArray = new JSONArray();
        if (callResults == null || callResults.isEmpty()) {
            return callResultsArray;
        }
        for (CallResult callResult : callResults) {
            callResultsArray.put(toJsonObject(callResult));
        }
        return callResultsArray;
    }

    public static String toJsonString(CallResult callResult) {
        return toJsonObject(callResult).toString();
    }

    public static String toJsonString(List<CallResult> callResults) {
        return toJsonArray(callResults).toString();
    }

    public static List<CallResult> getFromLoggedUser() {
        final User loggedUser = Account.loggedInUser;
        final boolean callResultsExist = loggedUser != null && loggedUser.isValid() &&
                loggedUser.getAccount() != null && loggedUser.getAccount().getCallResults() != null;
        return (callResultsExist) ?
                loggedUser.getAccount().getCallResults() : new ArrayList<CallResult>();
    }

    public static List<CallResult> defaultCallResults() {
        List<CallResult> callResults = new LinkedList<>();
        callResults.add(new CallResult().setPosition(0).setId("interested").setDisplay("Interested"));
        callResults.add(new CallResult().setPosition(1).setId("not_interested").setDisplay("Not interested"));
        callResults.add(new CallResult().setPosition(2).setId("left_message").setDisplay("Left message"));
        callResults.add(new CallResult().setPosition(3).setId("no_answer").setDisplay("No answer"));
        callResults.add(new CallResult().setPosition(4).setId("other").setDisplay("Other"));
        return callResults;
    }
}
