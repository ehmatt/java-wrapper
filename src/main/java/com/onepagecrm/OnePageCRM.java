package com.onepagecrm;

import com.onepagecrm.models.Account;
import com.onepagecrm.net.request.Request;

@SuppressWarnings({"WeakerAccess", "unused"})
public final class OnePageCRM {

    public static Account account;
    private static OnePageCRM instance;

    public static final String WRAPPER_JSON_PATH = "./src/test/res/responses/perfect/";
    public static String ASSET_PATH = WRAPPER_JSON_PATH;

    public static boolean DEBUG = false;
    public static int SERVER = Request.APP_SERVER;
    public static String SOURCE = "java-wrapper";
    public static boolean COMPLEX_AUTH = false;

    public static OnePageCRM getInstance() {
        if (instance == null) {
            instance = new OnePageCRM();
        }
        return instance;
    }

    @SuppressWarnings("SameParameterValue")
    public static OnePageCRM setDEBUG(boolean debug) {
        DEBUG = debug;
        return getInstance();
    }

    public static OnePageCRM setServer(int server) {
        SERVER = server;
        return getInstance();
    }

    public static OnePageCRM setSource(String source) {
        SOURCE = source;
        return getInstance();
    }

    public static OnePageCRM setCustomUrl(String customUrl) {
        Request.setCustomUrl(customUrl);
        return getInstance();
    }

    public static OnePageCRM setComplexAuth(boolean complexAuth) {
        COMPLEX_AUTH = complexAuth;
        return getInstance();
    }
}
