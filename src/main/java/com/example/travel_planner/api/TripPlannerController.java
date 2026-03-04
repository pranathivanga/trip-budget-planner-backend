package com.example.travel_planner.api;

import com.example.travel_planner.domain.budget.Budget;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.plan.TripPlan;
import com.example.travel_planner.domain.trip.Location;
import com.example.travel_planner.domain.trip.StayPreference;
import com.example.travel_planner.domain.trip.Trip;
import com.example.travel_planner.service.ItineraryGenerator;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trips")
public class TripPlannerController {

    private final ItineraryGenerator generator = new ItineraryGenerator();

    @PostMapping("/plan")
    public List<TripPlanResponse> generatePlans(@RequestBody TripRequest request) {

        Trip trip = new Trip(
                new Location(request.getSourceCity()),
                new Location(request.getDestinationCity()),
                request.getDays(),
                request.getTravelers(),
                StayPreference.STANDARD
        );

        Budget budget = new Budget(
                new Money(20000, "INR"),
                new Money(40000, "INR"),
                new Money(15000, "INR")
        );

        List<TripPlan> plans = generator.generatePlans(trip, budget);

        return plans.stream()
                .map(p -> new TripPlanResponse(
                        p.getType().name(),
                        p.getTotalCost().getAmount(),
                        p.getFeasibility().getBudgetState().name(),
                        p.getExplanation()
                ))
                .collect(Collectors.toList());
    }
}