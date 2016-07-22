package com.onepagecrm.models.internal;

import com.onepagecrm.models.serializers.CountrySerializer;

import java.io.Serializable;

public class Country implements Serializable {

    private String name;
    private String code;
    private String currency;
    private String prefix;
    private Integer popularity;

    public Country() {

    }

    @Override
    public String toString() {
        return CountrySerializer.toJsonObject(this);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Country) {
            Country toBeCompared = (Country) object;
            if (this.getCode() != null && toBeCompared.getCode() != null) {
                return this.getCode().equals(toBeCompared.getCode());
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Country setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public Country setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public Country setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public Country setPopularity(Integer popularity) {
        this.popularity = popularity;
        return this;
    }
}
