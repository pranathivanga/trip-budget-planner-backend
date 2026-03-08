package com.example.travel_planner.api;

public class MinimumBudgetResponse {

    private double minimumBudget;
    private int recommendedDays;
    private String suggestion;

    public MinimumBudgetResponse(double minimumBudget, int recommendedDays, String suggestion) {
        this.minimumBudget = minimumBudget;
        this.recommendedDays = recommendedDays;
        this.suggestion = suggestion;
    }

    public double getMinimumBudget() {
        return minimumBudget;
    }

    public int getRecommendedDays() {
        return recommendedDays;
    }

    public String getSuggestion() {
        return suggestion;
    }
}