package com.example.restaurantmanagerproject.dao;

import java.util.List;

import com.example.restaurantmanagerproject.model.Order;

public interface DAOOrders {

    // CRUD operations for orders
    List<Order> getAllOrders();

    boolean SaveOrder(Order order); // Save or update order

    void RemoveOrder(Order order);

    Order getOrderById(int orderId);

}
