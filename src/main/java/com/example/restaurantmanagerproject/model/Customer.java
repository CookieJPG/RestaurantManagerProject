package com.example.restaurantmanagerproject.model;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

public abstract class Customer implements IRewardable {
    private String id;
    private String name;
    private Type type;
    private String email;
    private String phone;
    private double loyaltyPoints;

    // Constructor for creating a customer with an ID
    public Customer(String id, String name, Type type, String email, String phone, double loyaltyPoints) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.email = email;
        this.phone = phone;
        this.loyaltyPoints = loyaltyPoints;
    }

    public Customer(String name, Type type) {
        this(null, name, type, null, null, 0);
        setId();
    }

    public Customer(String name, Type type, String email, String phone) {
        this(null, name, type, email, phone, 0);
        setId();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    // Setters
    public void setId() {
        SecureRandom random = new SecureRandom();
        switch (type) {
            case Type.FIRST -> {
                byte[] bytes = new byte[9];
                random.nextBytes(bytes);
                String randomStr = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes).substring(0, 11);
                id = "1" + randomStr;
            }
            case Type.REGULAR -> {
                byte[] bytes = new byte[9];
                random.nextBytes(bytes);
                String randomStr = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes).substring(0, 11);
                id = "2" + randomStr;
            }
            case Type.VIP -> {
                byte[] bytes = new byte[9];
                random.nextBytes(bytes);
                String randomStr = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes).substring(0, 11);
                id = "3" + randomStr;
            }
        }
    }

    public void setName(String newName) {
        if (newName != null) {
            if (newName.length() > 3) {
                name = newName;
            }
        }
    }

    public void setType(Type newType) {
        type = Objects.requireNonNullElse(newType, Type.FIRST);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null) {
            if (email.contains("@")) {
                this.email = email;
            }
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null) {
            if (phone.length() == 10) {
                this.phone = phone;
            }
        }
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addLoyaltyPoints(double loyaltyPoints) {
        this.loyaltyPoints += loyaltyPoints;
    }

    public void removeLoyaltyPoints(double loyaltyPoints) {
        this.loyaltyPoints -= loyaltyPoints;
    }

}
