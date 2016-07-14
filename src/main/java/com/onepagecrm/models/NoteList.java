package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by mahasamatman on 13.07.16.
 */
public class NoteList extends ResourceList<Note> implements Serializable {
    
    private String contactId;

    public NoteList() {
        super(null);
    }

    public NoteList(List<Note> list, String contactId) {
        super(list);
        this.contactId = contactId;
    }

    public NoteList(List<Note> list) {
        super(list);
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @Override
    public List<Note> nextPage(Map<String, Object> params) throws OnePageException {
        this.paginator.getNextPageNo();
        return Note.list(contactId, paginator);
    }

    @Override
    public List<Note> refresh(Map<String, Object> params) throws OnePageException {
        return null;
    }
}
