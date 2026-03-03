package com.example.travel_planner.service;

import com.example.travel_planner.domain.budget.Budget;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.FeasibilityResult;
import com.example.travel_planner.domain.decision.RuleViolation;

import java.util.ArrayList;
import java.util.List;

public class BudgetFeasibilityService {

    public FeasibilityResult evaluate(Budget budget,
                                      Money travelCost,
                                      Money stayCost,
                                      Money foodCost) {

        List<RuleViolation> violations = new ArrayList<>();

        CategoryBudgetRule rule = new CategoryBudgetRule();

        RuleViolation travelViolation =
                rule.check("travel", travelCost, budget.getTravelBudget());

        RuleViolation stayViolation =
                rule.check("stay", stayCost, budget.getStayBudget());

        RuleViolation foodViolation =
                rule.check("food", foodCost, budget.getFoodBudget());

        if (travelViolation != null) violations.add(travelViolation);
        if (stayViolation != null) violations.add(stayViolation);
        if (foodViolation != null) violations.add(foodViolation);

        boolean feasible = violations.isEmpty();

        // Remaining budget is optional now — keeping simple
        Money dummyRemaining = new Money(0, "INR");

        return new FeasibilityResult(
                feasible,
                new Money(
                        travelCost.getAmount()
                                + stayCost.getAmount()
                                + foodCost.getAmount(),
                        "INR"
                ),
                dummyRemaining,
                violations
        );
    }
}