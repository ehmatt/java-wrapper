package com.onepagecrm.models;

public class Query {

    public static String perPageQueryString(int number) {
        return "?per_page=" + number;
    }

    public static String teamValueQueryString(boolean value) {
        return "?team=" + value;
    }

    public static String contactIdQueryString(String contactId) {
        return "?contact_id=" + contactId;
    }

}
