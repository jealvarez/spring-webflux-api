package com.spring.example.api.model;

public enum ErrorCode implements Error {

    INVALID_REQUEST("Request is not well-formed, syntactically incorrect, or violates schema"),
    INTERNAL_SERVER_ERROR("An internal server error has occurred");

    private final String message;

    ErrorCode(final String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
