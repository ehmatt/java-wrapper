package com.onepagecrm.models.serializers;

import com.onepagecrm.models.Note;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alex on 4/25/16.
 */
public class NoteSerializer extends BaseSerializer{
    private static final String NOTES_TAG = "notes";
    private static final String NOTE_TAG = "note";
    public static final String LINKED_DEAL_ID_TAG = "linked_deal_id";
    private static final Logger LOG = Logger.getLogger(NoteSerializer.class.getName());

    public static List<Note> fromJsonString(String pResponseBody) {
        List<Note> lNotes = new LinkedList<>();
        try{
            JSONObject root = new JSONObject(pResponseBody);
            JSONObject data = root.optJSONObject(DATA_TAG);
            JSONArray calls = data.optJSONArray(NOTES_TAG);
            for (int i=0;i<calls.length();++i){
                JSONObject obj = calls.optJSONObject(i);
                Note lNote= objFromJson(obj);
                if (lNote!=null) {
                    lNotes.add(lNote);
                }
            }
        } catch (Exception e){
            LOG.severe("Could not find call object tags");
            LOG.severe(e.toString());
        }
        return lNotes;
    }

    public static Note fromString(String pResponseBody) {
        Note note = new Note();
        try {
            JSONObject responseObject = new JSONObject(pResponseBody);
            note = objFromJson(responseObject);
        } catch (JSONException e) {
            LOG.severe("Could not find call object tags");
            LOG.severe(e.toString());
        }

        return note;
    }

    private static Note objFromJson(JSONObject pObj) {
        JSONObject lJSONObject = pObj.optJSONObject(NOTE_TAG);
        if (lJSONObject == null)
            return null;
        Note lNote = new Note();
        lNote.setId(lJSONObject.optString(ID_TAG));
        lNote.setAuthor(lJSONObject.optString(AUTHOR_TAG));
        lNote.setText(lJSONObject.optString(TEXT_TAG));
        lNote.setContactId(lJSONObject.optString(CONTACT_ID_TAG));
        lNote.setCreatedAt(DateSerializer.fromFormattedString(lJSONObject.optString(CREATED_AT_TAG)));
        lNote.setDate(DateSerializer.fromFormattedString(lJSONObject.optString(DATE_TAG)));
        if (!lJSONObject.isNull(LINKED_DEAL_ID_TAG)) {
            lNote.setLinkedDealId(lJSONObject.optString(LINKED_DEAL_ID_TAG));
        }
        return lNote;
    }

    public static String toJsonObject(Note pNote) {
        JSONObject noteObject = new JSONObject();
        addJsonStringValue(pNote.getId(), noteObject, ID_TAG);
        addJsonStringValue(pNote.getAuthor(), noteObject, AUTH_KEY_TAG);
        addJsonStringValue(pNote.getText(), noteObject, TEXT_TAG);
        addJsonStringValue(pNote.getContactId(), noteObject, CONTACT_ID_TAG);
        addJsonStringValue(DateSerializer.toFormattedDateTimeString(pNote.getCreatedAt()), noteObject, CREATED_AT_TAG);
        addJsonStringValue(DateSerializer.toFormattedDateString(pNote.getDate()), noteObject, DATE_TAG);
        addJsonStringValue(pNote.getLinkedDealId(), noteObject, LINKED_DEAL_ID_TAG);
        return noteObject.toString();
    }
}
