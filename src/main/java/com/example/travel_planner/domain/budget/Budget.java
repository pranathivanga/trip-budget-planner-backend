package com.example.travel_planner.domain.budget;

import com.example.travel_planner.domain.cost.Money;

public class Budget {
    private final Money totalBudget;

    public Budget(Money totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Money getTotalBudget() {
        return totalBudget;
    }
}
