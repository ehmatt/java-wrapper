package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.serializers.CallSerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.GetRequest;
import com.onepagecrm.net.request.PostRequest;
import com.onepagecrm.net.request.PutRequest;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Call extends ApiResource implements Serializable {

    private String id;
    private String author;
    private CallResult callResult;
    private Date time;
    private String contactId;
    private Date createdAt;
    private String phoneNumber;
    private Date modifiedAt;
    private String via;
    private String recordingLink;
    private List<Attachment> attachments;
    private String mText;

    public static List<Call> list(Contact contact) throws OnePageException {
        Map<String, Object> params = new HashMap<>();
        params.put("contact_id", contact.getId());
        GetRequest getRequest = new GetRequest(CALLS_ENDPOINT, Query.fromParams(params));
        Response response = getRequest.send();
        return CallSerializer.listFromString(response.getResponseBody());
    }

    public static List<Call> list() throws OnePageException {
        GetRequest getRequest = new GetRequest(CALLS_ENDPOINT);
        Response response = getRequest.send();
        return CallSerializer.listFromString(response.getResponseBody());
    }

    public Call update() throws OnePageException {
        PutRequest updateRequest = new PutRequest(
                addCallIdToEndpoint(CALLS_ENDPOINT),
                null,
                CallSerializer.toJsonObject(this)
        );
        Response response = updateRequest.send();
        return CallSerializer.fromString(response.getResponseBody());
    }

    public Call create(Contact contact) throws OnePageException {
        Map<String, Object> params = new HashMap<>();
        params.put("contact_id", contact.getId());
        PostRequest createRequest = new PostRequest(
                CALLS_ENDPOINT,
                Query.fromParams(params),
                CallSerializer.toJsonObject(this)
        );
        Response response = createRequest.send();
        return CallSerializer.fromString(response.getResponseBody());
    }

    private String addCallIdToEndpoint(String endpoint) {
        return endpoint + "/" + this.id;
    }

    public Call() {
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Call setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return CallSerializer.toJsonObject(this);
    }

    public String getAuthor() {
        return author;
    }

    public Call setAuthor(String author) {
        this.author = author;
        return this;
    }

    public CallResult getCallResult() {
        return callResult;
    }

    public Call setCallResult(CallResult callResult) {
        this.callResult = callResult;
        return this;
    }

    public Date getTime() {
        return time;
    }

    public Call setTime(Date time) {
        this.time = time;
        return this;
    }

    public String getContactId() {
        return contactId;
    }

    public Call setContactId(String contactId) {
        this.contactId = contactId;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Call setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Call setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public Call setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public String getVia() {
        return via;
    }

    public Call setVia(String via) {
        this.via = via;
        return this;
    }

    public String getRecordingLink() {
        return recordingLink;
    }

    public Call setRecordingLink(String recordingLink) {
        this.recordingLink = recordingLink;
        return this;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public Call setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    public void setText(String pText) {
        mText = pText;
    }

    public String getText() {
        return mText;
    }
}
