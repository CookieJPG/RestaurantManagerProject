package com.example.restaurantmanagerproject.dao;

import java.util.List;

import com.example.restaurantmanagerproject.model.Reservation;

public interface DAOReservations {

    // CRUD operations for reservations

    void SaveReservation(Reservation reservation); // Save or update a reservation

    void RemoveReservation(Reservation reservation);

    List<Reservation> getAllReservations();

}