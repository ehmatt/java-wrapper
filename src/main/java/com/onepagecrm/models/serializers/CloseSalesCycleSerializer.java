package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.CloseSalesCycle;
import org.json.JSONObject;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 18/09/2016.
 */
public class CloseSalesCycleSerializer extends BaseSerializer {

    public static String toJsonObject(CloseSalesCycle closeSalesCycle) {
        JSONObject closeCycleObject = new JSONObject();
        addJsonStringValue(closeSalesCycle.getComment(), closeCycleObject, CLOSE_SALES_CYCLE_COMMENT_TAG);
        return closeCycleObject.toString();
    }
}
