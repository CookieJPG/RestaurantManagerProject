package com.example.restaurantmanagerproject.model;

public class CTFirstTime extends Customer implements IRewardable {

    public CTFirstTime(String name) {
        super(name, Type.First);
    }

    @Override
    public Double PointRate() {
        return 0.0;
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
