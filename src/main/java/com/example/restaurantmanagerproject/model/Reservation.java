package com.example.restaurantmanagerproject.model;

import java.time.LocalDate;

public class Reservation {
    private int reservationId;
    private String customerID;
    private int tableID;
    private LocalDate reservationDate;
    private int numberOfGuests;

    public Reservation(int reservationId, int tableID, String customerID, LocalDate reservationDate,
                       int numberOfGuests) {
        this.reservationId = reservationId;
        this.tableID = tableID;
        this.customerID = customerID;
        this.reservationDate = reservationDate;
        this.numberOfGuests = numberOfGuests;
    }

    // Getters
    public int getReservationId() {
        return reservationId;
    }

    public int getTableID() {
        return tableID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    // Setters
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public void setTableID(int tableId) {
        this.tableID = tableId;
    }

    public void setCustomerID(String customerId) {
        this.customerID = customerId;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }
}
