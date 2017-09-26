package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.DealStage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 26/09/2017.
 */
public class DealStageSerializer extends BaseSerializer {

    private static DealStage DEFAULT = new DealStage();

    public static DealStage fromJsonObject(JSONObject dealStageObject) {
        if (dealStageObject == null) {
            return DEFAULT;
        }
        return new DealStage()
                .setLabel(dealStageObject.optString(LABEL_TAG))
                .setPercentage(dealStageObject.optInt(STAGE_TAG));
    }

    public static List<DealStage> fromJsonArray(JSONArray dealStagesArray) {
        List<DealStage> dealStages = new LinkedList<>();
        if (dealStagesArray == null) return dealStages;
        for (int i = 0; i < dealStagesArray.length(); i++) {
            JSONObject dealStageObject = dealStagesArray.optJSONObject(i);
            dealStages.add(fromJsonObject(dealStageObject));
        }
        return dealStages;
    }
}
