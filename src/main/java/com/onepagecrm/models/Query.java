package com.onepagecrm.models;

import com.onepagecrm.models.internal.Paginator;
import com.onepagecrm.models.serializers.BaseSerializer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Logger;

import static com.onepagecrm.models.internal.Utilities.notNullOrEmpty;

public class Query {

    private static final Logger LOG = Logger.getLogger(Query.class.getSimpleName());

    public static String fromParams(Map<String, Object> params) {
        return "?" + BaseSerializer.encodeParams(params);
    }

    public static String paginatorToString(Paginator paginator) {
        return "?page=" + paginator.getCurrentPage() + "&per_page=" + paginator.getPerPage();
    }

    public static String searchWithPaginator(Paginator paginator, String search) {
        return paginatorToString(paginator) + "&search=" + encode(search);
    }

    public static String letterWithPaginator(Paginator paginator, String letter) {
        return paginatorToString(paginator) + "&letter=" + encode(letter);
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

    private static String encode(String query) {
        if (notNullOrEmpty(query)) {
            try {
                return URLEncoder.encode(query, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                LOG.severe("Could not encode query.\n" + e);
            }
        }
        return query;
    }
}
