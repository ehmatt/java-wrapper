package com.onepagecrm.exceptions;

import com.onepagecrm.net.Response;

import java.util.Map;

/**
 * OnePageCRM HTTP response codes.
 * <p/>
 * 200 	OK - Operation successful.
 * <p/>
 * 400 	Bad Request. The request is not properly formatted, does not properly specify response format or contains
 * improper parameters or data.
 * <p/>
 * 401 	Unauthorised. Authorization data is absent, invalid or expired.
 * <p/>
 * 402 	Payment Required. Trial expired, subscription cancelled or payment declined etc.
 * <p/>
 * 403 	Forbidden. Issued when user is logged in, but does not have permission for requested operation.
 * <p/>
 * 404 	Resource Not Found. When an id was provided for a request but no resource exists for that object.
 * <p/>
 * 405 	Method Not Allowed. A request method is not supported for the requested resource; for example, a GET request on
 * a form that requires data to be presented via POST, or a PUT request on a read-only resource.
 * <p/>
 * 409 	Precondition Failed. Server state does not allow requested operation to be carried on. Issued in cases such as
 * when trying to add an assigned nextPage action to a contact which already has one assigned for a user.
 * <p/>
 * 500 	Internal Server Error. A generic error message, given when an unexpected condition was encountered and no more
 * specific message is suitable.
 * <p/>
 * 503 	Service Unavailable. The server is currently unavailable (because it is overloaded or down for maintenance).
 * <p/>
 *
 * @author Cillian Myles <cillian@onepagecrm.com> on 31/08/2017.
 */
@SuppressWarnings("WeakerAccess")
public class APIException extends OnePageException {

    public static final int TEAPOT_CODE = 418;

    private Response response;
    private int code; // HTTP code
    private int status; // API status no.
    private String errorName;
    private Map<String, String> errors;

    public APIException(String message) {
        super(message);
    }

    public APIException() {
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public APIException setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    @Override
    public String getErrorMessage() {
        return super.getErrorMessage();
    }

    @Override
    public APIException setErrorMessage(String errorMessage) {
        super.setErrorMessage(errorMessage);
        return this;
    }

    public APIException setResponse(Response response) {
        this.response = response;
        return this;
    }

    public int getResponseCode() {
        return response != null ? response.getResponseCode() : TEAPOT_CODE;
    }

    public String getResponseMessage() {
        return response != null ? response.getResponseMessage() : "";
    }

    public String getResponseBody() {
        return response != null ? response.getResponseBody() : "";
    }

    public String getErrorName() {
        return errorName;
    }

    public APIException setErrorName(String errorName) {
        this.errorName = errorName;
        return this;
    }

    public int getCode() {
        return code;
    }

    public APIException setCode(int code) {
        this.code = code;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public APIException setStatus(int status) {
        this.status = status;
        return this;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public APIException setErrors(Map<String, String> errors) {
        this.errors = errors;
        return this;
    }
}
