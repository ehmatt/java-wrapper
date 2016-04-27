package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Account;
import com.onepagecrm.models.Call;
import com.onepagecrm.models.CallResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class CallSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CallSerializer.class.getName());
    private static final java.lang.String PHONE_NUMBER_TAG = "phone_number";
    private static final java.lang.String RECORDING_LINK_TAG = "recording_link";

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
        CallResult result = new CallResult();

        try {
            if (callObject.has(ID_TAG)) {
                call.setId(callObject.getString(ID_TAG));
            }
            if (callObject.has(CALL_RESULT_TAG)) {
                result.setId(callObject.getString(CALL_RESULT_TAG));
            }
            if (callObject.has(CALL_TIME_INT_TAG)) {
                String callTimeIntString = callObject.getString(CALL_TIME_INT_TAG);
                int callTimeInt = Integer.parseInt(callTimeIntString);
                Date callTime = new Date(callTimeInt);
                call.setTime(callTime);
            }
            if (callObject.has(CONTACT_ID_TAG)) {
                call.setContactId(callObject.getString(CONTACT_ID_TAG));
            }
            if (callObject.has(CREATED_AT_TAG)) {
                String createdAtString = callObject.getString(CREATED_AT_TAG);
                Date createdAt = DateSerializer.fromFormattedString(createdAtString);
                call.setCreatedAt(createdAt);
            }
            if (callObject.has(MODIFIED_AT_TAG)) {
                String modifiedAtString = callObject.getString(MODIFIED_AT_TAG);
                Date modifiedAt = DateSerializer.fromFormattedString(modifiedAtString);
                call.setModifiedAt(modifiedAt);
            }
            if (callObject.has(VIA_TAG)) {
                call.setVia(callObject.getString(VIA_TAG));
            }
            if (callObject.has(AUTHOR_TAG)) {
                call.setAuthor(callObject.getString(AUTHOR_TAG));
            }

//            JSONArray attachmentsArray = callObject.getJSONArray(ATTACHMENTS_TAG);
//            List<Attachment> attachments = AttachmentSerializer.fromJsonArray(attachmentsArray);
//            .setAttachments(attachments);

            call.setCallResult(result);

        } catch (JSONException e) {
            LOG.severe("Could not find call object tags");
            LOG.severe(e.toString());
        }
        return call;
    }

    public static String toJsonObject(Call call) {
        JSONObject callObject = new JSONObject();
        addJsonStringValue(call.getId(), callObject, ID_TAG);
        addJsonStringValue(call.getCallResult().getText(), callObject, TEXT_TAG);
        addJsonStringValue(call.getCallResult().getId(), callObject, CALL_RESULT_TAG);
        addJsonLongValue(DateSerializer.dateInMillis(call.getTime()), callObject, CALL_TIME_INT_TAG);
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

    public static List<Call> listFromString(String pResponseBody) {
        List<Call> lCalls = new LinkedList<>();
        try{
            JSONObject root = new JSONObject(pResponseBody);
            JSONObject data = root.optJSONObject(DATA_TAG);
            JSONArray calls = data.optJSONArray(CALLS_TAG);
            for (int i=0;i<calls.length();++i){
                JSONObject obj = calls.optJSONObject(i);
                Call lCall = objFromJson(obj);
                if (lCall!=null) {
                    lCalls.add(lCall);
                }
            }
        }catch (Exception e){
            LOG.severe(e.toString());
        }
        return lCalls;
    }

    private static Call objFromJson(JSONObject pObj) {
        JSONObject lObject = pObj.optJSONObject("call");
        if (lObject!=null){
            Call lCall = new Call();
            lCall.setId(lObject.optString(ID_TAG));
            lCall.setVia(lObject.optString(VIA_TAG));
            lCall.setAuthor(lObject.optString(AUTHOR_TAG));
            lCall.setPhoneNumber(lObject.optString(PHONE_NUMBER_TAG));
            lCall.setText(lObject.optString(TEXT_TAG));
            String callResultId = lObject.optString(CALL_RESULT_TAG);
            for (CallResult lCallResult:Account.loggedInUser.getAccount().getCallResults()){
                if (lCallResult.getId().equals(callResultId)){
                    lCall.setCallResult(lCallResult);
                }
            }
            lCall.setTime(DateSerializer.fromNumString(lObject.optString(CALL_TIME_INT_TAG)));
            lCall.setContactId(lObject.optString(CONTACT_ID_TAG));
            lCall.setRecordingLink(lObject.optString(RECORDING_LINK_TAG));
            lCall.setCreatedAt(DateSerializer.fromFormattedString(lObject.optString(CREATED_AT_TAG)));
            lCall.setModifiedAt(DateSerializer.fromFormattedString(lObject.optString(MODIFIED_AT_TAG)));
            return lCall;
        }
        return null;
    }
}
