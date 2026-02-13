package com.example.travel_planner.domain.decision;

import com.example.travel_planner.domain.cost.Money;

import java.util.List;

public class FeasibilityResult {
    private final boolean feasible;
    private final Money totalCost;
    private final Money remainingBudget;
    private final List<RuleViolation> violations;

    public FeasibilityResult(boolean feasible,
                             Money totalCost,
                             Money remainingBudget,
                             List<RuleViolation> violations) {
        this.feasible = feasible;
        this.totalCost = totalCost;
        this.remainingBudget = remainingBudget;
        this.violations = violations;
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

    public List<RuleViolation> getViolations() {
        return violations;
    }
}
