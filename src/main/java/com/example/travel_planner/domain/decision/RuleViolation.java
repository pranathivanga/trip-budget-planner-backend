package com.example.travel_planner.domain.decision;

public class RuleViolation {
    private final String ruleName;
    private final String message;

    public RuleViolation(String ruleName, String message) {
        this.ruleName = ruleName;
        this.message = message;
    }

    public String getRuleName() {
        return ruleName;
    }

    public String getMessage() {
        return message;
    }
}
