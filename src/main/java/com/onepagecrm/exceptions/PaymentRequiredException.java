package com.onepagecrm.exceptions;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 31/08/2017.
 */
public class PaymentRequiredException extends APIException {

    public PaymentRequiredException(String message) {
        super(message);
    }

    public PaymentRequiredException() {
        super();
    }
}
