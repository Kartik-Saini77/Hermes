package com.hermes.ExceptionHandling;

public class HermesError extends Exception {
    private final int statusCode;
    public static int RESOURCE_NOT_FOUND = 4004;
    public static int BAD_REQUEST = 4000;
    public static int FORBIDDEN = 4003;

    public HermesError(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
    public static void AssertThrowBadRequest(boolean flag) throws HermesError {
        if (!flag) {
            throw new HermesError("BAD REQUEST", BAD_REQUEST);
        }
        return;
    }
    public int getStatusCode() {
        return statusCode;
    }
}
