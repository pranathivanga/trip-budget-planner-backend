package com.example.travel_planner;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.trip.StayPreference;
import com.example.travel_planner.domain.trip.Trip;

public class FoodCostCalculator {

    public Money calculate(Trip trip) {

        double costPerDay;

        StayPreference preference = trip.getStayPreference();

        if(preference == StayPreference.BUDGET) {
            costPerDay = 400;
        }
        else if(preference == StayPreference.STANDARD) {
            costPerDay = 700;
        }
        else {
            costPerDay = 1200;
        }

        double total =
                costPerDay *
                        trip.getNumberOfDays() *
                        trip.getNumberOfTravelers();

        return new Money(total, "INR");
    }
}