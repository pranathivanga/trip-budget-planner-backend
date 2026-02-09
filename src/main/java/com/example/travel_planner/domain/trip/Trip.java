package com.example.travel_planner.domain.trip;

public class Trip {
    private final Location source;
    private final Location destination;
    private final int numberOfDays;
    private final int numberOfTravelers;

    public Trip(Location source,
                Location destination,
                int numberOfDays,
                int numberOfTravelers) {

        this.source = source;
        this.destination = destination;
        this.numberOfDays = numberOfDays;
        this.numberOfTravelers = numberOfTravelers;
    }

    public Location getSource() {
        return source;
    }

    public Location getDestination() {
        return destination;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public int getNumberOfTravelers() {
        return numberOfTravelers;
    }
}
