package com.example.restaurantmanagerproject.model;

public class Beverage implements ISellable {
    private String name;
    private double basePrice;
    private String size;

    public Beverage(String name, double basePrice, String size) {
        this.name = name;
        this.basePrice = basePrice;
        this.size = size;
    }

    @Override
    public double getPrice() {
        return switch (size) {
            case "Large" -> basePrice * 1.8;
            case "Medium" -> basePrice * 1.5;
            default -> basePrice * 1.2; //That would be the small
        };
    }

    @Override
    public String getCategory() {
        return "Beverage";
    }

    @Override
    public String getName() {
        return name + " (" + size + ") ";
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
