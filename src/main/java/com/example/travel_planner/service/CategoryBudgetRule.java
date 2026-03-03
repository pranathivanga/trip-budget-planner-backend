package com.example.travel_planner.service;

import com.example.travel_planner.domain.budget.Budget;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.RuleViolation;

public class CategoryBudgetRule {

    public RuleViolation check(String category,
                               Money cost,
                               Money allowedBudget) {

        if (cost.getAmount() > allowedBudget.getAmount()) {
            return new RuleViolation(
                    category.toUpperCase() + "_BUDGET_EXCEEDED",
                    category + " cost exceeds allocated budget"
            );
        }
        return null;
    }
}
