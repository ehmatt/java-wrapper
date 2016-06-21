package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.Settings;
import org.json.JSONObject;

/**
 * Created by alex on 4/23/16.
 */
public class SettingsSerializer extends BaseSerializer {

    public static Settings fromJsonObject(JSONObject settingsObject) {
        return new Settings()
                .setTimeZone(settingsObject.optString(TIME_ZONE_TAG))
                .setDateFormat(settingsObject.optString(DATE_FORMAT_TAG))
                .setListingSize(settingsObject.optInt(LISTING_SIZE_TAG))
                .setCurrency(settingsObject.optString(CURRENCY_TAG))
                .setCurrencySymbol(settingsObject.optString(CURRENCY_SYMBOL_TAG))
//                .setPopularCountries(CountrySerializer.fromJsonArray(POPULAR_COUNTRIES_TAG))
                .setDealStages(DealStageSerializer.fromJsonArray(settingsObject.optJSONArray(DEAL_STAGES_TAG)))
                .setDefaultView(settingsObject.optString(DEFAULT_CONTACT_TYPE_TAG))
                .setShowTidyStream(settingsObject.optBoolean(SHOW_TIDY_STREAM_TAG));
    }
}
