package com.onepagecrm.models;

import java.util.HashMap;
import java.util.Map;

import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.PostRequest;

public class Call {

    private String callResult;
    private String note; // field name = 'text'

    public Call() {}

    public Call(String callResult, String note) {
        this.callResult = callResult;
        this.note = note;
    }
    
    public Response save(Contact contact) {
    	Map<String, String> params = new HashMap<>();
        params.put("call_result", callResult);
        params.put("text", note);
    	PostRequest saveRequest = new PostRequest("calls", params, "?contact_id=" + contact.getId());
    	Response response = saveRequest.send();
    	return response;
    }

    public String getCallResult() {
        return callResult;
    }

    public Call setCallResult(String callResult) {
        this.callResult = callResult;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Call setNote(String note) {
        this.note = note;
        return this;
    }
    

}
