package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.DealList;
import com.onepagecrm.models.internal.Paginator;
import com.onepagecrm.net.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 24/11/2015.
 */
public class DealListSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(DealListSerializer.class.getName());

    private static DealList DEFAULT = new DealList();

    public static DealList fromResponse(Response response) throws APIException {
        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
        return fromJsonObject(dataObject);
    }

    // TODO: delete me
    public static DealList fromString(String responseBody) throws APIException {
        DealList deals = new DealList();

        try {
            String dataString = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(dataString);
            JSONArray dealsArray = responseObject.getJSONArray(DEALS_TAG);
            deals = fromJsonArray(dealsArray);
            Paginator paginator = RequestMetadataSerializer.fromJsonObject(responseObject);
            deals.setPaginator(paginator);

        } catch (JSONException e) {
            LOG.severe("Error parsing deals array from response body");
            LOG.severe(e.toString());
        }

        return deals;
    }

    public static DealList fromJsonObject(JSONObject dataObject) {
        if (dataObject == null) {
            return DEFAULT;
        }

        JSONArray dealsArray = dataObject.optJSONArray(DEALS_TAG);
        DealList deals = fromJsonArray(dealsArray);
        Paginator paginator = RequestMetadataSerializer.fromJsonObject(dataObject);
        deals.setPaginator(paginator);
        return deals;
    }

    public static DealList fromJsonArray(JSONArray dealsArray) {
        return new DealList(DealSerializer.fromJsonArray(dealsArray));
    }

    public static JSONArray toJsonArray(DealList deals) {
        return DealSerializer.toJsonArray(deals);
    }

    public static String toJsonString(DealList deals) {
        return DealSerializer.toJsonString(deals);
    }
}
