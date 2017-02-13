package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.Country;
import com.onepagecrm.models.internal.Settings;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 4/23/16.
 */
public class SettingsSerializer extends BaseSerializer {

    public static Settings fromJsonObject(JSONObject settingsObject) {
        return new Settings()
                .setTimeZone(settingsObject.optString(TIME_ZONE_TAG))
                .setDateFormat(settingsObject.optString(DATE_FORMAT_TAG))
                .setMilitaryTime(!settingsObject.optBoolean(TIME_WITH_AMPM_TAG))
                .setListingSize(settingsObject.optInt(LISTING_SIZE_TAG))
                .setCurrency(settingsObject.optString(CURRENCY_TAG))
                .setCurrencySymbol(settingsObject.optString(CURRENCY_SYMBOL_TAG))
                .setPopularCountries(fromPopularCountryArray(settingsObject.optJSONArray(POPULAR_COUNTRIES_TAG)))
                .setDealStages(DealStageSerializer.fromJsonArray(settingsObject.optJSONArray(DEAL_STAGES_TAG)))
                .setDefaultView(settingsObject.optString(DEFAULT_CONTACT_TYPE_TAG))
                .setShowTidyStream(settingsObject.optBoolean(SHOW_TIDY_STREAM_TAG))
                .setShowCompanyFieldsWithContact(settingsObject.optBoolean(SHOW_COMPANY_FIELDS_WITH_CONTACT_TAG))
                .setShowCompanyAddress(settingsObject.optBoolean(SHOW_COMPANY_ADDRESS_TAG))
                .setShowCompanyDescription(settingsObject.optBoolean(SHOW_COMPANY_DESCRIPTION_TAG))
                .setShowCompanyPhone(settingsObject.optBoolean(SHOW_COMPANY_PHONE_TAG))
                .setShowCompanyURL(settingsObject.optBoolean(SHOW_COMPANY_URL_TAG));
    }

    private static List<Country> fromPopularCountryArray(JSONArray choicesArray) {
        List<Country> countries = new LinkedList<>();
        List<String> countryCodes = BaseSerializer.toListOfStrings(choicesArray);
        int lastIndex = countryCodes.size() - 1;
        for (int i = 0; i < countryCodes.size(); i++) {
            Country country = new Country()
                    .setCode(countryCodes.get(i))
                    .setPopularity(lastIndex);
            countries.add(i, country);
            lastIndex--;
        }
        return countries;
    }
}
