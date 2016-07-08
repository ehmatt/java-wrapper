package com.onepagecrm;

import com.onepagecrm.models.Account;
import com.onepagecrm.net.request.Request;

public final class OnePageCRM {

    public static Account account;
    private static OnePageCRM instance;

    public static final String WRAPPER_JSON_PATH = "./src/test/res/responses/perfect/";
    public static String ASSET_PATH = WRAPPER_JSON_PATH;

    public static boolean DEBUG = false;
    public static int SERVER = Request.APP_SERVER;
    public static String SOURCE = "java-wrapper";

    public static OnePageCRM getInstance() {
        if (instance == null) {
            instance = new OnePageCRM();
        }
        return instance;
    }

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
}
