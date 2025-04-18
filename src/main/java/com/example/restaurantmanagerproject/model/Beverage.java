package com.example.restaurantmanagerproject.model;

/**
 * Create classes, constructors and getters and setters for Beverage (Name,
 * Size, Price), MainDish (Name, Price), and Dessert (Name, Size, Price).
 * Represents a beverage item on the restaurant menu.
 * Contains properties for name, size, and price with appropriate getters and
 * setters.
 */

public class Beverage implements ISellable {
    // Private member variables to encapsulate the data
    private int id;
    private String name;
    private String size;
    private double price;

    /**
     * Creates a beverage with default values
     */
    public Beverage() {
        this.id = 0; // Default ID
        this.name = "";
        this.size = "Medium"; // Default size
        this.price = 0.0;
    }

    /**
     * Creates a beverage with specified values
     */
    public Beverage(int id, String name, String size, double price) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
    }

    // Name getter/setter
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
     * Sets the price of the beverage
     * 
     * @param price The new price for the beverage
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