package com.example.restaurantmanagerproject.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {
    private int paymentId;
    private double amount;
    private Status status;
    private Order order;
    private String paymentMethod;
    private String transactionId;
    private LocalDateTime paymentDate;

    public Payment(int paymentId, Order order, double amount, String paymentMethod, String transactionId, Status status,
                   LocalDateTime paymentDate) {
        this.paymentId = paymentId;
        this.order = order;
        this.setAmount(amount);
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    public Payment(int paymentId, Order order, double amount, String paymentMethod) {
        this(paymentId, order, amount, paymentMethod, UUID.randomUUID().toString(), Status.PENDING,
                LocalDateTime.now());
    }

    // Getters

    public int getPaymentId() {
        return paymentId;
    }

    public Order getOrder() {
        return order;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public int getPointsGained() {
        Customer customer = order.getCustomer();
        return (int) (amount * customer.PointRate());
    }

    public Customer getCustomer() {
        return order.getCustomer();
    }

    // Setters
    public void setAmount(double amount) {
        Customer customer = order.getCustomer();
        if (customer.getType() == IRewardable.Type.FIRST) {
            this.amount = amount;
        } else {
            double discountAmount = amount * (customer.DiscountRate() / 100);
            this.amount = amount - discountAmount;
        }
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentDate = paymentTime;
    }

}