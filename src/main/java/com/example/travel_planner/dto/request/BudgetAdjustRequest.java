package com.example.travel_planner.dto.request;

public class BudgetAdjustRequest {

    private double travelPercent;
    private double stayPercent;
    private double foodPercent;
private double totalBudget;

    public double getTotalBudget() {
        return totalBudget;
    }

    public double getTravelPercent() {
        return travelPercent;
    }

    public void setTravelPercent(double travelPercent) {
        this.travelPercent = travelPercent;
    }

    public double getStayPercent() {
        return stayPercent;
    }

    public void setStayPercent(double stayPercent) {
        this.stayPercent = stayPercent;
    }

    public double getFoodPercent() {
        return foodPercent;
    }

    public void setFoodPercent(double foodPercent) {
        this.foodPercent = foodPercent;
    }
}