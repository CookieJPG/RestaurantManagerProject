package com.example.restaurantmanagerproject.model;

public class Dish implements ISellable {
    private String dishName;
    private double dishPrice;

    public Dish(String dishName, double dishPrice) {
        this.dishName = dishName;
        this.dishPrice = dishPrice;
    }

    @Override
    public String getName() {
        return dishName;
    }

    @Override
    public double getPrice() {
        return dishPrice;
    }

    @Override
    public String getCategory() {
        return "Dish";
    }

}
