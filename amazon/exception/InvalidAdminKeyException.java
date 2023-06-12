package com.amazon.exception;

/**
 * Exception thrown when an invalid admin key is provided.
 * Exception is used to indicate that the input provided .
 */
public class InvalidAdminKeyException extends Exception {

    public InvalidAdminKeyException(final String exceptionMessage) {
        super(exceptionMessage);
    }
}
