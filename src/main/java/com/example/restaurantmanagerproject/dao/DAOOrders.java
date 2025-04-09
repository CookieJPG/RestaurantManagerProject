package com.example.restaurantmanagerproject.dao;

import java.util.List;
import com.example.restaurantmanagerproject.model.Orders;

public interface DAOOrders {

    // CRUD operations for orders
    List<Orders> getAllOrders();

    void SaveOrder(Orders order); // Save or update order

    void RemoveOrder(Orders order);

    Orders getOrderById(int orderId);

}
