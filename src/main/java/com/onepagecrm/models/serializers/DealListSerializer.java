package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Deal;
import com.onepagecrm.models.DealList;
import com.onepagecrm.models.internal.Paginator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Cillian Myles (cillian@onepagecrm.com) on 11/24/15.
 */
public class DealListSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(DealListSerializer.class.getName());

    /**
     * Parse response from deals request to construct Deal object(s).
     *
     * @param responseBody
     * @return
     */
    public static DealList fromString(String responseBody) throws OnePageException {
        DealList deals = new DealList();

        try {
            String parsedResponse = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(parsedResponse);
            JSONArray dealsArray = responseObject.getJSONArray(DEALS_TAG);
            deals = fromJsonArray(dealsArray);
            Paginator paginator = RequestMetadataSerializer.fromJsonObject(responseObject);
            deals.setPaginator(paginator);

        } catch (ClassCastException e) {
            throw (OnePageException) BaseSerializer.fromString(responseBody);

        } catch (JSONException e) {
            LOG.severe("Error parsing deals array from response body");
            LOG.severe(e.toString());
        }

        return deals;
    }

    /**
     * Parse response from deals request to construct Deal object(s).
     *
     * @param dealsArray
     * @return
     */
    public static DealList fromJsonArray(JSONArray dealsArray) {
        List<Deal> deals = new ArrayList<>();
        try {
            for (int i = 0; i < dealsArray.length(); i++) {
                JSONObject dealObject = dealsArray.getJSONObject(i);
                deals.add(DealSerializer.fromJsonObject(dealObject));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing deals array from response body");
            LOG.severe(e.toString());
        }
        return new DealList(deals);
    }

    public static DealList fromJsonObject(JSONObject dealsObject) {
        DealList deals = new DealList();
        try {
            JSONArray dealsArray = dealsObject.getJSONArray(DEALS_TAG);
            deals = fromJsonArray(dealsArray);
            Paginator paginator = RequestMetadataSerializer.fromJsonObject(dealsObject);
            deals.setPaginator(paginator);

        } catch (JSONException e) {
            LOG.severe("Error parsing Deals array from Deals object");
            LOG.severe(e.toString());
        }
        return deals;
    }
}
