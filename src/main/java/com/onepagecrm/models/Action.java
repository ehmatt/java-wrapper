package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.serializers.ActionSerializer;
import com.onepagecrm.models.serializers.DateSerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.PostRequest;
import com.onepagecrm.net.request.PutRequest;
import com.onepagecrm.net.request.Request;

import java.io.Serializable;
import java.util.Date;

public class Action extends ApiResource implements Serializable {

    private static final long serialVersionUID = -7486991046434989805L;
    private static final String ACTIONS_ENDPOINT = "actions";
    private static final String MARK_COMPLETE_ENDPOINT = "actions/{id}/mark_as_done";
    private static final String UNDO_COMLETION_ENDPOINT = "actions/{id}/undo_completion";
    private String id;
    private String assigneeId;
    private String contactId;
    private String text;
    private Date modifiedAt;
    private String status;
    private Date date;
    private int dateColor;

    public Action() {
    }

    private Action create() throws OnePageException {
        Request request = new PostRequest("contacts/" + contactId + "/" + ACTIONS_ENDPOINT, null, ActionSerializer.toJsonObject(this));
        Response response = request.send();
        return ActionSerializer.fromString(response.getResponseBody());
    }

    private Action update() throws OnePageException {
        Request request = new PutRequest(addActionIdToEndpoint(ACTIONS_ENDPOINT), null, ActionSerializer.toJsonObject(this));
        Response response = request.send();
        return ActionSerializer.fromString(response.getResponseBody());
    }

    public Action save() throws OnePageException {
        return isValid() ? update() : create();
    }

    public Action markComplete() throws OnePageException {
        String endpoint = MARK_COMPLETE_ENDPOINT.replace("{id}", this.getId());
        Request request = new PutRequest(endpoint, null, ActionSerializer.toJsonObject(this));
        Response response = request.send();
        return ActionSerializer.fromString(response.getResponseBody());
    }

    public Action undoCompletion() throws OnePageException {
        String endpoint = UNDO_COMLETION_ENDPOINT.replace("{id}", this.getId());
        Request request = new PutRequest(endpoint, null, ActionSerializer.toJsonObject(this));
        Response response = request.send();
        return ActionSerializer.fromString(response.getResponseBody());
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Action setId(String id) {
        this.id = id;
        return this;
    }

    private String addActionIdToEndpoint(String endpoint) {
        return endpoint + "/" + this.id;
    }

    @Override
    public String toString() {
        return ActionSerializer.toJsonObject(this);
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public Action setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
        return this;
    }

    public String getContactId() {
        return contactId;
    }

    public Action setContactId(String contactId) {
        this.contactId = contactId;
        return this;
    }

    public String getText() {
        return text;
    }

    public Action setText(String text) {
        this.text = text;
        return this;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public Action setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Action setStatus(String status) {
        this.status = status;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Action setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getFriendlyDateString() {
        if (this.date != null) {
            // Return date in format yyyy-MM-dd (uppercase).
            return DateSerializer.toFriendlyDateString(this.date).toUpperCase();
        } else if (this.status != null) {
            // Return status (uppercase).
            return this.status.toUpperCase();
        } else {
            // This is needed to correctly display contacts w/out NA's in Action Stream.
            return null;
        }
    }

    public int getDateColor() {
        return dateColor;
    }

    public Action setDateColor(int dateColor) {
        this.dateColor = dateColor;
        return this;
    }
}
