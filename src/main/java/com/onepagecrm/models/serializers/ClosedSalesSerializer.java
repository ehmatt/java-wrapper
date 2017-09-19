package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.CloseSalesCycle;
import com.onepagecrm.models.internal.SalesCycleClosure;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 15/06/2016.
 */
@SuppressWarnings("WeakerAccess")
public class ClosedSalesSerializer extends BaseSerializer {

    private static SalesCycleClosure DEFAULT = new SalesCycleClosure();

    public static SalesCycleClosure fromJsonObject(JSONObject closureObject, String contactId) {
        if (closureObject == null) {
            return DEFAULT;
        }
        return new SalesCycleClosure()
                .setUserId(closureObject.optString(USER_ID_TAG))
                .setClosedAt(DateSerializer.fromTimestamp(String.valueOf(closureObject.optInt(CLOSED_AT_TAG))))
                .setComment((closureObject.isNull(COMMENT_TAG) ? null : closureObject.optString(COMMENT_TAG)))
                .setContactId(contactId);
    }

    public static List<SalesCycleClosure> fromJsonArray(JSONArray closureArray, String contactId) {
        List<SalesCycleClosure> closures = new ArrayList<>();
        if (closureArray == null) return closures;
        for (int i = 0; i < closureArray.length(); i++) {
            JSONObject closureObject = closureArray.optJSONObject(i);
            SalesCycleClosure closure = fromJsonObject(closureObject, contactId);
            closures.add(closure);
        }
        return closures;
    }

    public static Map<String, SalesCycleClosure> mapFromJsonArray(JSONArray closureArray, String contactId) {
        Map<String, SalesCycleClosure> closures = new HashMap<>();
        if (closureArray == null) return closures;
        for (int i = 0; i < closureArray.length(); i++) {
            JSONObject closureObject = closureArray.optJSONObject(i);
            SalesCycleClosure closure = fromJsonObject(closureObject, contactId);
            closures.put(closure.getUserId(), closure);
        }
        return closures;
    }

    public static JSONObject toJsonObject(CloseSalesCycle closeSalesCycle) {
        JSONObject closeCycleObject = new JSONObject();
        if (closeSalesCycle == null) return closeCycleObject;
        addJsonStringValue(closeSalesCycle.getComment(), closeCycleObject, CLOSE_SALES_CYCLE_COMMENT_TAG);
        return closeCycleObject;
    }

    public static String toJsonString(CloseSalesCycle closeSalesCycle) {
        return toJsonObject(closeSalesCycle).toString();
    }
}
