package com.example.travel_planner.api;

public class TripPlanResponse {

    private String planType;

    private double travelCost;
    private double stayCost;
    private double foodCost;

    private double totalCost;

    private String budgetState;

    private String explanation;
    private int comfortScore;
    private int safetyScore;
    private int experienceScore;

    public TripPlanResponse(
            String planType,
            double travelCost,
            double stayCost,
            double foodCost,
            double totalCost,
            String budgetState,
            String explanation
    ) {
        this.planType = planType;
        this.travelCost = travelCost;
        this.stayCost = stayCost;
        this.foodCost = foodCost;
        this.totalCost = totalCost;
        this.budgetState = budgetState;
        this.explanation = explanation;
    }

    public String getPlanType() {
        return planType;
    }

    public double getTravelCost() {
        return travelCost;
    }

    public double getStayCost() {
        return stayCost;
    }

    public double getFoodCost() {
        return foodCost;
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