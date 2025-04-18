package com.example.restaurantmanagerproject.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    // Fields
    private int id;
    private int tableId;
    private Customer customer;
    private ArrayList<ISellable> orderItems;
    private String orderStatus;
    private LocalDateTime orderDate;

    public Order(int id, int tableId, Customer customer, ArrayList<ISellable> orderItem, String orderStatus,
            LocalDateTime orderDate) {
        this.id = id;
        this.tableId = tableId;
        this.customer = customer;
        this.orderItems = orderItem;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getTableId() {
        return tableId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<ISellable> getOrderItems() {
        return orderItems;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderItems(ArrayList<ISellable> orderItems) {
        this.orderItems = orderItems;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void addItem(ISellable item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            orderItems.add(item);
        }
    }

    public void removeItem(ISellable item) {
        orderItems.remove(item);
    }

    public double calculateSubtotal() {
        return orderItems.stream()
                .mapToDouble(ISellable::getPrice)
                .sum();
    }

    public double calculateTotal() {
        double subtotal = calculateSubtotal();

        if (customer != null && customer.getType() != IRewardable.Type.FIRST) {
            subtotal *= (1 - (customer.DiscountRate() / 100));
        }

        return subtotal;
    }

    public double getDiscountAmount() {
        if (customer != null && customer.getType() != IRewardable.Type.FIRST) {
            return calculateSubtotal() * (customer.DiscountRate() / 100);
        }
        return 0.0;
    }

    public void updateOrderStatus(String status) {
        this.orderStatus = status;
    }
}
