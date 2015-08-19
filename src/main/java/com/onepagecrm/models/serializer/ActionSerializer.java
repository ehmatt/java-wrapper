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

    public static Action fromJSONObject(JSONObject actionObject) {

	Action action = new Action();
	
	try {
	    String id = actionObject.getString(ID_TAG);
	    String contactId = actionObject.getString(CONTACT_ID_TAG);
	    String text = actionObject.getString(TEXT_TAG);
	    String assigneeId = actionObject.getString(ASSIGNEE_ID_TAG);
	    String modifiedAtStr = actionObject.getString(MODIFIED_AT_TAG);
	    Date modifiedAt = DateSerializer.fromFormattedString(modifiedAtStr);
	    String status = actionObject.getString(STATUS_TAG);
	    
	    if (actionObject.has(DATE_TAG)) {
//		Object dateObj = actionObject.get(DATE_TAG);
		if (!actionObject.isNull(DATE_TAG)) {
		    Date date = DateSerializer.fromFormattedString(actionObject.getString(DATE_TAG));
		    action.setDate(date);
		} 
	    }
	    
//	    if (actionObject.has(DATE_TAG)) {
//		Object dateObj = actionObject.get(DATE_TAG);
//		if (dateObj instanceof String) {
//		    Date date = DateSerializer.fromFormattedString((String) dateObj);
//		} 
//	    }
	    
//	    String dateStr = actionObject.getString(DATE_TAG);
//	    Date date = DateSerializer.fromFormattedString(dateStr);

	    return action
	    	.setId(id)
	    	.setContactId(contactId)
	    	.setText(text)
	    	.setAssigneeId(assigneeId)
	    	.setModifiedAt(modifiedAt)
	    	.setStatus(status);
	    
	} catch (JSONException e) {
	    LOG.severe("Error parsing contact object");
	    LOG.severe(e.toString());
	}
	return new Action();
    }

    public static ArrayList<Action> fromJSONArray(JSONArray actionsArray) {

	ArrayList<Action> actions = new ArrayList<>();

	for (int i = 0; i < actionsArray.length(); i++) {
	    try {
		actions.add(fromJSONObject(actionsArray.getJSONObject(i)));
	    } catch (JSONException e) {
		LOG.severe("Error parsing contact object");
		LOG.severe(e.toString());
	    }
	}

	return actions;
    }
}
