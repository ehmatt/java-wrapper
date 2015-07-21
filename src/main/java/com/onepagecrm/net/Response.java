package com.onepagecrm.net;


public class Response {

    private int responseCode;
    private String responseMessage;
    private String responseBody;

    public Response(int responseCode, String responseMessage, String responseBody) {
        this.setResponseCode(responseCode);
        this.setResponseMessage(responseMessage);
        this.setResponseBody(responseBody);
    }

    public String getResponseMessage() throws NullPointerException {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseBody() throws NullPointerException {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public int getResponseCode() throws NullPointerException {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}