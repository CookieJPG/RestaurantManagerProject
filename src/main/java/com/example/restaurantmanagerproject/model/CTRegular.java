package com.example.restaurantmanagerproject.model;

public class CTRegular extends Customer implements IRewardable {

    public CTRegular(String name) {
        super(name, Type.Regular);
    }

    @Override
    public Double PointRate() {
        return 9.0;
    }

    @Override
    public Double DiscountRate() {
        return 5.0;
    }

    @Override
    public Double SubscriptionPrice() {
        return 14.99;
    }

    @Override
    public void Upgrade() {

    }
}
