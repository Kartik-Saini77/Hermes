package com.hermes.ExceptionHandling;

public class HermesError extends Exception {
    private final int statusCode;

    public HermesError(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
