package com.example.restaurantmanagerproject.model;

public class CTVIP extends Customer implements IRewardable {

    public CTVIP(String name) {
        super(name, Type.VIP);
    }

    @Override
    public Double PointRate() {
        return 0.0;
    }

    @Override
    public Double DiscountRate() {
        return 10.0;
    }

    @Override
    public Double SubscriptionPrice() {
        return 29.99;
    }

    @Override
    public void Upgrade() {

    }
}
