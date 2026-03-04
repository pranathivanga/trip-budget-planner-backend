package com.example.travel_planner.api;

public class TripPlanResponse {

    private String planType;
    private double totalCost;
    private String budgetState;
    private String explanation;

    public TripPlanResponse(String planType,
                            double totalCost,
                            String budgetState,
                            String explanation) {
        this.planType = planType;
        this.totalCost = totalCost;
        this.budgetState = budgetState;
        this.explanation = explanation;
    }

    public String getPlanType() {
        return planType;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getBudgetState() {
        return budgetState;
    }

    public String getExplanation() {
        return explanation;
    }
}