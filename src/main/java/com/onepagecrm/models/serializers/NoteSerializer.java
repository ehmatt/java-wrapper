package com.onepagecrm.models.serializers;

import com.onepagecrm.models.Note;
import com.onepagecrm.models.NoteList;
import com.onepagecrm.models.internal.Paginator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alex on 4/25/16.
 */
public class NoteSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(NoteSerializer.class.getName());

    public static Note fromString(String responseBody) {
        Note note = new Note();
        try {
            JSONObject responseObject = new JSONObject(responseBody);
            JSONObject dataObject = responseObject.optJSONObject(DATA_TAG);
            return fromJsonObject(dataObject);

        } catch (JSONException e) {
            LOG.severe("Error parsing Note from JSON.");
            LOG.severe(e.toString());
        }

        return note;
    }

    public static NoteList listFromString(String responseBody) {
        NoteList notes = new NoteList();
        try {
            JSONObject responseObject = new JSONObject(responseBody);
            JSONObject dataObject = responseObject.optJSONObject(DATA_TAG);
            JSONArray notesArray = dataObject.optJSONArray(NOTES_TAG);
            Paginator paginator = RequestMetadataSerializer.fromJsonObject(dataObject);
            notes.setPaginator(paginator);

            for (int i = 0; i < notesArray.length(); ++i) {
                JSONObject noteObject = notesArray.optJSONObject(i);
                Note note = fromJsonObject(noteObject);
                if (note != null) {
                    notes.add(note);
                }
            }
        } catch (Exception e) {
            LOG.severe("Error parsing NoteList from JSON.");
            LOG.severe(e.toString());
        }
        return notes;
    }

    public static Note fromJsonObject(JSONObject noteObject) {
        // Fix for some objects not having name.
        if (noteObject.has(NOTE_TAG)) {
            noteObject = noteObject.optJSONObject(NOTE_TAG);
        }
        Note note = new Note();
        note.setId(noteObject.optString(ID_TAG));
        note.setAuthor(noteObject.optString(AUTHOR_TAG));
        note.setText(noteObject.optString(TEXT_TAG));
        note.setContactId(noteObject.optString(CONTACT_ID_TAG));
        note.setCreatedAt(DateSerializer.fromFormattedString(noteObject.optString(CREATED_AT_TAG)));
        note.setDate(DateSerializer.fromFormattedString(noteObject.optString(DATE_TAG)));
        if (!noteObject.isNull(LINKED_DEAL_ID_TAG)) {
            note.setLinkedDealId(noteObject.optString(LINKED_DEAL_ID_TAG));
        }
        return note;
    }

    public static List<Note> fromJsonArray(JSONArray notesArray) {
        List<Note> notes = new LinkedList<>();
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
                LOG.severe("Error creating JSONObject out of Note");
                LOG.severe(e.toString());
            }
        }
        return notesArray.toString();
    }
}
