package com.example.travel_planner.api;

public class MinimumBudgetResponse {

    private double travelCost;
    private double stayCost;
    private double foodCost;
    private double minimumBudget;

    public MinimumBudgetResponse(
            double travelCost,
            double stayCost,
            double foodCost,
            double minimumBudget
    ) {
        this.travelCost = travelCost;
        this.stayCost = stayCost;
        this.foodCost = foodCost;
        this.minimumBudget = minimumBudget;
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

    public double getMinimumBudget() {
        return minimumBudget;
    }
}