package com.onepagecrm.models.serializers;

import com.onepagecrm.models.Call;
import com.onepagecrm.models.Note;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 4/25/16.
 */
public class NoteSerializer extends BaseSerializer{
    private static final String NOTES_TAG = "notes";
    private static final String NOTE_TAG = "note";
    public static final String LINKED_DEAL_ID_TAG = "linked_deal_id";

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
        }catch (Exception e){

        }
        return lNotes;
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
        lNote.setLinkedDealId(lJSONObject.optString(LINKED_DEAL_ID_TAG));
        return lNote;
    }
}
