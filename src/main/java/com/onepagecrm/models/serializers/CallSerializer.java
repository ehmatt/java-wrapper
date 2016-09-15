package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Account;
import com.onepagecrm.models.Call;
import com.onepagecrm.models.CallList;
import com.onepagecrm.models.CallResult;
import com.onepagecrm.models.internal.Paginator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class CallSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CallSerializer.class.getName());

    public static Call fromString(String responseBody) throws OnePageException {
        OnePageException exception;

        try {
            JSONObject responseObject = new JSONObject(responseBody);
            JSONObject dataObject = responseObject.optJSONObject(DATA_TAG);
            return fromJsonObject(dataObject);

        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;

        } catch (JSONException e) {
            LOG.severe("Error parsing Call from JSON.");
            LOG.severe(e.toString());
        }

        return new Call();
    }

    public static CallList listFromString(String responseBody) throws OnePageException {
        CallList calls = new CallList();
        OnePageException exception;

        try {
            JSONObject responseObject = new JSONObject(responseBody);
            JSONObject dataObject = responseObject.optJSONObject(DATA_TAG);
            JSONArray callsArray = dataObject.optJSONArray(CALLS_TAG);
            Paginator paginator = RequestMetadataSerializer.fromJsonObject(dataObject);
            calls.setPaginator(paginator);
            calls.setList(fromJsonArray(callsArray));

        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;

        } catch (Exception e) {
            LOG.severe("Error parsing CallList from JSON.");
            LOG.severe(e.toString());
        }

        return calls;
    }

    public static Call fromJsonObject(JSONObject callObject) {
        // Fix for some objects not having name.
        if (callObject.has(CALL_TAG)) {
            callObject = callObject.optJSONObject(CALL_TAG);
        }
        // Get the result if exists.
        String callResultId = callObject.optString(CALL_RESULT_TAG);
        CallResult callResult = null;
        for (CallResult result : Account.loggedInUser.getAccount().getCallResults()) {
            if (result.getId().equals(callResultId)) {
                callResult = result;
                break;
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
                .setModifiedAt(DateSerializer.fromFormattedString(callObject.optString(MODIFIED_AT_TAG)));
    }

    public static List<Call> fromJsonArray(JSONArray callsArray) {
        List<Call> calls = new LinkedList<>();
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
