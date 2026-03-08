package com.example.travel_planner;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.RuleViolation;

import java.util.Optional;

public class StayBudgetRule implements Rule {

    private final Money stayCost;
    private final Money stayBudget;

    public StayBudgetRule(Money stayCost, Money stayBudget) {
        this.stayCost = stayCost;
        this.stayBudget = stayBudget;
    }

    @Override
    public Optional<RuleViolation> check() {

        if (stayCost.getAmount() > stayBudget.getAmount()) {
            return Optional.of(
                    new RuleViolation(
                            "STAY_BUDGET_EXCEEDED",
                            "Stay cost exceeds allocated budget"
                    )
            );
        }

        return Optional.empty();
    }
}