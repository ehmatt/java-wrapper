package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.DealStage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 4/23/16.
 */
public class DealStageSerializer extends BaseSerializer {

    public static List<DealStage> fromJsonArray(JSONArray dealStagesArray) {
        List<DealStage> dealStages = new LinkedList<>();
        if (dealStagesArray != null) {
            for (int i = 0; i < dealStagesArray.length(); ++i) {
                JSONObject dealObject = dealStagesArray.optJSONObject(i);
                DealStage dealStage = new DealStage();
                dealStage.setLabel(dealObject.optString(LABEL_TAG));
                dealStage.setPercentage(dealObject.optInt(STAGE_TAG));
                dealStages.add(dealStage);
            }
        }
        return dealStages;
    }
}
