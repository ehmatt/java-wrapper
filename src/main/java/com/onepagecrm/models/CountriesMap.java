package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.internal.Country;
import com.onepagecrm.models.serializer.CountryMapSerializer;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.GetRequest;
import com.onepagecrm.net.request.Request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CountriesMap extends HashMap<String, Country> implements Serializable {

    private static final String COUNTRIES_ENDPOINT = "countries";

    private Map<String, Country> countries;

    public static CountriesMap list() throws OnePageException {
        Request request = new GetRequest(COUNTRIES_ENDPOINT);
        Response response = request.send();
        return CountryMapSerializer.fromString(response.getResponseBody());
    }

    public CountriesMap(Map<String, Country> countries) {
        this.countries = new HashMap<>();
        Iterator it = countries.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String countryCode = ((Country) pair.getKey()).getCode();
            Country country = (Country) pair.getValue();
            this.countries.put(countryCode, country);
            it.remove();
        }
    }

    public CountriesMap() {
        this.countries = new HashMap<>();
    }

    public boolean isEmpty() {
        return countries.isEmpty();
    }

    public int size() {
        return countries.size();
    }

    public void add(Country country) {
        countries.put(country.getCode(), country);
    }

    public Country get(String code) {
        return countries.get(code);
    }

    public Map<String, Country> getCountries() {
        return countries;
    }

    public java.util.Collection<Country> getCountriesList() {
        return countries.values();
    }

    public CountriesMap setCountries(Map<String, Country> countries) {
        this.countries = countries;
        return this;
    }

    @Override
    public String toString() {
        return CountryMapSerializer.toJsonArray(this);
    }
}
