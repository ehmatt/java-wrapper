package com.onepagecrm.models.serializer;

import com.onepagecrm.models.Call;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.logging.Logger;

public class CallSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CallSerializer.class.getName());

    public static String toJsonObject(Call call) {
        JSONObject callObject = new JSONObject();
        addJsonValue(call.getId(), callObject, ID_TAG);
        addJsonValue(CallResultSerializer.toJsonKeyValuePair(call.getCallResult()), callObject, CALL_RESULT_TAG);
        LOG.info(callObject.toString());
        return callObject.toString();
    }

    public static String toJsonArray(List<Call> calls) {
        JSONArray callsArray = new JSONArray();
        for (int i = 0; i < calls.size(); i++) {
            callsArray.put(toJsonObject(calls.get(i)));
        }
        return callsArray.toString();
    }
}
