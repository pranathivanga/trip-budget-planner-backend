package com.example.travel_planner.domain.decision;

import com.example.travel_planner.domain.cost.Money;

public class FeasibilityResult {
    private final boolean feasible;
    private final Money totalCost;
    private final Money remainingBudget;

    public FeasibilityResult(boolean feasible,
                             Money totalCost,
                             Money remainingBudget) {
        this.feasible = feasible;
        this.totalCost = totalCost;
        this.remainingBudget = remainingBudget;
    }

    public boolean isFeasible() {
        return feasible;
    }

    public Money getTotalCost() {
        return totalCost;
    }

    public Money getRemainingBudget() {
        return remainingBudget;
    }
}
