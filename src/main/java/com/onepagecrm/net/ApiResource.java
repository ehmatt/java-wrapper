package com.onepagecrm.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.onepagecrm.models.BaseResource;

public abstract class ApiResource extends BaseResource {

    public static final String CHARSET = "UTF-8";

    public static final String ACTION_STREAM_ENDPOINT = "action_stream";
    public static final String CONTACTS_ENDPOINT = "contacts";
    public static final String CALLS_ENDPOINT = "calls";

    @Override
    public abstract String toString();

    @Override
    public abstract String getId();

    public enum RequestMethod {
        GET, POST, DELETE, PUT, PATCH
    }

    public enum RequestType {
        NORMAL, MULTIPART
    }

    public static String urlEncode(String url) throws UnsupportedEncodingException {
        if (url == null) {
            return null;
        } else {
            return URLEncoder.encode(url, CHARSET);
        }
    }

    private static String className(Class<?> clazz) {
        return clazz.getSimpleName().toLowerCase().replace("$", " ");
    }
}
