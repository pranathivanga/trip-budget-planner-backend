package com.example.travel_planner.service;

import com.example.travel_planner.domain.budget.Budget;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.FeasibilityResult;

public class BudgetFeasibilityService {
    public FeasibilityResult evaluate(Budget budget, Money totalCost) {

        double remaining =
                budget.getTotalBudget().getAmount() - totalCost.getAmount();

        boolean feasible = remaining >= 0;

        Money remainingBudget = new Money(remaining, "INR");

        return new FeasibilityResult(
                feasible,
                totalCost,
                remainingBudget
        );
    }
}
