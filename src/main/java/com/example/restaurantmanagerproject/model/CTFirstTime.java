package com.example.restaurantmanagerproject.model;

public class CTFirstTime extends Customer {

    public CTFirstTime(String name) {
        super(name, Type.FIRST, null, null);
        setId();
    }

    public CTFirstTime(String name, String email, String phone) {
        super(name, Type.FIRST, email, phone);
        setId();
    }

    public CTFirstTime(String id, String name, String email, String phone, double loyaltyPoints) {
        super(id, name, Type.FIRST, email, phone, loyaltyPoints);
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

}
