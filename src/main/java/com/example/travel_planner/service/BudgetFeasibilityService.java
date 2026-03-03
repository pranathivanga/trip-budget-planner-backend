package com.example.travel_planner.service;

import com.example.travel_planner.domain.budget.Budget;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.FeasibilityResult;
import com.example.travel_planner.domain.decision.RuleViolation;

import java.util.List;

public class BudgetFeasibilityService {

    public FeasibilityResult evaluate(Budget budget,
                                      Money travelCost,
                                      Money stayCost,
                                      Money foodCost) {

        List<Rule> rules = List.of(
                new TravelBudgetRule(travelCost, budget.getTravelBudget()),
                new StayBudgetRule(stayCost, budget.getStayBudget()),
                new FoodBudgetRule(foodCost, budget.getFoodBudget())
        );

        RuleEngine engine = new RuleEngine();
        List<RuleViolation> violations = engine.evaluate(rules);

        boolean feasible = violations.isEmpty();

        Money totalCost = new Money(
                travelCost.getAmount()
                        + stayCost.getAmount()
                        + foodCost.getAmount(),
                "INR"
        );

        return new FeasibilityResult(
                feasible,
                totalCost,
                new Money(0, "INR"),
                violations
        );
    }
}