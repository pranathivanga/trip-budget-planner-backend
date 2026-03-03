package com.example.travel_planner.domain.budget;

import com.example.travel_planner.domain.cost.Money;

public class Budget {

    private final Money travelBudget;
    private final Money stayBudget;
    private final Money foodBudget;

    public Budget(Money travelBudget,
                  Money stayBudget,
                  Money foodBudget) {
        this.travelBudget = travelBudget;
        this.stayBudget = stayBudget;
        this.foodBudget = foodBudget;
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