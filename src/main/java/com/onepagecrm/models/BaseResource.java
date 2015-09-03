package com.onepagecrm.models;

public abstract class BaseResource {

    public abstract String getId();

    @Override
    public abstract String toString();

    protected static boolean equals(Object a, Object b) {
        return a == null ? b == null : a.equals(b);
    }
}
