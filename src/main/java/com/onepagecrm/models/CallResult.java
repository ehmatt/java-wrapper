package com.onepagecrm.models;

import java.io.Serializable;

public class CallResult implements Serializable {

    private static final long serialVersionUID = -3981577783870512717L;

    private int intId;
    private String stringId;
    private String result;

    public CallResult(int intId, String stringId, String result) {
        this.intId = intId;
        this.stringId = stringId;
        this.result = result;
    }

    public String toString() {
        return stringId + "=\'" + result + "\'";
    }

    public int getintId() {
        return intId;
    }

    public CallResult setIntId(int intId) {
        this.intId = intId;
        return this;
    }

    public String getStringId() {
        return stringId;
    }

    public CallResult setStringId(String stringId) {
        this.stringId = stringId;
        return this;
    }

    public String getResult() {
        return result;
    }

    public CallResult setResult(String result) {
        this.result = result;
        return this;
    }
}