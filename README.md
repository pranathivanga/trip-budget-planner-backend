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

---

📡 API Example

Generate Plans

POST "/api/trips/plan"

Example request

{
  "sourceCity": "Hyderabad",
  "destinationCity": "Goa",
  "days": 3,
  "travelers": 1,
  "totalBudget": 40000
}

Example response

{
  "planType": "BALANCED",
  "travelCost": 3300,
  "stayCost": 10800,
  "foodCost": 2400,
  "totalCost": 16500,
  "budgetState": "COMFORTABLE"
}

---

▶ Running the Project

Clone the repository

git clone https://github.com/YOUR_GITHUB_USERNAME/YOUR_REPO_NAME.git

Run backend

mvn spring-boot:run

Backend runs at

http://localhost:8081

Frontend runs at

http://localhost:3000
