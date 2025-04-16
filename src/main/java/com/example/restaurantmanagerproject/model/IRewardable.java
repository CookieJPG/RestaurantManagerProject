package com.example.restaurantmanagerproject.model;

public interface IRewardable {
    public enum Type {
        FIRST, REGULAR, VIP
    }

    Double PointRate();

    Double DiscountRate();

    Double SubscriptionPrice();

}
