package com.example.restaurantmanagerproject.model;

import java.util.Date;

public class Reservations {
    private int reservationId;
    private String customerID;
    private int tableID;
    private Date reservationDate;
    private int numberOfGuests;

    public Reservations(int reservationId, int tableID, String customerID, Date reservationDate,
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

    public Date getReservationDate() {
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

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}
