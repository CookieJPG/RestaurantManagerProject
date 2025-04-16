package com.example.restaurantmanagerproject.model;

public class CTVIP extends Customer {

    public CTVIP(String name) {
        super(name, Type.First, null, null);
        setId();
    }

    public CTVIP(String name, String email, String phone) {
        super(name, Type.First, email, phone);
        setId();
    }

    public CTVIP(String id, String name, String email, String phone, double loyaltyPoints) {
        super(id, name, Type.First, email, phone, loyaltyPoints);
    }

    @Override
    public Double PointRate() {
        return 11.0;
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
