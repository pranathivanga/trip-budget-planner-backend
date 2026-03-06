package com.example.travel_planner.service.itinerary;



import com.example.travel_planner.domain.itinerary.DayPlan;
import com.example.travel_planner.domain.trip.Trip;

import java.util.ArrayList;
import java.util.List;

public class ItineraryService {

    public List<DayPlan> generateItinerary(Trip trip) {

        List<DayPlan> plans = new ArrayList<>();

        int days = trip.getNumberOfDays();

        if (days >= 1) {
            plans.add(new DayPlan(1, "Arrival and explore local area"));
        }

        if (days >= 2) {
            plans.add(new DayPlan(2, "Visit major sightseeing attractions"));
        }

        if (days >= 3) {
            plans.add(new DayPlan(3, "Beach / cultural experience / local food tour"));
        }

        if (days >= 4) {
            plans.add(new DayPlan(4, "Adventure activities or shopping"));
        }

        if (days >= 5) {
            plans.add(new DayPlan(5, "Relaxation and departure preparation"));
        }

        return plans;
    }
}