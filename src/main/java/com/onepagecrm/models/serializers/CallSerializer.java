package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Account;
import com.onepagecrm.models.Call;
import com.onepagecrm.models.CallResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class CallSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CallSerializer.class.getName());

    private static final java.lang.String PHONE_NUMBER_TAG = "phone_number";
    private static final java.lang.String RECORDING_LINK_TAG = "recording_link";

    public static Call fromString(String responseBody) throws OnePageException {
        OnePageException exception;

        try {
            JSONObject responseObject = new JSONObject(responseBody);
            JSONObject data = responseObject.optJSONObject(DATA_TAG);
            return CallSerializer.objFromJson(data);

        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;

        } catch (JSONException e) {
            LOG.severe("Could not find call tag");
            LOG.severe(e.toString());
        }
        return new Call();
    }

    public static String toJsonObject(Call call) {
        JSONObject callObject = new JSONObject();
        addJsonStringValue(call.getId(), callObject, ID_TAG);
        addJsonStringValue(call.getText(), callObject, TEXT_TAG);
        addJsonStringValue(call.getCallResult().getId(), callObject, CALL_RESULT_TAG);
        addJsonLongValue(DateSerializer.dateInMillis(call.getTime()), callObject, CALL_TIME_INT_TAG);
        addJsonStringValue(call.getContactId(), callObject, CONTACT_ID_TAG);
        addJsonStringValue(DateSerializer.toFormattedDateTimeString(call.getCreatedAt()), callObject, CREATED_AT_TAG);
        addJsonStringValue(DateSerializer.toFormattedDateTimeString(call.getModifiedAt()), callObject, MODIFIED_AT_TAG);
        addJsonStringValue(call.getVia(), callObject, VIA_TAG);
        addJsonStringValue(call.getAuthor(), callObject, AUTHOR_TAG);
        addJsonStringValue(call.getPhoneNumber(), callObject, PHONE_NUMBER_TAG);
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
        try {
            JSONObject root = new JSONObject(pResponseBody);
            JSONObject data = root.optJSONObject(DATA_TAG);
            JSONArray calls = data.optJSONArray(CALLS_TAG);
            for (int i = 0; i < calls.length(); ++i) {
                JSONObject obj = calls.optJSONObject(i);
                Call lCall = objFromJson(obj);
                if (lCall != null) {
                    lCalls.add(lCall);
                }
            }
        } catch (Exception e) {
            LOG.severe(e.toString());
        }
        return lCalls;
    }

    private static Call objFromJson(JSONObject pObj) {
        JSONObject lObject = pObj.optJSONObject("call");
        if (lObject != null) {
            return fromJsonObject(lObject);
        }
        return null;
    }

    public static Call fromJsonObject(JSONObject callObject) {
        // Fix for some objects not having name.
        if (callObject.has(CALL_TAG)) {
            callObject = callObject.optJSONObject(CALL_TAG);
        }
        Call lCall = new Call();
        lCall.setId(callObject.optString(ID_TAG));
        lCall.setVia(callObject.optString(VIA_TAG));
        lCall.setAuthor(callObject.optString(AUTHOR_TAG));
        lCall.setPhoneNumber(callObject.optString(PHONE_NUMBER_TAG));
        lCall.setText(callObject.optString(TEXT_TAG));
        String callResultId = callObject.optString(CALL_RESULT_TAG);
        for (CallResult lCallResult : Account.loggedInUser.getAccount().getCallResults()) {
            if (lCallResult.getId().equals(callResultId)) {
                lCall.setCallResult(lCallResult);
            }
        }
        lCall.setTime(DateSerializer.fromNumString(callObject.optString(CALL_TIME_INT_TAG)));
        lCall.setContactId(callObject.optString(CONTACT_ID_TAG));
        lCall.setRecordingLink(callObject.optString(RECORDING_LINK_TAG));
        lCall.setCreatedAt(DateSerializer.fromCallFormattedString(callObject.optString(CREATED_AT_TAG)));
        lCall.setModifiedAt(DateSerializer.fromFormattedString(callObject.optString(MODIFIED_AT_TAG)));
        return lCall;
    }

    public static List<Call> fromJsonArray(JSONArray callsArray) {
        List<Call> calls = new LinkedList<>();
        for (int i = 0; i < callsArray.length(); ++i) {
            JSONObject obj = callsArray.optJSONObject(i);
            Call call = fromJsonObject(obj);
            if (call != null) {
                calls.add(call);
            }
        }
        return calls;
    }
}
