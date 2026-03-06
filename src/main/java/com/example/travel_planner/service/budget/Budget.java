package com.example.travel_planner.service.budget;

import com.example.travel_planner.domain.cost.Money;

public class Budget {

    private final Money totalBudget;
    private final Money travelBudget;
    private final Money stayBudget;
    private final Money foodBudget;

    public Budget(Money totalBudget, Money travelBudget, Money stayBudget, Money foodBudget) {
        this.totalBudget = totalBudget;
        this.travelBudget = travelBudget;
        this.stayBudget = stayBudget;
        this.foodBudget = foodBudget;
    }

    public Money getTotalBudget() {
        return totalBudget;
    }

    public Money getTravelBudget() {
        return travelBudget;
    }

    public Money getStayBudget() {
        return stayBudget;
    }

    public Money getFoodBudget() {
        return foodBudget;
    }
}