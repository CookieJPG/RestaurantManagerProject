package com.example.restaurantmanagerproject.model;

/**
 * Represents a dessert item on the restaurant menu.
 * Contains properties for name, size, and price with appropriate getters and
 * setters.
 */
public class Dessert implements ISellable {
    // Private member variables
    private int id; // Unique identifier for the dessert
    private String name;
    private String size;
    private double price;

    /**
     * Default constructor - creates a dessert with default values
     */
    public Dessert() {
        this.id = 0;
        this.name = "";
        this.size = ""; // Default size
        this.price = 0.0; // Default price
    }

    /**
     * Creates a Dessert object
     * 
     * @param name  Dessert name (e.g., "Chocolate Cake", "Ice Cream")
     * @param size  Serving size (e.g., "Single", "Double", "Shareable")
     * @param price Price in local currency
     */
    public Dessert(int id, String name, String size, double price) {
        this.id = id;
        this.name = name;
        this.size = size;
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

    // Size getter/setter
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // Price getter/setter
    public double getPrice() {
        return price;
    }

    @Override
    public String getCategory() {
        return "Dessert";
    }

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