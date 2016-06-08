package com.onepagecrm;

import com.onepagecrm.models.Account;

public final class OnePageCRM {

    public static Account account;

    public static final String WRAPPER_JSON_PATH = "./src/test/res/responses/perfect/";
    public static String ASSET_PATH = WRAPPER_JSON_PATH;

    public static boolean DEBUG = false;

    public static void setDEBUG(boolean debug) {
        DEBUG = debug;
    }

    private OnePageCRM() {

    }
}
