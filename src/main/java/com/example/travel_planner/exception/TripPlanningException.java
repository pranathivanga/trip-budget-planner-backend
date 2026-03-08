package com.example.travel_planner.exception;

public class TripPlanningException extends RuntimeException {

    private String suggestion;

    public TripPlanningException(String message, String suggestion) {
        super(message);
        this.suggestion = suggestion;
    }

    public String getSuggestion() {
        return suggestion;
    }
}