package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.Settings;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by alex on 4/23/16.
 */
public class SettingsSerializer extends BaseSerializer {
    public static Settings fromJsonObject(JSONObject pJSONObject) {
        Settings lSettings = new Settings();
        lSettings.setCurrency(pJSONObject.optString(CURRENCY_TAG));
        lSettings.setCurrencySymbol(pJSONObject.optString(CURRENCY_SYMBOL_TAG));
        JSONArray lDealStagesArray = pJSONObject.optJSONArray(DEAL_STAGES_TAG);

        lSettings.setDealStages(DealStageSerializer.fromJsonArray(lDealStagesArray));

        return lSettings;
    }
}
