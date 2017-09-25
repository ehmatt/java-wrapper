package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.Countries;
import com.onepagecrm.models.internal.Country;
import com.onepagecrm.net.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 25/09/2017.
 */
public class CountrySerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CountrySerializer.class.getName());

    private static Country DEFAULT = new Country();

    public static Countries fromResponse(Response response) throws APIException {
        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
        JSONArray countriesArray = dataObject.optJSONArray(COUNTRIES_TAG);
        return fromJsonArray(countriesArray);
    }

    // TODO: delete
    public static Countries fromString(String responseBody) throws APIException {
        try {
            String dataString = (String) BaseSerializer.fromString(responseBody);
            JSONObject dataObject = new JSONObject(dataString);
            JSONArray countriesArray = dataObject.getJSONArray(COUNTRIES_TAG);
            return fromJsonArray(countriesArray);

        } catch (JSONException e) {
            LOG.severe("Could not find Country array");
            LOG.severe(e.toString());
        }
        return new Countries();
    }

    public static Country fromJsonObject(JSONObject countryObject) {
        if (countryObject == null) {
            return DEFAULT;
        }
        if (countryObject.has(COUNTRY_TAG)) {
            countryObject = countryObject.optJSONObject(COUNTRY_TAG);
        }
        return new Country()
                .setName(countryObject.optString(NAME_TAG))
                .setCode(countryObject.optString(CODE_TAG))
                .setPrefix(countryObject.optString(PHONE_PREFIX_TAG))
                .setCurrency(countryObject.optString(CURRENCY_TAG));
    }

    public static Countries fromJsonArray(JSONArray countryArray) {
        List<Country> countries = new ArrayList<>();
        if (countryArray == null) return new Countries();
        for (int j = 0; j < countryArray.length(); j++) {
            JSONObject countryObject = countryArray.optJSONObject(j);
            countries.add(fromJsonObject(countryObject));
        }
        return new Countries(countries);
    }

    public static JSONObject toJsonObject(Country country) {
        JSONObject countryObject = new JSONObject();
        if (country == null) return countryObject;
        addJsonStringValue(country.getName(), countryObject, NAME_TAG);
        addJsonStringValue(country.getCode(), countryObject, CODE_TAG);
        addJsonStringValue(country.getPrefix(), countryObject, PHONE_PREFIX_TAG);
        addJsonStringValue(country.getCurrency(), countryObject, CURRENCY_TAG);
        addJsonIntegerValue(country.getPopularity(), countryObject, POPULARITY_TAG);
        return countryObject;
    }

    public static JSONArray toJsonArray(List<Country> countries) {
        JSONArray countryArray = new JSONArray();
        if (countries == null) return countryArray;
        for (Country country : countries) {
            countryArray.put(toJsonObject(country));
        }
        return countryArray;
    }

    public static String toJsonString(Country country) {
        return toJsonObject(country).toString();
    }

    public static String toJsonString(List<Country> countries) {
        return toJsonArray(countries).toString();
    }
}
