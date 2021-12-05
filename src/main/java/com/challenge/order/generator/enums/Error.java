package com.challenge.order.generator.enums;

public enum Error {

    // 400 Error

    /**
     * Invalid input parameters
     */
    INVALID_PARAMS("E0002_400_0001", "Invalid input parameters."),

    // 404 Error

    /**
     * Target resource does not exist
     */
    RESOURCE_NOT_FOUND("E0002_404_0001", "Resource not found."),

    // 500 Error

    /**
     * Uncaught exception
     */
    UNCAUGHT_EXCEPTION("E0002_500_0001", "System error."),

    /**
     * Error when doing DB CRUD
     */
    DB_CRUD_ERROR("E0002_500_0002", "System error."),

    /**
     * Error occurred when calling REST APIs
     */
    REST_API_CALL_ERROR("E0002_500_0003", "System error.");

    /**
     * Error code
     */
    private final String errorCode;
    /**
     * Default message returned for the error
     */
    private final String defaultMessage;

    Error(String errorCode, String defaultMessage) {
        this.errorCode = errorCode;
        this.defaultMessage = defaultMessage;
    }

    // Getters

    public String getErrorCode() {
        return errorCode;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
