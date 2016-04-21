package com.onepagecrm.models;

import com.onepagecrm.models.internal.Paginator;
import com.onepagecrm.models.serializers.BaseSerializer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Query {

    private static final Logger LOG = Logger.getLogger(Query.class.getSimpleName());

    public static String fromParams(Map<String, Object> params) {
        return "?" + BaseSerializer.encodeParams(params);
    }

    public static Map<String, Object> paramsDefault() {
        return params(new Paginator());
    }

    public static Map<String, Object> params(Paginator paginator) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("page", paginator.getCurrentPage());
        params.put("per_page", paginator.getPerPage());
        return params;
    }

    public static String queryDefault() {
        return fromParams(paramsDefault());
    }

    public static String query(Paginator paginator) {
        return fromParams(params(paginator));
    }

    public static String query(boolean team) {
        Map<String, Object> params = paramsDefault();
        params.put("team", team ? 1 : 0);
        return fromParams(params);
    }

    public static String query(Paginator paginator, boolean team) {
        Map<String, Object> params = params(paginator);
        params.put("team", team ? 1 : 0);
        return fromParams(params);
    }

    public static String querySearch(String search) {
        Map<String, Object> params = paramsDefault();
        params.put("search", search);
        return fromParams(params);
    }

    public static String querySearch(String search, boolean team) {
        Map<String, Object> params = paramsDefault();
        params.put("team", team ? 1 : 0);
        params.put("search", search);
        return fromParams(params);
    }

    public static String querySearch(Paginator paginator, String search) {
        Map<String, Object> params = params(paginator);
        params.put("search", search);
        return fromParams(params);
    }

    public static String querySearch(Paginator paginator, String search, boolean team) {
        Map<String, Object> params = params(paginator);
        params.put("team", team ? 1 : 0);
        params.put("search", search);
        return fromParams(params);
    }

    public static String queryLetter(String letter) {
        Map<String, Object> params = paramsDefault();
        params.put("letter", letter);
        return fromParams(params);
    }

    public static String queryLetter(String letter, boolean team) {
        Map<String, Object> params = paramsDefault();
        params.put("team", team ? 1 : 0);
        params.put("letter", letter);
        return fromParams(params);
    }

    public static String queryLetter(Paginator paginator, String letter) {
        Map<String, Object> params = params(paginator);
        params.put("letter", letter);
        return fromParams(params);
    }

    public static String queryLetter(Paginator paginator, String letter, boolean team) {
        Map<String, Object> params = params(paginator);
        params.put("team", team ? 1 : 0);
        params.put("letter", letter);
        return fromParams(params);
    }
}
