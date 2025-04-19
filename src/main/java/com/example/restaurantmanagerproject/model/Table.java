package com.example.restaurantmanagerproject.model;

public class Table {
    private int id;
    private boolean available;

    public Table(boolean b) {
        // this.id = id; // Solo si quitamos el IDENTITY de la base de datos
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

    public void setId(int int1) {
        this.id = int1;
    }
}
