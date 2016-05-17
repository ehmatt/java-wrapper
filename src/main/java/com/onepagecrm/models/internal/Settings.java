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
    private boolean showTidyStream; // *****************

    public Settings() {

    }

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String pTimeZone) {
        timeZone = pTimeZone;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public int getListingSize() {
        return listingSize;
    }

    public void setListingSize(int listingSize) {
        this.listingSize = listingSize;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public List<Country> getPopularCountries() {
        return popularCountries;
    }

    public void setPopularCountries(List<Country> popularCountries) {
        this.popularCountries = popularCountries;
    }

    public List<DealStage> getDealStages() {
        return dealStages;
    }

    public void setDealStages(List<DealStage> dealStages) {
        this.dealStages = dealStages;
    }

    public String getDefaultView() {
        return defaultView;
    }

    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }

    public boolean isShowTidyStream() {
        return showTidyStream;
    }

    public void setShowTidyStream(boolean showTidyStream) {
        this.showTidyStream = showTidyStream;
    }
}