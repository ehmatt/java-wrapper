package com.onepagecrm.models.internal;

import java.io.Serializable;
import java.util.List;

public class Settings implements Serializable {

    private Reminder reminder;
    private String timeZone;
    private String dateFormat;
    private Boolean militaryTime;
    private Integer listingSize;
    private String currency;
    private String currencySymbol;
    private List<Country> popularCountries;
    private List<DealStage> dealStages;
    private String defaultView;
    private Boolean showTidyStream;
    private Boolean showCompanyFieldsWithContact;
    private Boolean showCompanyDescription;
    private Boolean showCompanyPhone;
    private Boolean showCompanyURL;
    private Boolean showCompanyAddress;
    private Boolean contactTitleEnabled;
    private Boolean birthdaysEnabled;

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

    public Integer getListingSize() {
        return listingSize;
    }

    public Settings setListingSize(Integer listingSize) {
        this.listingSize = listingSize;
        return this;
    }

    public Boolean getMilitaryTime() {
        return militaryTime;
    }

    public boolean isMilitaryTimeEnabled() {
        return militaryTime != null && militaryTime;
    }

    public Settings setMilitaryTime(Boolean militaryTime) {
        this.militaryTime = militaryTime;
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

    public Boolean getShowCompanyFieldsWithContact() {
        return showCompanyFieldsWithContact;
    }

    public boolean shouldShowCompanyFieldsWithContact() {
        return showCompanyFieldsWithContact != null && showCompanyFieldsWithContact;
    }

    public Settings setShowCompanyFieldsWithContact(Boolean showCompanyFieldsWithContact) {
        this.showCompanyFieldsWithContact = showCompanyFieldsWithContact;
        return this;
    }

    public Boolean getShowCompanyAddress() {
        return showCompanyAddress;
    }

    public Boolean shouldShowCompanyAddress() {
        return showCompanyAddress != null && showCompanyAddress;
    }

    public Settings setShowCompanyAddress(Boolean showCompanyAddress) {
        this.showCompanyAddress = showCompanyAddress;
        return this;
    }

    public Boolean getShowCompanyDescription() {
        return showCompanyDescription;
    }

    public Boolean shouldShowCompanyDescription() {
        return showCompanyDescription != null && showCompanyDescription;
    }

    public Settings setShowCompanyDescription(Boolean showCompanyDescription) {
        this.showCompanyDescription = showCompanyDescription;
        return this;
    }

    public Boolean getShowCompanyPhone() {
        return showCompanyPhone;
    }

    public Boolean shouldShowCompanyPhone() {
        return showCompanyPhone != null && showCompanyPhone;
    }

    public Settings setShowCompanyPhone(Boolean showCompanyPhone) {
        this.showCompanyPhone = showCompanyPhone;
        return this;
    }

    public Boolean getShowCompanyURL() {
        return showCompanyURL;
    }

    public Boolean shouldShowCompanyURL() {
        return showCompanyURL != null && showCompanyURL;
    }

    public Settings setShowCompanyURL(Boolean showCompanyURL) {
        this.showCompanyURL = showCompanyURL;
        return this;
    }

    public Boolean getContactTitleEnabled() {
        return contactTitleEnabled;
    }

    public boolean isContactTitleEnabled() {
        return contactTitleEnabled != null && contactTitleEnabled;
    }

    public Settings setContactTitleEnabled(Boolean contactTitleEnabled) {
        this.contactTitleEnabled = contactTitleEnabled;
        return this;
    }

    public Boolean getBirthdaysEnabled() {
        return birthdaysEnabled;
    }

    public boolean areBirthdaysEnabled() {
        return birthdaysEnabled != null && birthdaysEnabled;
    }

    public Settings setBirthdaysEnabled(Boolean birthdaysEnabled) {
        this.birthdaysEnabled = birthdaysEnabled;
        return this;
    }
}