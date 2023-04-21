package org.booking.exceptions;

public class ValidateException extends RuntimeException {
    public ValidateException(String errorMessage) {
        super(String.format("Validate exception. %s", errorMessage));
    }
}
