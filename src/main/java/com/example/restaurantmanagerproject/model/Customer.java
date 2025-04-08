package com.example.restaurantmanagerproject.model;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

public abstract class Customer implements IRewardable {
    private String id;
    private String name;
    private Type type;

    public Customer(String name, Type type) {
        this.name = name;
        this.type = type;
        setId();
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public Type getType() { return type; }

    // Setters
    public void setId() {
        SecureRandom random = new SecureRandom();
        switch (type) {
            case Type.First -> {
                byte[] bytes = new byte[9];
                random.nextBytes(bytes);
                String randomStr = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes).substring(0, 11);
                id = "1" + randomStr;
            }
            case Type.Regular -> {
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
    public void setType(Type newType) { type = Objects.requireNonNullElse(newType, Type.First); }
}
