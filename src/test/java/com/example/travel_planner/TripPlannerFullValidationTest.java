package com.example.travel_planner;

import com.example.travel_planner.budget.Budget;
import com.example.travel_planner.budget.BudgetAllocator;
import com.example.travel_planner.domain.plan.TripPlan;
import com.example.travel_planner.domain.trip.Location;
import com.example.travel_planner.domain.trip.StayPreference;
import com.example.travel_planner.domain.trip.Trip;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TripPlannerFullValidationTest {

    @Test
    void shouldProduceCorrectCostsForKnownTrip() {

        Trip trip = new Trip(
                new Location("Hyderabad"),
                new Location("Delhi"),
                3,
                1,
                StayPreference.STANDARD
        );

        BudgetAllocator allocator = new BudgetAllocator();
        Budget budget = allocator.allocate(40000, trip);

        ItineraryGenerator generator = new ItineraryGenerator();
        List<TripPlan> plans = generator.generatePlans(trip, budget);

        TripPlan budgetPlan = plans.get(0);
        TripPlan balancedPlan = plans.get(1);
        TripPlan comfortPlan = plans.get(2);

        // Travel cost should be same for all plans
        assertEquals(budgetPlan.getTravelCost().getAmount(),
                balancedPlan.getTravelCost().getAmount());

        assertEquals(budgetPlan.getTravelCost().getAmount(),
                comfortPlan.getTravelCost().getAmount());

        // Stay cost should increase with plan type
        assertTrue(budgetPlan.getStayCost().getAmount()
                < balancedPlan.getStayCost().getAmount());

        assertTrue(balancedPlan.getStayCost().getAmount()
                < comfortPlan.getStayCost().getAmount());

        // Food cost should increase with plan type
        assertTrue(budgetPlan.getFoodCost().getAmount()
                < balancedPlan.getFoodCost().getAmount());

        assertTrue(balancedPlan.getFoodCost().getAmount()
                < comfortPlan.getFoodCost().getAmount());

        // Total cost validation
        for (TripPlan plan : plans) {

            double expectedTotal =
                    plan.getTravelCost().getAmount()
                            + plan.getStayCost().getAmount()
                            + plan.getFoodCost().getAmount();

            assertEquals(expectedTotal, plan.getTotalCost().getAmount());
        }

        // No cost should be zero
        for (TripPlan plan : plans) {
            assertTrue(plan.getTravelCost().getAmount() > 0);
            assertTrue(plan.getStayCost().getAmount() > 0);
            assertTrue(plan.getFoodCost().getAmount() > 0);
        }
    }
}