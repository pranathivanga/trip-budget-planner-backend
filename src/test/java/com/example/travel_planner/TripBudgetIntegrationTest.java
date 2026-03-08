package com.example.travel_planner;

import com.example.travel_planner.budget.Budget;
import com.example.travel_planner.config.PlannerConfig;
import com.example.travel_planner.distance.DistanceService;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.BudgetState;
import com.example.travel_planner.domain.decision.FeasibilityResult;
import com.example.travel_planner.domain.trip.Location;
import com.example.travel_planner.domain.trip.StayPreference;
import com.example.travel_planner.domain.trip.Trip;
import com.example.travel_planner.service.BudgetFeasibilityService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TripBudgetIntegrationTest {

    @Test
    void shouldDetermineTripAsFeasibleWithinBudget() {

        // 1. Trip input
        Trip trip = new Trip(
                new Location("Hyderabad"),
                new Location("Goa"),
                3,
                2,
                StayPreference.STANDARD

        );

// 2. Individual cost calculations

        PlannerConfig config = new PlannerConfig();
        DistanceService distanceService = new DistanceService();

        TravelCostCalculator travelCalc =
                new TravelCostCalculator(config, distanceService);

        StayCostCalculator stayCalc = new StayCostCalculator();
        FoodCostCalculator foodCalc = new FoodCostCalculator();
        Money travelCost = travelCalc.calculate(trip);
        Money stayCost = stayCalc.calculate(trip);
        Money foodCost = foodCalc.calculate(trip);

        // 3. Category-based Budget
        Budget budget = new Budget(
                new Money(60000, "INR"),    // travel budget
                new Money(20000, "INR"),   // stay budget
                new Money(30000, "INR"),
                new Money(10000, "INR") // food budget
        );

        // 4. Feasibility decision
        BudgetFeasibilityService feasibilityService =
                new BudgetFeasibilityService();

        FeasibilityResult result =
                feasibilityService.evaluate(
                        budget,
                        travelCost,
                        stayCost,
                        foodCost
                );

        // 5. Assertions
        assertTrue(result.isFeasible());

        double expectedTotal =
                travelCost.getAmount()
                        + stayCost.getAmount()
                        + foodCost.getAmount();

        assertTrue(result.isFeasible());
        assertEquals(BudgetState.LUXURY_POSSIBLE, result.getBudgetState());
    }
}