✈️ TripWise — Smart Budget Travel Planner

TripWise is a backend-driven travel planning system that helps users determine whether a trip is financially feasible before making bookings.

Instead of simply suggesting travel options, the system evaluates budget constraints, calculates trip costs, and generates optimized travel plans.

---

🚀 Features

- Budget / Balanced / Comfort travel plan generation
- Distance-based travel cost calculation
- Reverse minimum budget calculator
- Budget feasibility analysis
- Automatic trip itinerary generation
- PDF export for trip summary
- Smart suggestions for improving trip feasibility
- Unit tests for core planning logic

---

🛠 Tech Stack

Backend

- Java
- Spring Boot
- Maven
- REST APIs
- JUnit Testing

Frontend

- React
- Tailwind CSS

---
```
🏗 Architecture

User Interface (React)
        │
        ▼
TripPlannerController
        │
        ▼
Trip Planning Engine
(ItineraryGenerator)
        │
        ▼
Cost Calculators
├── TravelCostCalculator
├── StayCostCalculator
└── FoodCostCalculator
        │
        ▼
BudgetFeasibilityService
        │
        ▼
PlanScoringService
        │
        ▼
SuggestionService
        │
        ▼
Response → Frontend
```
---

▶ Running the Project

Clone the repository

git clone (https://github.com/pranathivanga/trip-budget-planner-backend.git)

Run backend

mvn spring-boot:run

## Live Deployment

Frontend  
https://tripwise-frontend.vercel.app

Backend API  
http://54.226.116.18:8081
