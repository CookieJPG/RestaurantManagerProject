package com.example.restaurantmanagerproject.model;

public class Desert implements ISellable {
    private String name;
    private double price;

    public Desert(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCategory() {
        return "Desert";
    }
}
