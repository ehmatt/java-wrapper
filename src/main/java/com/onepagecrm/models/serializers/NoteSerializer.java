package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Note;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alex on 4/25/16.
 *
 * @author Cillian Myles (cillian@onepagecrm.com) on 31/07/2017.
 */
public class NoteSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(NoteSerializer.class.getName());

    public static Note fromString(String responseBody) throws OnePageException {
        Note note = new Note();
        try {
            String parsedResponse = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(parsedResponse);
            return fromJsonObject(responseObject);

        } catch (ClassCastException e) {
            throw (OnePageException) BaseSerializer.fromString(responseBody);

        } catch (JSONException e) {
            LOG.severe("Error parsing Note from JSON.");
            LOG.severe(e.toString());
        }
        return note;
    }

    public static Note fromJsonObject(JSONObject noteObject) {
        // Fix for some objects not having name.
        if (noteObject.has(NOTE_TAG)) {
            noteObject = noteObject.optJSONObject(NOTE_TAG);
        }
        return new Note()
                .setId(noteObject.optString(ID_TAG))
                .setAuthor(noteObject.optString(AUTHOR_TAG))
                .setText(noteObject.optString(TEXT_TAG))
                .setContactId(noteObject.optString(CONTACT_ID_TAG))
                .setDate(DateSerializer.fromFormattedString(noteObject.optString(DATE_TAG)))
                .setLinkedDealId(!noteObject.isNull(LINKED_DEAL_ID_TAG) ? noteObject.optString(LINKED_DEAL_ID_TAG) : null)
                .setCreatedAt(DateSerializer.fromFormattedString(noteObject.optString(CREATED_AT_TAG)))
                .setModifiedAt(DateSerializer.fromFormattedString(noteObject.optString(MODIFIED_AT_TAG)))
                .setAttachments(AttachmentSerializer.fromJsonArray(noteObject.optJSONArray(ATTACHMENTS_TAG)));
    }

    public static List<Note> fromJsonArray(JSONArray notesArray) {
        List<Note> notes = new LinkedList<>();
        if (notesArray == null) return notes;
        for (int i = 0; i < notesArray.length(); ++i) {
            JSONObject noteObject = notesArray.optJSONObject(i);
            Note note = fromJsonObject(noteObject);
            if (note != null) {
                notes.add(note);
            }
        }
        return notes;
    }

    public static String toJsonObject(Note note) {
        JSONObject noteObject = new JSONObject();
        addJsonStringValue(note.getId(), noteObject, ID_TAG);
        addJsonStringValue(note.getAuthor(), noteObject, AUTH_KEY_TAG);
        addJsonStringValue(note.getText(), noteObject, TEXT_TAG);
        addJsonStringValue(note.getContactId(), noteObject, CONTACT_ID_TAG);
        addJsonStringValue(DateSerializer.toFormattedDateTimeString(note.getCreatedAt()), noteObject, CREATED_AT_TAG);
        addJsonStringValue(DateSerializer.toFormattedDateString(note.getDate()), noteObject, DATE_TAG);
        addJsonStringValue(note.getLinkedDealId(), noteObject, LINKED_DEAL_ID_TAG);
        return noteObject.toString();
    }

    public static String toJsonArray(List<Note> notes) {
        JSONArray notesArray = new JSONArray();
        for (int i = 0; i < notes.size(); i++) {
            try {
                notesArray.put(new JSONObject(toJsonObject(notes.get(i))));
            } catch (JSONException e) {
                LOG.severe("Error creating JSON out of Note(s).");
                LOG.severe(e.toString());
            }
        }
        return notesArray.toString();
    }
}
