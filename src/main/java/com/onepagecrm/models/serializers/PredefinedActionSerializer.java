package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.internal.PredefinedAction;
import com.onepagecrm.net.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 17/05/2016.
 */
public class PredefinedActionSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(PredefinedActionSerializer.class.getSimpleName());

    private static PredefinedAction DEFAULT = new PredefinedAction();

    public static List<PredefinedAction> fromResponse(Response response) throws APIException {
        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
        return fromJsonArray(dataObject.optJSONArray(PREDEFINED_ACTIONS_TAG));
    }

    // TODO: delete
    public static List<PredefinedAction> fromString(String responseBody) throws APIException {
        try {
            String dataString = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(dataString);
            return fromJsonArray(responseObject.optJSONArray(PREDEFINED_ACTIONS_TAG));

        } catch (JSONException e) {
            LOG.severe("Error parsing JSON" + e);
            return new ArrayList<>();
        }
    }

    public static PredefinedAction fromJsonObject(JSONObject actionObject) {
        if (actionObject == null) {
            return DEFAULT;
        }
        if (actionObject.has(PREDEFINED_ACTION_TAG)) {
            actionObject = actionObject.optJSONObject(PREDEFINED_ACTION_TAG);
        }
        return new PredefinedAction()
                .setId(actionObject.optString(ID_TAG))
                .setText(actionObject.optString(TEXT_TAG))
                .setDays(actionObject.optInt(DAYS_TAG));
    }

    public static List<PredefinedAction> fromJsonArray(JSONArray actionsArray) {
        List<PredefinedAction> actions = new ArrayList<>();
        if (actionsArray == null) return actions;
        for (int i = 0; i < actionsArray.length(); ++i) {
            JSONObject actionObject = actionsArray.optJSONObject(i);
            actions.add(fromJsonObject(actionObject));
        }
        return actions;
    }

    public static JSONObject toJsonObject(PredefinedAction action) {
        JSONObject actionObject = new JSONObject();
        if (action == null) return actionObject;
        addJsonStringValue(action.getId(), actionObject, ID_TAG);
        addJsonStringValue(action.getText(), actionObject, TEXT_TAG);
        addJsonIntegerValue(action.getDays(), actionObject, DAYS_TAG);
        return actionObject;
    }

    public static String toJsonString(PredefinedAction action) {
        return toJsonObject(action).toString();
    }

    public static JSONArray toJsonArray(List<PredefinedAction> actions) {
        JSONArray actionsArray = new JSONArray();
        if (actions == null || actions.isEmpty()) return actionsArray;
        for (PredefinedAction action : actions) {
            actionsArray.put(toJsonObject(action));
        }
        return actionsArray;
    }

    public static String toJsonString(List<PredefinedAction> actions) {
        return toJsonArray(actions).toString();
    }
}
