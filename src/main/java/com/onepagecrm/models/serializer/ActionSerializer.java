package com.onepagecrm.models.serializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.onepagecrm.models.Action;

public class ActionSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ActionSerializer.class.getName());

    public static Action fromJsonObject(JSONObject actionObject) {
        Action action = new Action();
        Date date = null;
        try {
            String id = actionObject.getString(ID_TAG);
            String contactId = actionObject.getString(CONTACT_ID_TAG);
            String text = actionObject.getString(TEXT_TAG);
            String assigneeId = actionObject.getString(ASSIGNEE_ID_TAG);
            String modifiedAtStr = actionObject.getString(MODIFIED_AT_TAG);
            Date modifiedAt = DateSerializer.fromFormattedString(modifiedAtStr);
            String status = actionObject.getString(STATUS_TAG);

            if (actionObject.has(DATE_TAG)) {
                if (!actionObject.isNull(DATE_TAG)) {
                    date = DateSerializer.fromFormattedString(actionObject.getString(DATE_TAG));
                    action.setDate(date);
                }
            }
            int dateColor = DateSerializer.getDateColour(date, status);

            return action
                    .setId(id)
                    .setContactId(contactId)
                    .setText(text)
                    .setAssigneeId(assigneeId)
                    .setModifiedAt(modifiedAt)
                    .setStatus(status)
                    .setDateColor(dateColor);

        } catch (JSONException e) {
            LOG.severe("Error parsing contact object");
            LOG.severe(e.toString());
        }
        return new Action();
    }

    public static ArrayList<Action> fromJsonArray(JSONArray actionsArray) {
        ArrayList<Action> actions = new ArrayList<>();
        for (int i = 0; i < actionsArray.length(); i++) {
            try {
                actions.add(fromJsonObject(actionsArray.getJSONObject(i)));
            } catch (JSONException e) {
                LOG.severe("Error parsing contact object");
                LOG.severe(e.toString());
            }
        }
        return actions;
    }
}
