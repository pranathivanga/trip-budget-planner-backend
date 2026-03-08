package com.example.travel_planner.api;

public class ErrorResponse {

    private String error;
    private String message;
    private String suggestion;

    public ErrorResponse(String error, String message, String suggestion) {
        this.error = error;
        this.message = message;
        this.suggestion = suggestion;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getSuggestion() {
        return suggestion;
    }
}