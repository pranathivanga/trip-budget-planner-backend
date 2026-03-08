package com.example.travel_planner;


import com.example.travel_planner.budget.Budget;
import com.example.travel_planner.budget.BudgetAllocator;
import com.example.travel_planner.domain.trip.Location;
import com.example.travel_planner.domain.trip.StayPreference;
import com.example.travel_planner.domain.trip.Trip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetAllocatorTest {

    @Test
    void shouldAllocateBudgetAcrossCategories() {

        Trip trip = new Trip(
                new Location("Hyderabad"),
                new Location("Goa"),
                3,
                2,
                StayPreference.STANDARD
        );

        BudgetAllocator allocator = new BudgetAllocator();

        Budget budget = allocator.allocate(20000, trip);

        assertNotNull(budget);
        assertTrue(budget.getTravelBudget().getAmount() > 0);
        assertTrue(budget.getStayBudget().getAmount() > 0);
        assertTrue(budget.getFoodBudget().getAmount() > 0);
    }
}