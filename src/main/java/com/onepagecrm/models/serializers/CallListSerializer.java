package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.CallList;
import com.onepagecrm.models.internal.Paginator;
import com.onepagecrm.net.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/09/2016.
 */
public class CallListSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CallListSerializer.class.getName());

    public static CallList fromResponse(Response response) throws APIException {
        CallList calls = new CallList();
        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
        Paginator paginator = RequestMetadataSerializer.fromJsonObject(dataObject);
        JSONArray callsArray = dataObject.optJSONArray(CALLS_TAG);
        calls.setPaginator(paginator);
        calls.setList(CallSerializer.fromJsonArray(callsArray));
        return calls;
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
}
