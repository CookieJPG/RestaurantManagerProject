package com.example.restaurantmanagerproject.model;

public class Table {
    private int id;
    private boolean available;

    public Table(int id, boolean b) {
        this.id = id;
        this.available = b;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void Reserve() {
        available = false;
    }

    public void UnReserve() {
        available = true;
    }
}
