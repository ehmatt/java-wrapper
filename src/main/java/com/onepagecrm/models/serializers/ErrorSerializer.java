package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.AuthenticationException;
import com.onepagecrm.exceptions.BadRequestException;
import com.onepagecrm.exceptions.ForbiddenException;
import com.onepagecrm.exceptions.MethodNotAllowedException;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.exceptions.PaymentRequiredException;
import com.onepagecrm.exceptions.PreconditionFailedException;
import com.onepagecrm.exceptions.ResourceNotFoundException;
import com.onepagecrm.exceptions.ServerErrorException;
import com.onepagecrm.exceptions.ServiceUnavailableException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

public class ErrorSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ErrorSerializer.class.getName());

    public static OnePageException fromString(String responseBody) {
        OnePageException exception = new OnePageException();
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            int status = responseObject.getInt(STATUS_TAG);
            switch (status) {
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
            exception
                    .setErrorName(responseObject.getString(ERROR_NAME_TAG))
                    .setStatus(responseObject.getInt(STATUS_TAG))
                    .setMessage(responseObject.getString(MESSAGE_TAG))
                    .setErrorMessage(responseObject.getString(ERROR_MESSAGE_TAG))
                    .setErrors(fromJsonObject(responseObject));

        } catch (JSONException e) {
            LOG.severe("Error parsing error tags from response body");
            LOG.severe(e.toString());
        }
        return exception;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> fromJsonObject(JSONObject errorObject) {
        Map<String, String> errorMessages = new HashMap<>();
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
