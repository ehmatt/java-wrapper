package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.CallList;
import com.onepagecrm.models.internal.Paginator;
import com.onepagecrm.net.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 15/09/2016.
 */
public class CallListSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CallListSerializer.class.getName());

    private static CallList DEFAULT = new CallList();

    public static CallList fromResponse(Response response) throws APIException {
        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
        return fromJsonObject(dataObject);
    }

    // TODO - delete
    public static CallList fromString(String responseBody) throws APIException {
        CallList calls = new CallList();

        try {
            String dataString = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(dataString);
            JSONArray callsArray = responseObject.optJSONArray(CALLS_TAG);
            Paginator paginator = RequestMetadataSerializer.fromJsonObject(responseObject);
            calls.setPaginator(paginator);
            calls.setList(CallSerializer.fromJsonArray(callsArray));

        } catch (Exception e) {
            LOG.severe("Error parsing CallList from JSON.");
            LOG.severe(e.toString());
        }

        return calls;
    }

    public static CallList fromJsonObject(JSONObject dataObject) {
        if (dataObject == null) {
            return DEFAULT;
        }

        JSONArray callsArray = dataObject.optJSONArray(CALLS_TAG);
        CallList calls = fromJsonArray(callsArray);
        Paginator paginator = RequestMetadataSerializer.fromJsonObject(dataObject);
        calls.setPaginator(paginator);
        return calls;
    }

    public static CallList fromJsonArray(JSONArray callsArray) {
        return new CallList(CallSerializer.fromJsonArray(callsArray));
    }

    public static JSONArray toJsonArray(CallList calls) {
        return CallSerializer.toJsonArray(new CallList(calls));
    }

    public static String toJsonString(CallList calls) {
        return CallSerializer.toJsonString(calls.getList());
    }
}
