package com.onepagecrm.exceptions;

import java.util.Map;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 04/10/2017.
 */
public class S3UploadException extends OnePageException {

    public S3UploadException(String message) {
        super(message);
    }

    public S3UploadException() {

    }

    @Override
    public String getErrorName() {
        return super.getErrorName();
    }

    @Override
    public S3UploadException setErrorName(String errorName) {
        super.setErrorName(errorName);
        return this;
    }

    @Override
    public int getStatus() {
        return super.getStatus();
    }

    @Override
    public S3UploadException setStatus(int status) {
        super.setStatus(status);
        return this;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public S3UploadException setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    @Override
    public String getErrorMessage() {
        return super.getErrorMessage();
    }

    @Override
    public S3UploadException setErrorMessage(String errorMessage) {
        super.setErrorMessage(errorMessage);
        return this;
    }

    @Override
    public Map<String, String> getErrors() {
        return super.getErrors();
    }

    @Override
    public S3UploadException setErrors(Map<String, String> errors) {
        super.setErrors(errors);
        return this;
    }
}
