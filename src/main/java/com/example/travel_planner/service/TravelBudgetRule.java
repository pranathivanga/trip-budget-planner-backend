package com.example.travel_planner.service;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.RuleViolation;

import java.util.Optional;

public class TravelBudgetRule implements Rule {

    private final Money travelCost;
    private final Money travelBudget;

    public TravelBudgetRule(Money travelCost, Money travelBudget) {
        this.travelCost = travelCost;
        this.travelBudget = travelBudget;
    }

    @Override
    public Optional<RuleViolation> check() {

        if (travelCost.getAmount() > travelBudget.getAmount()) {
            return Optional.of(
                    new RuleViolation(
                            "TRAVEL_BUDGET_EXCEEDED",
                            "Travel cost exceeds allocated budget"
                    )
            );
        }

        return Optional.empty();
    }
}