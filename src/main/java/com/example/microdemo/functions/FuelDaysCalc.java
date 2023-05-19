package com.example.microdemo.functions;

public class FuelDaysCalc {
    public static String calculator(Integer remainingFuel) {
        int fuelConsumptionPerDay = 5;
        int days = remainingFuel / fuelConsumptionPerDay;
        return "The spaceship will last for " + days + " days with the given amount of fuel.";
    }
}
