package com.example.restaurantmanagerproject.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Payment {
    private double amount;
    private Status status;
    private final Order order;
    private final String paymentMethod;
    private final String transactionId;
    private final LocalDate paymentDate;

    public Payment(Order order, double amount, String paymentMethod) {
        this.order = order;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transactionId = UUID.randomUUID().toString();
        this.status = Status.PENDING;
        this.paymentDate = LocalDate.now();
    }

    // Getters
    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    public Order getOrder() {
        return order;
    }
    public double getAmount() {
        return amount;
    }
    public Status getStatus() {
        return status;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public String getTransactionId() {
        return transactionId;
    }

    // Setters
    public void setAmount(double amount) {
        Customer customer = order.getCustomer();
        if (customer.getType() == IRewardable.Type.First) {
            this.amount = amount;
        } else {
            double discountAmount = amount * (customer.DiscountRate() / 100);
            this.amount = amount - discountAmount;
        }
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
