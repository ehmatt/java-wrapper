package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.internal.Country;
import com.onepagecrm.models.serializer.CountrySerializer;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.GetRequest;
import com.onepagecrm.net.request.Request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Countries extends ArrayList<Country> implements Serializable {

    private static final String COUNTRIES_ENDPOINT = "countries";

    private List<Country> countries;

    public static Countries list() throws OnePageException {
        Request request = new GetRequest(COUNTRIES_ENDPOINT);
        Response response = request.send();
        return CountrySerializer.fromString(response.getResponseBody());
    }

    public Countries(List<Country> countries) {
        this.countries = new ArrayList<>();
        if (countries != null && !countries.isEmpty()) {
            for (int i = 0; i < countries.size(); i++) {
                this.countries.add(countries.get(i));
            }
        }
    }

    public Countries() {
        this.countries = new ArrayList<>();
    }

    public List<Country> getCountries() {
        return countries;
    }

    public Countries setCountries(List<Country> countries) {
        this.countries = countries;
        return this;
    }

    @Override
    public String toString() {
        return CountrySerializer.toJsonArray(this);
    }
}
