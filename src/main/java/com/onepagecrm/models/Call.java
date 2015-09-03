package com.onepagecrm.models;

import com.onepagecrm.models.serializer.BaseSerializer;
import com.onepagecrm.models.serializer.CallResultSerializer;
import com.onepagecrm.models.serializer.CallSerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.PostRequest;

import java.util.Date;
import java.util.List;

public class Call extends ApiResource {

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

    public boolean save(Contact contact) {
        PostRequest saveRequest = new PostRequest(
                CALLS_ENDPOINT,
                saveQueryString(contact.getId()),
                CallResultSerializer.toJsonObject(this.callResult)
        );
        Response response = saveRequest.send();
        return BaseSerializer.createResourceFromString(response.getResponseBody());
    }

    private String saveQueryString(String contactId) {
        return "?contact_id=" + contactId;
    }

    public Call() {
    }

    @Override
    public String toString() {
        return CallSerializer.toJsonObject(this);
    }

    @Override
    public String getId() {
        return id;
    }

    public Call setId(String id) {
        this.id = id;
        return this;
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
}
