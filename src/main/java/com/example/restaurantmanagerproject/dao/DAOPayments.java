package com.example.restaurantmanagerproject.dao;

import com.example.restaurantmanagerproject.model.Payment;

public interface DAOPayments {

    // CRUD operations for payments
    Payment createPayment(Payment payment);

    Payment readPayment(int paymentId);

    void deletePayment(int paymentId);

}