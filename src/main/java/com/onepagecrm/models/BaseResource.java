package com.onepagecrm.models;


public abstract class BaseResource {

    @Override
    public abstract String toString();

    public abstract String getId();

    protected static boolean equals(Object a, Object b) {
        return a == null ? b == null : a.equals(b);
    }
}