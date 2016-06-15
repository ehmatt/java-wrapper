package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.SalesCycleClosure;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/06/2016.
 */
public class ClosedSalesSerializer extends BaseSerializer {

    public static SalesCycleClosure fromJsonObject(JSONObject closureObject, String contactId) {
        return new SalesCycleClosure()
                .setUserId(closureObject.optString(USER_ID_TAG))
                .setClosedAt(DateSerializer.fromTimestamp(String.valueOf(closureObject.optInt(CLOSED_AT_TAG))))
                .setComment(closureObject.optString(COMMENT_TAG))
                .setContactId(contactId);
    }

    public static List<SalesCycleClosure> fromJsonArray(JSONArray closureArray, String contactId) {
        List<SalesCycleClosure> closures = new LinkedList<>();
        for (int i = 0; i < closureArray.length(); i++) {
            JSONObject closureObject = closureArray.optJSONObject(i);
            SalesCycleClosure closure = fromJsonObject(closureObject, contactId);
            closures.add(closure);
        }
        return closures;
    }

    public static Map<String, SalesCycleClosure> mapFromJsonArray(JSONArray closureArray, String contactId) {
        Map<String, SalesCycleClosure> closures = new HashMap<>();
        for (int i = 0; i < closureArray.length(); i++) {
            JSONObject closureObject = closureArray.optJSONObject(i);
            SalesCycleClosure closure = fromJsonObject(closureObject, contactId);
            closures.put(closure.getUserId(), closure);
        }
        return closures;
    }
}
