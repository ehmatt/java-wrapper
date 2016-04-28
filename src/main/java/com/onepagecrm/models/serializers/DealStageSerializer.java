package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.DealStage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 4/23/16.
 */
public class DealStageSerializer extends BaseSerializer{


    public static List<DealStage> fromJsonArray(JSONArray pDealStagesArray) {
        List<DealStage> lDealStages = new LinkedList<>();
        if (pDealStagesArray!=null){
            for (int i=0;i<pDealStagesArray.length();++i){
                JSONObject lObject = pDealStagesArray.optJSONObject(i);
                DealStage lDealStage = new DealStage();
                lDealStage.setLabel(lObject.optString(DEAL_STAGE_LABEL_TAG));
                lDealStage.setPercentage(lObject.optInt(DEAL_STAGE_STAGE_TAG));
                lDealStages.add(lDealStage);
            }
        }
        return lDealStages;
    }
}
