package com.example.restaurantmanagerproject.model;

public class CTRegular extends Customer {

    public CTRegular(String name) {
        super(name, Type.First, null, null);
        setId();
    }

    public CTRegular(String name, String email, String phone) {
        super(name, Type.First, email, phone);
        setId();
    }

    public CTRegular(String id, String name, String email, String phone, double loyaltyPoints) {
        super(id, name, Type.First, email, phone, loyaltyPoints);
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
