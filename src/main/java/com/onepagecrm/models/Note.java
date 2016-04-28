package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.serializers.NoteSerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.GetRequest;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Note extends ApiResource implements Serializable {

    private static final String NOTES_ENDPOINT = "notes";

    private String id;
    private String author;
    private String text;
    private String contactId;
    private Date createdAt;
    private Date date;
    private String linkedDealId;

    public static List<Note> list(Contact contact) throws OnePageException {
        Map<String, Object> params = new HashMap<>();
        params.put("contact_id", contact.getId());
        GetRequest getRequest = new GetRequest(NOTES_ENDPOINT, Query.fromParams(params));
        Response response = getRequest.send();
        return NoteSerializer.fromJsonString(response.getResponseBody());
    }

    public static List<Note> list() throws OnePageException {
        GetRequest request = new GetRequest(NOTES_ENDPOINT);
        Response lResponse = request.send();
        return NoteSerializer.fromJsonString(lResponse.getResponseBody());
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Note setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return null;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setLinkedDealId(String linkedDealId) {
        this.linkedDealId = linkedDealId;
    }

    public String getLinkedDealId() {
        return linkedDealId;
    }
}
