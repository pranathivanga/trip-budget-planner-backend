package com.example.travel_planner.service;

import com.example.travel_planner.domain.budget.Budget;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.FeasibilityResult;
import com.example.travel_planner.domain.decision.RuleViolation;

import java.util.Collections;
import java.util.List;

public class BudgetFeasibilityService {

    public FeasibilityResult evaluate(Budget budget, Money totalCost) {

        double remaining =
                budget.getTotalBudget().getAmount() - totalCost.getAmount();

        boolean feasible = remaining >= 0;

        Money remainingBudget = new Money(remaining, "INR");

        // 🔹 No rules applied yet → empty violations list
        List<RuleViolation> violations = Collections.emptyList();

        return new FeasibilityResult(
                feasible,
                totalCost,
                remainingBudget,
                violations
        );
    }
}
