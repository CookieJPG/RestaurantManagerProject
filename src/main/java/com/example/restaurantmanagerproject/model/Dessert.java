package com.example.restaurantmanagerproject.model;

/**
 * Represents a dessert item on the restaurant menu.
 * Contains properties for name, size, and price with appropriate getters and setters.
 */
public class Dessert {
    // Private member variables
    private String name;
    private String size;
    private double price;

    /**
     * Default constructor - creates a dessert with default values
     */
    public Dessert() {
        this.name = "";
        this.size = ""; // Default size
        this.price = price;
    }

    /**
     * Creates a Dessert object
     * @param name  Dessert name (e.g., "Chocolate Cake", "Ice Cream")
     * @param size  Serving size (e.g., "Single", "Double", "Shareable")
     * @param price Price in local currency
     */
    public Dessert(String name, String size, double price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }

    // Getter and Setter methods

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // Size getter/setter
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    // Price getter/setter
    public double getPrice() { return price; }

    /**
     * Sets the price of the dessert
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