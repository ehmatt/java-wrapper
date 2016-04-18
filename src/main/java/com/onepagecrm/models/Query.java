package com.onepagecrm.models;

import com.onepagecrm.models.internal.Paginator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Query {

    public static String paginatorToString(Paginator paginator) {
        return "?page=" + paginator.getCurrentPage() +
                "&per_page=" + paginator.getPerPage();
    }

    public static Map<String, Object> paginatorToMap(Paginator paginator) {
        Map<String, Object> p = new HashMap<String, Object>();
        p.put("page", paginator.getCurrentPage());
        p.put("per_page", paginator.getPerPage());
        return p;
    }

    public static String perPageQueryString(int number) {
        return "?per_page=" + number;
    }

    public static String teamValueQueryString(boolean value) {
        return "?team=" + value;
    }

    public static String contactIdQueryString(String contactId) {
        return "?contact_id=" + contactId;
    }

    public static String mapsToString(Map<String, Object>... pParams) {

        List<String> vals = new LinkedList<>();
        for (Map<String, Object> param : pParams) {
            for (String key : param.keySet()) {
                vals.add(String.format("%s=%s", key, param.get(key)));
            }
        }
        return "?" + join("&",vals);
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
}
