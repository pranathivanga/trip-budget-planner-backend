package com.example.travel_planner;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.RuleViolation;

import java.util.Optional;

public class FoodBudgetRule implements Rule {

    private final Money foodCost;
    private final Money foodBudget;

    public FoodBudgetRule(Money foodCost, Money foodBudget) {
        this.foodCost = foodCost;
        this.foodBudget = foodBudget;
    }

    @Override
    public Optional<RuleViolation> check() {

        if (foodCost.getAmount() > foodBudget.getAmount()) {
            return Optional.of(
                    new RuleViolation(
                            "FOOD_BUDGET_EXCEEDED",
                            "Food cost exceeds allocated budget"
                    )
            );
        }

        return Optional.empty();
    }
}