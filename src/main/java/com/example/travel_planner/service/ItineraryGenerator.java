package com.example.travel_planner.service;

import com.example.travel_planner.domain.plan.PlanType;
import com.example.travel_planner.domain.plan.TripPlan;
import com.example.travel_planner.domain.trip.StayPreference;
import com.example.travel_planner.domain.trip.Trip;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.FeasibilityResult;
import com.example.travel_planner.domain.budget.Budget;

import java.util.ArrayList;
import java.util.List;

public class ItineraryGenerator {

    private final TravelCostCalculator travelCostCalculator = new TravelCostCalculator();
    private final StayCostCalculator stayCostCalculator = new StayCostCalculator();
    private final FoodCostCalculator foodCostCalculator = new FoodCostCalculator();
    private final BudgetFeasibilityService feasibilityService = new BudgetFeasibilityService();

    public List<TripPlan> generatePlans(Trip baseTrip, Budget budget) {

        List<TripPlan> plans = new ArrayList<>();

        plans.add(createPlan(baseTrip, budget, StayPreference.BUDGET, PlanType.BUDGET));
        plans.add(createPlan(baseTrip, budget, StayPreference.STANDARD, PlanType.BALANCED));
        plans.add(createPlan(baseTrip, budget, StayPreference.PREMIUM, PlanType.COMFORT));

        return plans;
    }

    private TripPlan createPlan(Trip baseTrip,
                                Budget budget,
                                StayPreference preference,
                                PlanType type) {

        Trip trip = new Trip(
                baseTrip.getSource(),
                baseTrip.getDestination(),
                baseTrip.getNumberOfDays(),
                baseTrip.getNumberOfTravelers(),
                preference
        );

        Money travelCost = travelCostCalculator.calculate(trip);
        Money stayCost = stayCostCalculator.calculate(trip);
        Money foodCost = foodCostCalculator.calculate(trip);

        FeasibilityResult result =
                feasibilityService.evaluate(budget, travelCost, stayCost, foodCost);

        Money totalCost = new Money(
                travelCost.getAmount()
                        + stayCost.getAmount()
                        + foodCost.getAmount(),
                "INR"
        );

        String explanation =
                "Generated " + type + " plan using stay preference " + preference;

        return new TripPlan(type, totalCost, result, explanation);
    }
}