package com.example.travel_planner.service;

import com.example.travel_planner.domain.plan.PlanType;
import com.example.travel_planner.domain.plan.TripPlan;
import com.example.travel_planner.domain.trip.StayPreference;
import com.example.travel_planner.domain.trip.Trip;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.FeasibilityResult;
import com.example.travel_planner.service.budget.Budget;
import com.example.travel_planner.config.PlannerConfig;
import com.example.travel_planner.service.distance.DistanceService;
import com.example.travel_planner.service.explanation.ExplanationService;

import java.util.ArrayList;
import java.util.List;

public class ItineraryGenerator {
    private final PlannerConfig config = new PlannerConfig();
    private final TravelCostCalculator travelCostCalculator =
            new TravelCostCalculator(new PlannerConfig(), new DistanceService());
    private final StayCostCalculator stayCostCalculator = new StayCostCalculator();
    private final FoodCostCalculator foodCostCalculator = new FoodCostCalculator();
    private final BudgetFeasibilityService feasibilityService = new BudgetFeasibilityService();
    private final ExplanationService explanationService = new ExplanationService();

    public List<TripPlan> generatePlans(Trip baseTrip, Budget budget) {

        List<TripPlan> plans = new ArrayList<>();

        plans.add(createPlan(baseTrip, budget, StayPreference.BUDGET, PlanType.BUDGET));
        plans.add(createPlan(baseTrip, budget, StayPreference.STANDARD, PlanType.BALANCED));
        plans.add(createPlan(baseTrip, budget, StayPreference.PREMIUM, PlanType.COMFORT));

        return plans;
    }
    private int reduceDays(int currentDays) {
        if (currentDays > 1) {
            return currentDays - 1;
        }
        return 1;
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

        StayPreference usedPreference = preference;

        if (!result.isFeasible()) {
            if (!result.isFeasible()) {

                int reducedDays = reduceDays(baseTrip.getNumberOfDays());

                if (reducedDays < baseTrip.getNumberOfDays()) {

                    Trip shorterTrip = new Trip(
                            baseTrip.getSource(),
                            baseTrip.getDestination(),
                            reducedDays,
                            baseTrip.getNumberOfTravelers(),
                            usedPreference
                    );

                    travelCost = travelCostCalculator.calculate(shorterTrip);
                    stayCost = stayCostCalculator.calculate(shorterTrip);
                    foodCost = foodCostCalculator.calculate(shorterTrip);

                    result = feasibilityService.evaluate(
                            budget,
                            travelCost,
                            stayCost,
                            foodCost
                    );
                }
            }
            StayPreference downgraded = downgrade(preference);

            if (downgraded != preference) {

                Trip cheaperTrip = new Trip(
                        baseTrip.getSource(),
                        baseTrip.getDestination(),
                        baseTrip.getNumberOfDays(),
                        baseTrip.getNumberOfTravelers(),
                        downgraded
                );

                stayCost = stayCostCalculator.calculate(cheaperTrip);

                result = feasibilityService.evaluate(
                        budget,
                        travelCost,
                        stayCost,
                        foodCost
                );

                usedPreference = downgraded;
            }
        }
        Money totalCost = new Money(
                travelCost.getAmount()
                        + stayCost.getAmount()
                        + foodCost.getAmount(),
                "INR"
        );

        int finalDays = baseTrip.getNumberOfDays();

        TripPlan tempPlan = new TripPlan(
                type,
                travelCost,
                stayCost,
                foodCost,
                totalCost,
                result.getBudgetState(),
                ""
        );

        String explanation = explanationService.generateExplanation(tempPlan);

        TripPlan plan = new TripPlan(
                type,
                travelCost,
                stayCost,
                foodCost,
                totalCost,
                result.getBudgetState(),
                explanation
        );

        return plan;
    }
    private StayPreference downgrade(StayPreference current) {

        if (current == StayPreference.PREMIUM) {
            return StayPreference.STANDARD;
        }

        if (current == StayPreference.STANDARD) {
            return StayPreference.BUDGET;
        }

        return StayPreference.BUDGET;
    }
    public TravelCostCalculator getTravelCostCalculator() {
        return travelCostCalculator;
    }

    public StayCostCalculator getStayCostCalculator() {
        return stayCostCalculator;
    }

    public FoodCostCalculator getFoodCostCalculator() {
        return foodCostCalculator;
    }
}