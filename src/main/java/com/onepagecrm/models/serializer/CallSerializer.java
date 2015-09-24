package com.onepagecrm.models.serializer;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Call;
import com.onepagecrm.models.CallResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class CallSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CallSerializer.class.getName());

    public static Call fromString(String responseBody) throws OnePageException {
        String parsedResponse;
        OnePageException exception;

        try {
            parsedResponse = (String) BaseSerializer.fromString(responseBody);
            JSONObject parsedObject = new JSONObject(parsedResponse);
            JSONObject callObject = parsedObject.getJSONObject(CALL_TAG);
            return CallSerializer.fromJsonObject(callObject);

        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;
        } catch (JSONException e) {
            LOG.severe("Could not find call tag");
            LOG.severe(e.toString());
        }
        return new Call();
    }

    public static Call fromJsonObject(JSONObject callObject) {
        Call call = new Call();
        try {
            String id = callObject.getString(ID_TAG);
            String text = callObject.getString(TEXT_TAG);
            String callResultId = callObject.getString(CALL_RESULT_TAG);
            String callTimeIntString = callObject.getString(CALL_TIME_INT_TAG);
            int callTimeInt = Integer.parseInt(callTimeIntString);
            Date callTime = new Date(callTimeInt);
            String contactId = callObject.getString(CONTACT_ID_TAG);
            String createdAtString = callObject.getString(CREATED_AT_TAG);
            Date createdAt = DateSerializer.fromFormattedString(createdAtString);
            String modifiedAtString = callObject.getString(MODIFIED_AT_TAG);
            Date modifiedAt = DateSerializer.fromFormattedString(modifiedAtString);
            String via = callObject.getString(VIA_TAG);
            String author = callObject.getString(AUTHOR_TAG);

//            JSONArray attachmentsArray = callObject.getJSONArray(ATTACHMENTS_TAG);
//            List<Attachment> attachments = AttachmentSerializer.fromJsonArray(attachmentsArray);

            call.setCallResult
                    (new CallResult()
                            .setText(text)
                            .setId(callResultId))
                    .setId(id)
                    .setTime(callTime)
                    .setContactId(contactId)
                    .setCreatedAt(createdAt)
                    .setModifiedAt(modifiedAt)
                    .setVia(via)
                    .setAuthor(author);
//            .setAttachments(attachments);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return call;
    }

    public static String toJsonObject(Call call) {
        JSONObject callObject = new JSONObject();
        addJsonStringValue(call.getId(), callObject, ID_TAG);
        addJsonStringValue(call.getCallResult().getText(), callObject, TEXT_TAG);
        addJsonStringValue((call.getCallResult().getId()), callObject, CALL_RESULT_TAG);
        addJsonLongValue(call.getTime().getTime(), callObject, CALL_TIME_INT_TAG);
        addJsonStringValue(call.getContactId(), callObject, CONTACT_ID_TAG);
        addJsonStringValue(DateSerializer.toFormattedDateTimeString(call.getCreatedAt()), callObject, CREATED_AT_TAG);
        addJsonStringValue(DateSerializer.toFormattedDateTimeString(call.getModifiedAt()), callObject, MODIFIED_AT_TAG);
        addJsonStringValue(call.getVia(), callObject, VIA_TAG);
        addJsonStringValue(call.getAuthor(), callObject, AUTHOR_TAG);
//        addJsonStringValue(call.getAttachments(), callObject, ATTACHMENTS_TAG);
        return callObject.toString();
    }

    public static String toJsonArray(List<Call> calls) {
        JSONArray callsArray = new JSONArray();
        for (int i = 0; i < calls.size(); i++) {
            try {
                callsArray.put(new JSONObject(toJsonObject(calls.get(i))));
            } catch (JSONException e) {
                LOG.severe("Error creating JSONObject out of Call");
                LOG.severe(e.toString());
            }
        }
        return callsArray.toString();
    }
}
