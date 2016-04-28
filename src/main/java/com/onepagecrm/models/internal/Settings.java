package com.onepagecrm.models.internal;

import java.io.Serializable;
import java.util.List;

public class Settings implements Serializable {

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(Reminder pReminder) {
        reminder = pReminder;
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

    public void setDateFormat(String pDateFormat) {
        dateFormat = pDateFormat;
    }

    public int getListingSize() {
        return listingSize;
    }

    public void setListingSize(int pListingSize) {
        listingSize = pListingSize;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String pCurrency) {
        currency = pCurrency;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String pCurrencySymbol) {
        currencySymbol = pCurrencySymbol;
    }

    public List<Country> getPopularCountries() {
        return popularCountries;
    }

    public void setPopularCountries(List<Country> pPopularCountries) {
        popularCountries = pPopularCountries;
    }

    public List<DealStage> getDealStages() {
        return dealStages;
    }

    public void setDealStages(List<DealStage> pDealStages) {
        dealStages = pDealStages;
    }

    public String getDefaultView() {
        return defaultView;
    }

    public void setDefaultView(String pDefaultView) {
        defaultView = pDefaultView;
    }

    public boolean isShowTidyStream() {
        return showTidyStream;
    }

    public void setShowTidyStream(boolean pShowTidyStream) {
        showTidyStream = pShowTidyStream;
    }

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
}