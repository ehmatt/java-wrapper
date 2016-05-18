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
        LinkedList<DealStage> lDealStages = new LinkedList<>();
        if (dealStagesArray != null) {
            for (int i = 0; i < dealStagesArray.length(); ++i) {
                JSONObject lObject = dealStagesArray.optJSONObject(i);
                DealStage lDealStage = new DealStage();
                lDealStage.setLabel(lObject.optString(LABEL_TAG));
                lDealStage.setPercentage(lObject.optInt(STAGE_TAG));
                lDealStages.add(lDealStage);
            }
        }
        return DealStage.addDefaults(lDealStages);
    }
}
