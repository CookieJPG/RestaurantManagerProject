package com.example.restaurantmanagerproject.model;

import java.time.LocalDateTime;

public class Reservation {
    private int reservationId;
    private String customerID;
    private int tableID;
    private LocalDateTime reservationDate;
    private int numberOfGuests;
    private String status;

    public Reservation(int reservationId, int tableID, String customerID, LocalDateTime reservationDate,
            int numberOfGuests) {
        this.reservationId = reservationId;
        this.tableID = tableID;
        this.customerID = customerID;
        this.reservationDate = reservationDate;
        this.numberOfGuests = numberOfGuests;
    }

    // Constructor for creating a reservation with a LocalDateTime object to the
    // database and a status
    public Reservation(int reservationId2, String customerId2, int tableId2,
            LocalDateTime reservationDate2,
            int numberOfGuests2, String status) {
        this.reservationId = reservationId2;
        this.customerID = customerId2;
        this.tableID = tableId2;
        this.reservationDate = reservationDate2;
        this.numberOfGuests = numberOfGuests2;
        this.status = status;
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

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public String getStatus() {
        return status;
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

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
