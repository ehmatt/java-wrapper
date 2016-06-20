package com.onepagecrm.models.internal;

import java.io.Serializable;
import java.util.List;

public class Settings implements Serializable {

    private Reminder reminder;
    private String timeZone; // *****************
    private String dateFormat; // *****************
    private int listingSize;
    private String currency; // *****************
    private String currencySymbol;
    private List<Country> popularCountries; // *****************
    private List<DealStage> dealStages;
    private String defaultView; // *****************
    private Boolean showTidyStream; // *****************

    public Settings() {

    }

    public Reminder getReminder() {
        return reminder;
    }

    public Settings setReminder(Reminder reminder) {
        this.reminder = reminder;
        return this;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public Settings setTimeZone(String pTimeZone) {
        timeZone = pTimeZone;
        return this;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public Settings setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    public int getListingSize() {
        return listingSize;
    }

    public Settings setListingSize(int listingSize) {
        this.listingSize = listingSize;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public Settings setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public Settings setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
        return this;
    }

    public List<Country> getPopularCountries() {
        return popularCountries;
    }

    public Settings setPopularCountries(List<Country> popularCountries) {
        this.popularCountries = popularCountries;
        return this;
    }

    public List<DealStage> getDealStages() {
        return dealStages;
    }

    public Settings setDealStages(List<DealStage> dealStages) {
        this.dealStages = dealStages;
        return this;
    }

    public String getDefaultView() {
        return defaultView;
    }

    public Settings setDefaultView(String defaultView) {
        this.defaultView = defaultView;
        return this;
    }

    public Boolean getShowTidyStream() {
        return showTidyStream;
    }

    public boolean shouldShowTidyStream() {
        return showTidyStream != null && showTidyStream;
    }

    public Settings setShowTidyStream(Boolean showTidyStream) {
        this.showTidyStream = showTidyStream;
        return this;
    }
}