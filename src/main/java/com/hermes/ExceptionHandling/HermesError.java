package com.hermes.ExceptionHandling;

public class HermesError extends RuntimeException {
    private final int statusCode;
    public static final int RESOURCE_NOT_FOUND = 4004;
    public static final int BAD_REQUEST = 4000;
    public static final int FORBIDDEN = 4003;
    public static final int INTERNAL_SERVER_ERROR = 5000;

    public HermesError(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
    public static void AssertThrowBadRequest(boolean flag) {
        if (!flag) {
            throw new HermesError("BAD REQUEST", BAD_REQUEST);
        }
    }

    public int getStatusCode() {
        return statusCode;
    }
}
