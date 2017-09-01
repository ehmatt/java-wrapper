package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.Call;
import com.onepagecrm.models.CallResult;
import com.onepagecrm.net.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Cillian Myles (cillian@onepagecrm.com) on 31/07/2017.
 */
public class CallSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CallSerializer.class.getName());

    public static Call fromResponse(Response response) throws APIException {
        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
        return fromJsonObject(dataObject);
    }

    // TODO - delete
    public static Call fromString(String responseBody) throws APIException {
        Call call = new Call();
        try {
            String dataString = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(dataString);
            return fromJsonObject(responseObject);

        } catch (JSONException e) {
            LOG.severe("Error parsing Call from JSON.");
            LOG.severe(e.toString());
        }
        return call;
    }

    public static Call fromJsonObject(JSONObject callObject) {
        if (callObject == null) {
            return null;
        }
        // Fix for some objects not having name.
        if (callObject.has(CALL_TAG)) {
            callObject = callObject.optJSONObject(CALL_TAG);
        }
        // Get the result if exists.
        String callResultId = callObject.optString(CALL_RESULT_TAG);
        CallResult callResult = null;
        List<CallResult> callResults = CallResultSerializer.getFromLoggedUser();
        if (callResults != null && !callResults.isEmpty()) {
            for (CallResult result : callResults) {
                if (result.getId().equals(callResultId)) {
                    callResult = result;
                    break;
                }
            }
        }
        // If no exact matching id, create fresh object.
        if (callResult == null) {
            callResult = new CallResult()
                    .setId(callResultId)
                    .setText(callObject.optString(TEXT_TAG));
        }
        String phoneNumber =
                callObject.isNull(PHONE_NUMBER_TAG) ? null : callObject.optString(PHONE_NUMBER_TAG);
        // Get other fields.
        return new Call()
                .setCallResult(callResult)
                .setId(callObject.optString(ID_TAG))
                .setVia(callObject.optString(VIA_TAG))
                .setAuthor(callObject.optString(AUTHOR_TAG))
                .setPhoneNumber(phoneNumber)
                .setText(callObject.optString(TEXT_TAG))
                .setTime(DateSerializer.fromTimestamp(callObject.optString(CALL_TIME_INT_TAG)))
                .setContactId(callObject.optString(CONTACT_ID_TAG))
                .setRecordingLink(callObject.optString(RECORDING_LINK_TAG))
                .setCreatedAt(DateSerializer.fromFormattedString(callObject.optString(CREATED_AT_TAG)))
                .setModifiedAt(DateSerializer.fromFormattedString(callObject.optString(MODIFIED_AT_TAG)))
                .setAttachments(AttachmentSerializer.fromJsonArray(callObject.optJSONArray(ATTACHMENTS_TAG)));
    }

    public static List<Call> fromJsonArray(JSONArray callsArray) {
        List<Call> calls = new LinkedList<>();
        if (callsArray == null) return calls;
        for (int i = 0; i < callsArray.length(); ++i) {
            JSONObject callObject = callsArray.optJSONObject(i);
            Call call = fromJsonObject(callObject);
            if (call != null) {
                calls.add(call);
            }
        }
        return calls;
    }

    public static String toJsonObject(Call call) {
        JSONObject callObject = new JSONObject();
        addJsonStringValue(call.getId(), callObject, ID_TAG);
        addJsonStringValue(call.getText(), callObject, TEXT_TAG);
        addJsonStringValue(call.getCallResult().getId(), callObject, CALL_RESULT_TAG);
        addJsonLongValue(DateSerializer.toTimestamp(call.getTime()), callObject, CALL_TIME_INT_TAG);
        addJsonStringValue(call.getContactId(), callObject, CONTACT_ID_TAG);
        addJsonStringValue(DateSerializer.toFormattedDateTimeString(call.getCreatedAt()), callObject, CREATED_AT_TAG);
        addJsonStringValue(DateSerializer.toFormattedDateTimeString(call.getModifiedAt()), callObject, MODIFIED_AT_TAG);
        addJsonStringValue(call.getVia(), callObject, VIA_TAG);
        addJsonStringValue(call.getAuthor(), callObject, AUTHOR_TAG);
        addJsonStringValue(call.getPhoneNumber(), callObject, PHONE_NUMBER_TAG);
        return callObject.toString();
    }

    public static String toJsonArray(List<Call> calls) {
        JSONArray callsArray = new JSONArray();
        for (int i = 0; i < calls.size(); i++) {
            try {
                callsArray.put(new JSONObject(toJsonObject(calls.get(i))));
            } catch (JSONException e) {
                LOG.severe("Error creating JSON out of Call(s).");
                LOG.severe(e.toString());
            }
        }
        return callsArray.toString();
    }
}
