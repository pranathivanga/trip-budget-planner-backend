package com.example.travel_planner.service;

import com.example.travel_planner.service.budget.Budget;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.BudgetState;
import com.example.travel_planner.domain.decision.FeasibilityResult;
import com.example.travel_planner.domain.decision.RuleViolation;

import java.util.List;

public class BudgetFeasibilityService {

    public FeasibilityResult evaluate(Budget budget,
                                      Money travelCost,
                                      Money stayCost,
                                      Money foodCost) {

        List<Rule> rules = List.of(

                new FoodBudgetRule(foodCost, budget.getTotalBudget())
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

        double totalBudget =
                budget.getTravelBudget().getAmount()
                        + budget.getStayBudget().getAmount()
                        + budget.getFoodBudget().getAmount();

        double totalCostAmount =
                travelCost.getAmount()
                        + stayCost.getAmount()
                        + foodCost.getAmount();

        double remaining = totalBudget - totalCostAmount;

        BudgetState state;

        if (!violations.isEmpty()) {
            state = BudgetState.NOT_FEASIBLE;
        } else {
            double ratio = remaining / totalBudget;

            if (ratio <= 0.05) {
                state = BudgetState.TIGHT;
            } else if (ratio <= 0.25) {
                state = BudgetState.COMFORTABLE;
            } else {
                state = BudgetState.LUXURY_POSSIBLE;
            }
        }

        return new FeasibilityResult(
                violations.isEmpty(),
                new Money(totalCostAmount, "INR"),
                new Money(remaining, "INR"),
                violations,
                state
        );
    }
}