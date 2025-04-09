package com.example.restaurantmanagerproject.dao;

import com.example.restaurantmanagerproject.model.Reservations;
import java.util.List;

public interface DAOReservations {

    // CRUD operations for reservations

    void SaveReservation(Reservations reservation); // Save or update a reservation

    void RemoveReservation(Reservations reservation);

    List<Reservations> getAllReservations();

}
