package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.internal.Country;
import com.onepagecrm.models.serializers.CountrySerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.GetRequest;
import com.onepagecrm.net.request.Request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Countries extends ArrayList<Country> implements Serializable {

    private static final String COUNTRIES_ENDPOINT = ApiResource.COUNTRIES_ENDPOINT;

    private List<Country> countryList;
    private Map<String, Country> countryMap;

    public static Countries list() throws OnePageException {
        Request request = new GetRequest(COUNTRIES_ENDPOINT);
        Response response = request.send();
        return CountrySerializer.fromResponse(response);
    }

    public Countries(List<Country> countryList) {
        initLists();
        setCountriesListInt(countryList);
    }

    public Countries(Map<String, Country> countryMap) {
        initLists();
        setCountriesMapInt(countryMap);
    }

    public Countries() {
        initLists();
    }

    private void initLists() {
        this.countryList = new ArrayList<>();
        this.countryMap = new HashMap<>();
    }

    private void setCountriesListInt(List<Country> countryList) {
        if (countryList == null) return;
        for (Country country : countryList) {
            this.countryList.add(country);
            this.countryMap.put(country.getCode(), country);
        }
    }

    private void setCountriesMapInt(Map<String, Country> countryMap) {
        if (countryMap == null) return;
        Iterator it = countryMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String countryCode = ((Country) pair.getKey()).getCode();
            Country country = (Country) pair.getValue();
            this.countryMap.put(countryCode, country);
            this.countryList.add(country);
            it.remove();
        }
    }

    @Override
    public String toString() {
        return CountrySerializer.toJsonString(this);
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public Countries setCountryList(List<Country> countryList) {
        initLists();
        setCountriesListInt(countryList);
        return this;
    }

    public Map<String, Country> getCountryMap() {
        return countryMap;
    }

    public Countries setCountryMap(Map<String, Country> countryMap) {
        initLists();
        setCountriesMapInt(countryMap);
        return this;
    }

    public boolean isEmpty() {
        return countryList.isEmpty() && countryMap.isEmpty();
    }

    public int size() {
        return countryList.size();
    }

    public void add(int index, Country country) {
        countryList.add(index, country);
        countryMap.put(country.getCode(), country);
    }

    public Country get(int index) {
        return countryList.get(index);
    }

    public Country get(String code) {
        return countryMap.get(code);
    }
}
