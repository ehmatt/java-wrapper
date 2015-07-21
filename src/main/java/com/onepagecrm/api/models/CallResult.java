package com.onepagecrm.api.models;

import java.io.Serializable;


public class CallResult implements Serializable {

    private int intId;
    private String stringId;
    private String result;

    public CallResult(int intId, String stringId, String result) {
        this.intId = intId;
        this.stringId = stringId;
        this.result = result;
    }

    public String toString() {
        return stringId + "='" + result + "'";
    }

    public int getintId() {
        return intId;
    }

    public void setIntId(int intId) {
        this.intId = intId;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
