package com.example.travel_planner.domain.plan;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.BudgetState;

public class TripPlan {

    private final PlanType planType;

    private final Money travelCost;
    private final Money stayCost;
    private final Money foodCost;

    private final Money totalCost;

    private final BudgetState budgetState;

    private final String explanation;

    public TripPlan(
            PlanType planType,
            Money travelCost,
            Money stayCost,
            Money foodCost,
            Money totalCost,
            BudgetState budgetState,
            String explanation
    ) {
        this.planType = planType;
        this.travelCost = travelCost;
        this.stayCost = stayCost;
        this.foodCost = foodCost;
        this.totalCost = totalCost;
        this.budgetState = budgetState;
        this.explanation = explanation;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public Money getTravelCost() {
        return travelCost;
    }

    public Money getStayCost() {
        return stayCost;
    }

    public Money getFoodCost() {
        return foodCost;
    }

    public Money getTotalCost() {
        return totalCost;
    }

    public BudgetState getBudgetState() {
        return budgetState;
    }

    public String getExplanation() {
        return explanation;
    }
}