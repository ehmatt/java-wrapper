package com.onepagecrm.api.models;

public class Call {

    private String callResult;
    private String note; // field name = 'text'

    public Call() {}

    public Call(String callResult, String note) {
        this.callResult = callResult;
        this.note = note;
    }

    public String getCallResult() {
        return callResult;
    }

    public void setCallResult(String callResult) {
        this.callResult = callResult;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
