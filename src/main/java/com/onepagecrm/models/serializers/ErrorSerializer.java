package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.exceptions.AuthenticationException;
import com.onepagecrm.exceptions.BadRequestException;
import com.onepagecrm.exceptions.ForbiddenException;
import com.onepagecrm.exceptions.MethodNotAllowedException;
import com.onepagecrm.exceptions.PaymentRequiredException;
import com.onepagecrm.exceptions.PreconditionFailedException;
import com.onepagecrm.exceptions.ResourceNotFoundException;
import com.onepagecrm.exceptions.ServerErrorException;
import com.onepagecrm.exceptions.ServiceUnavailableException;
import com.onepagecrm.net.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

@SuppressWarnings("WeakerAccess")
public class ErrorSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ErrorSerializer.class.getName());

    public static APIException fromResponse(Response response) {
        final int responseCode = response.getResponseCode();
        final String responseBody = response.getResponseBody();

        APIException exception = fromHttpStatusCode(responseCode);
        exception.setResponse(response);

        try {
            JSONObject responseObject = new JSONObject(responseBody);
            exception.setStatus(responseObject.optInt(STATUS_TAG, responseCode));
            exception.setMessage(responseObject.optString(MESSAGE_TAG));
            exception.setErrorName(responseObject.optString(ERROR_NAME_TAG));
            exception.setErrorMessage(responseObject.optString(ERROR_MESSAGE_TAG));
            exception.setErrors(fromJsonObject(responseObject));

        } catch (JSONException e) {
            LOG.severe("Error parsing error JSON from response body");
            LOG.severe(e.toString());
            exception.setStatus(responseCode);
        }

        return exception;
    }

    // TODO - delete
    public static APIException fromString(String responseBody) {
        APIException exception = new APIException();

        try {
            JSONObject responseObject = new JSONObject(responseBody);
            int statusCode = responseObject.getInt(STATUS_TAG);
            exception = fromHttpStatusCode(statusCode)
                    .setStatus(responseObject.optInt(STATUS_TAG))
                    .setMessage(responseObject.optString(MESSAGE_TAG))
                    .setErrorName(responseObject.optString(ERROR_NAME_TAG))
                    .setErrorMessage(responseObject.optString(ERROR_MESSAGE_TAG))
                    .setErrors(fromJsonObject(responseObject));

        } catch (JSONException e) {
            LOG.severe("Error parsing error JSON from response body");
            LOG.severe(e.toString());
        }

        return exception;
    }

    public static APIException fromHttpStatusCode(int statusCode) {
        APIException exception = new APIException();
        switch (statusCode) {
            case 400:
                exception = new BadRequestException();
                break;
            case 401:
                exception = new AuthenticationException();
                break;
            case 402:
                exception = new PaymentRequiredException();
                break;
            case 403:
                exception = new ForbiddenException();
                break;
            case 404:
                exception = new ResourceNotFoundException();
                break;
            case 405:
                exception = new MethodNotAllowedException();
                break;
            case 409:
                exception = new PreconditionFailedException();
                break;
            case 500:
                exception = new ServerErrorException();
                break;
            case 503:
                exception = new ServiceUnavailableException();
                break;
        }
        return exception.setCode(statusCode);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> fromJsonObject(JSONObject errorObject) {
        Map<String, String> errorMessages = new HashMap<>();
        if (errorObject == null) return errorMessages;
        try {
            if (errorObject.has(ERRORS_TAG)) {
                JSONObject errorsObject = errorObject.getJSONObject(ERRORS_TAG);
                Iterator<String> iterator = errorsObject.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    String value = (String) errorsObject.get(key);
                    errorMessages.put(key, value);
                }
            }
        } catch (JSONException e) {
            LOG.severe("Failed to parse all error id's and messages.");
            LOG.severe(e.toString());
        }
        return errorMessages;
    }
}
