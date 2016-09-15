package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.NoteList;
import com.onepagecrm.models.internal.Paginator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/09/2016.
 */
public class NoteListSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(NoteListSerializer.class.getName());

    public static NoteList fromString(String responseBody) throws OnePageException {
        NoteList notes = new NoteList();
        String parsedResponse;
        OnePageException exception;

        try {
            parsedResponse = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(parsedResponse);
            JSONArray notesArray = responseObject.optJSONArray(NOTES_TAG);
            Paginator paginator = RequestMetadataSerializer.fromJsonObject(responseObject);
            notes.setPaginator(paginator);
            notes.setList(NoteSerializer.fromJsonArray(notesArray));

        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;

        } catch (Exception e) {
            LOG.severe("Error parsing NoteList from JSON.");
            LOG.severe(e.toString());
        }

        return notes;
    }
}
