package com.example.restaurantmanagerproject.model;

/**
 * Represents a main dish item on the restaurant menu.
 * Contains properties for name and price with appropriate getters and setters.
 * Main dishes typically don't have sizes in most restaurants.
 */
public class Dish implements ISellable {
    // Private member variables
    private int id;
    private String name;
    private double price;

    /**
     * Default constructor - creates a main dish with default values
     */

    public Dish() {
        this.id = 0;
        this.name = ""; // Default name
        this.price = 0.0; // Default price
    }

    public Dish(int id, String Name, String string, double price) {
        this.id = id;
        this.name = Name;
        this.price = price;
    }

    // Getter and Setter methods

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Price getter/setter
    public double getPrice() {
        return price;
    }

    @Override
    public String getCategory() {
        return "Dish";
    }

    /**
     * Sets the price of the main dish
     */
    public void setPrice(double price) {
        // Basic validation to ensure price isn't negative
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Error: Price cannot be negative.");
        }
    }

}