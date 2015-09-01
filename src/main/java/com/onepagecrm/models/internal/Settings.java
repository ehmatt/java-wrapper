package com.onepagecrm.models.internal;

import java.util.List;

import com.onepagecrm.models.CallResult;
import com.onepagecrm.models.CustomField;

public class Settings {


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