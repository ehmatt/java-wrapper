package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.serializers.CallSerializer;
import com.onepagecrm.models.serializers.NoteSerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.GetRequest;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Note extends ApiResource implements Serializable {

    private static final java.lang.String NOTES_ENDPOINT = "notes";
    private String id;
    private String mAuthor;
    private String mText;
    private String mContactId;
    private Date mCreatedAt;
    private Date mDate;
    private String mLinkedDealId;

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

    public static List<Note> list(Contact pContact) throws OnePageException{
        GetRequest getRequest = new GetRequest(NOTES_ENDPOINT,Query.contactIdQueryString(pContact.getId()));
        Response response = getRequest.send();
        return NoteSerializer.fromJsonString(response.getResponseBody());
    }
    public static List<Note> list()throws OnePageException{
        GetRequest request = new GetRequest(NOTES_ENDPOINT);
        Response lResponse = request.send();
        return NoteSerializer.fromJsonString(lResponse.getResponseBody());
    }

    public void setAuthor(String pAuthor) {
        mAuthor = pAuthor;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setText(String pText) {
        mText = pText;
    }

    public String getText() {
        return mText;
    }

    public void setContactId(String pContactId) {
        mContactId = pContactId;
    }

    public String getContactId() {
        return mContactId;
    }

    public void setCreatedAt(Date pCreatedAt) {
        mCreatedAt = pCreatedAt;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public void setDate(Date pDate) {
        mDate = pDate;
    }

    public Date getDate() {
        return mDate;
    }

    public void setLinkedDealId(String pLinkedDealId) {
        mLinkedDealId = pLinkedDealId;
    }

    public String getLinkedDealId() {
        return mLinkedDealId;
    }
}
