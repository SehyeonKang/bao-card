package com.bao.baocard.infra.exception;

public enum ResponseCode {
    FAILED_TO_OPENAI("Failed to get a response from OpenAI");

    private final String message;

    ResponseCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

