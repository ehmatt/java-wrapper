package com.onepagecrm.models.serializer;

import com.onepagecrm.models.Deal;
import com.onepagecrm.models.DealList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * Created by Cillian Myles (cillian@onepagecrm.com) on 11/24/15.
 */
public class DealSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(DealSerializer.class.getName());

    public static Deal fromJsonObject(JSONObject dealsObject) {
        Deal deal = new Deal();
        try {
            JSONObject dealObject = dealsObject.getJSONObject(DEAL_TAG);
            if (dealObject.has(ID_TAG)) {
                deal.setId(dealObject.getString(ID_TAG));
            }
            if (dealObject.has(OWNER_ID_TAG)) {
                deal.setOwnerId(dealObject.getString(OWNER_ID_TAG));
            }
            // TODO : add rest of stuff for deals.
        } catch (JSONException e) {
            LOG.severe("Error parsing Deal object");
            LOG.severe(e.toString());
        }
        return deal;
    }

    public static String toJsonObject(Deal deal) {
        JSONObject dealObject = new JSONObject();
        addJsonStringValue(deal.getId(), dealObject, ID_TAG);
        addJsonStringValue(deal.getOwnerId(), dealObject, OWNER_ID_TAG);
        // TODO : add rest of stuff for deals.
        return dealObject.toString();
    }

    public static String toJsonArray(DealList deals) {
        JSONArray dealsArray = new JSONArray();
        if (deals != null && !deals.isEmpty()) {
            for (int i = 0; i < deals.size(); i++) {
                try {
                    dealsArray.put(new JSONObject(toJsonObject(deals.get(i))));
                } catch (JSONException e) {
                    LOG.severe("Error creating JSONArray out of Deals");
                    LOG.severe(e.toString());
                }
            }
        }
        return dealsArray.toString();
    }
}
