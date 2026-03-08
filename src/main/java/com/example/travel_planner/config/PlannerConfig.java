package com.example.travel_planner.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "planner")
public class PlannerConfig {

    private Travel travel = new Travel();
    private Food food = new Food();
    private Stay stay = new Stay();

    public static class Travel {
        private double costPerKm=8;

        public double getCostPerKm() {
            return costPerKm;
        }

        public void setCostPerKm(double costPerKm) {
            this.costPerKm = costPerKm;
        }
    }

    public static class Food {
        private double high;
        private double medium;
        private double low;

        public double getHigh() { return high; }
        public void setHigh(double high) { this.high = high; }

        public double getMedium() { return medium; }
        public void setMedium(double medium) { this.medium = medium; }

        public double getLow() { return low; }
        public void setLow(double low) { this.low = low; }
    }

    public static class Stay {
        private double high;
        private double medium;
        private double low;

        public double getHigh() { return high; }
        public void setHigh(double high) { this.high = high; }

        public double getMedium() { return medium; }
        public void setMedium(double medium) { this.medium = medium; }

        public double getLow() { return low; }
        public void setLow(double low) { this.low = low; }
    }

    public Travel getTravel() { return travel; }
    public Food getFood() { return food; }
    public Stay getStay() { return stay; }
}