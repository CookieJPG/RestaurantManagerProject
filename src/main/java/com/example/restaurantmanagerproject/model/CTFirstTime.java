package com.example.restaurantmanagerproject.model;

public class CTFirstTime extends Customer implements IRewardable {

    public CTFirstTime(String name) {
        super(name, Type.First);
    }

    @Override
    public Double PointRate() {
        // 1000 points = 1 dollar | 10 points = 1 cent
        // For 20 dollars spent you get 150
        return 7.5;
    }

    @Override
    public Double DiscountRate() {
        return 0.0;
    }

    @Override
    public Double SubscriptionPrice() {
        return 0.0;
    }

    @Override
    public void Upgrade() {

    }
}
