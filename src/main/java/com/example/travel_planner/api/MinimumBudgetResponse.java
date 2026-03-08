package com.example.travel_planner.api;

public class MinimumBudgetResponse {

    private double minimumBudget;
    private int recommendedDays;
    private String suggestion;
private double travelCost;
private double stayCost;
private double foodCost;

    public double getTravelCost() {
        return travelCost;
    }

    public void setTravelCost(double travelCost) {
        this.travelCost = travelCost;
    }

    public double getStayCost() {
        return stayCost;
    }

    public void setStayCost(double stayCost) {
        this.stayCost = stayCost;
    }

    public double getFoodCost() {
        return foodCost;
    }

    public void setFoodCost(double foodCost) {
        this.foodCost = foodCost;
    }

    public MinimumBudgetResponse(
            double minimumBudget,
            double travelCost,
            double stayCost,
            double foodCost,
            int recommendedDays,
            String suggestion
    ) {
        this.minimumBudget = minimumBudget;
        this.travelCost = travelCost;
        this.stayCost = stayCost;
        this.foodCost = foodCost;
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