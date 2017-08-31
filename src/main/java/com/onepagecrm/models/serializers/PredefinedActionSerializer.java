package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.internal.PredefinedAction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 17/05/2016.
 */
public class PredefinedActionSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(PredefinedActionSerializer.class.getSimpleName());

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
        return new PredefinedAction()
                .setId(actionObject.optString(ID_TAG))
                .setText(actionObject.optString(TEXT_TAG))
                .setDays(actionObject.optInt(DAYS_TAG));
    }

    public static List<PredefinedAction> fromJsonArray(JSONArray actionsArray) {
        List<PredefinedAction> predefinedActions = new LinkedList<>();
        if (actionsArray != null) {
            for (int i = 0; i < actionsArray.length(); ++i) {
                predefinedActions.add(
                        fromJsonObject(actionsArray.optJSONObject(i).optJSONObject(PREDEFINED_ACTION_TAG)));
            }
        }
        return predefinedActions;
    }

    public static String toJsonObject(PredefinedAction action) {
        JSONObject actionObject = new JSONObject();
        if (action != null) {
            addJsonStringValue(action.getId(), actionObject, ID_TAG);
            addJsonStringValue(action.getText(), actionObject, TEXT_TAG);
            addJsonIntegerValue(action.getDays(), actionObject, DAYS_TAG);
        }
        return actionObject.toString();
    }

    public static String toJsonArray(List<PredefinedAction> actions) {
        JSONArray actionsArray = new JSONArray();
        if (actions != null && !actions.isEmpty()) {
            for (int i = 0; i < actions.size(); i++) {
                try {
                    actionsArray.put(new JSONObject(toJsonObject(actions.get(i))));
                } catch (JSONException e) {
                    LOG.severe("Error creating JSONArray out of list of Predefined Actions.");
                    LOG.severe(e.toString());
                }
            }
        }
        return actionsArray.toString();
    }
}
