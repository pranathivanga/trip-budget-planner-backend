package com.example.travel_planner.exception;

import com.example.travel_planner.api.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TripPlanningException.class)
    public ResponseEntity<ErrorResponse> handleTripException(
            TripPlanningException ex) {

        ErrorResponse response =
                new ErrorResponse(
                        "TRIP_PLANNING_ERROR",
                        ex.getMessage(),
                        ex.getSuggestion()
                );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex) {

        ErrorResponse response =
                new ErrorResponse(
                        "INTERNAL_ERROR",
                        "Something went wrong",
                        "Please try again later"
                );

        return ResponseEntity.internalServerError().body(response);
    }
}