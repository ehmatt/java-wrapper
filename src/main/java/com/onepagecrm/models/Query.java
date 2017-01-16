package com.onepagecrm.models;

import com.onepagecrm.models.internal.Paginator;
import com.onepagecrm.models.serializers.BaseSerializer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@SuppressWarnings("WeakerAccess")
public class Query {

    private final static Logger LOG = Logger.getLogger(Query.class.getSimpleName());

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

    @SafeVarargs
    public static String fromMaps(Map<String, Object>... paramsArray) {
        List<String> values = new LinkedList<>();
        for (Map<String, Object> param : paramsArray) {
            for (String key : param.keySet()) {
                try {
                    String encodeKey = URLEncoder.encode(key, "UTF-8");
                    String encodeValue = URLEncoder.encode(String.valueOf(param.get(key)), "UTF-8");
                    values.add(String.format("%s=%s", encodeKey, encodeValue));
                } catch (UnsupportedEncodingException e) {
                    LOG.severe("Error encoding url params : " + param.toString());
                    LOG.severe(e.toString());
                }
            }
        }
        return "?" + join("&", values);
    }

    public static String join(CharSequence delimiter, List<String> tokens) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (String token : tokens) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(delimiter);
            }
            sb.append(token);
        }
        return sb.toString();
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

    public static String queryCompany(String companyId) {
        Map<String, Object> params = paramsDefault();
        params.put("company_id", companyId);
        return fromParams(params);
    }

    public static String queryCompany(Paginator paginator, String companyId) {
        Map<String, Object> params = params(paginator);
        params.put("company_id", companyId);
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
