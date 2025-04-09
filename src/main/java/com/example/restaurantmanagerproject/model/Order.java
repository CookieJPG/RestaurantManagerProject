package com.example.restaurantmanagerproject.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    //Fields
    private String id;
    private int tableId;
    private Customer customer;
    private ArrayList<ISellable> orderItems;
    private String orderStatus;
    private LocalDateTime orderDate;

    public Order(String id, int tableId, Customer customer, ArrayList<ISellable> orderItem, String orderStatus, LocalDateTime orderDate) {
        this.id = id;
        this.tableId = tableId;
        this.customer = customer;
        this.orderItems = orderItem;
        this.orderStatus = orderStatus;
        this.orderDate = LocalDateTime.now();
    }

    //Getters
    public String getId() { return id; }
    public int getTableId() { return tableId; }
    public Customer getCustomer() { return customer; }
    public ArrayList<ISellable> getOrderItems() { return orderItems; }
    public String getOrderStatus() { return orderStatus; }
    public LocalDateTime getOrderDate() { return orderDate; }

    public void addItem(ISellable item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            orderItems.add(item);
        }
    }

    public void removeItem(ISellable item) {
        orderItems.remove(item);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (ISellable item : orderItems) {
            total += item.getPrice();
        }
        return total;
    }

    public void updateOrderStatus(String status) {
        this.orderStatus = status;
    }
}
