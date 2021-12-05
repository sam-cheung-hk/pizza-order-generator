package com.challenge.order.generator.bean.incoming.response;

import com.challenge.order.generator.enums.Error;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class GenericErrorResponse {

    @Schema(description = "Code for the error", example = "E0002_500_0001")
    @JsonProperty("errorCode")
    private final String errorCode;

    @Schema(description = "Detail message of the error", example = "System error.")
    @JsonProperty("errorMessage")
    private final String errorMessage;

    public GenericErrorResponse(Error error) {
        this(error, error.getDefaultMessage());
    }

    public GenericErrorResponse(Error error, String customizedErrorMessage) {
        this.errorCode = error.getErrorCode();
        this.errorMessage = customizedErrorMessage;
    }

    // Getters

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "GenericErrorResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
