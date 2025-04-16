package com.example.restaurantmanagerproject.model;

public interface IRewardable {
    public enum Type {
        First, Regular, VIP
    }

    Double PointRate();

    Double DiscountRate();

    Double SubscriptionPrice();

    /*
     * TODO: Check if this is going to have a payment from the Payment class,
     * if it does we must relate it to Payment before upgrading the customer.
     * Same thing for the Customer DAO since it must replace a customer to upgrade
     * it.
     */
    void Upgrade();
}
