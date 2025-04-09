package com.example.restaurantmanagerproject.dao;

import com.example.restaurantmanagerproject.model.Reservation;
import java.util.List;

public interface DAOReservations {

    // CRUD operations for reservations

    void SaveReservation(Reservation reservation); // Save or update a reservation

    void RemoveReservation(Reservation reservation);

    List<Reservation> getAllReservations();

}
