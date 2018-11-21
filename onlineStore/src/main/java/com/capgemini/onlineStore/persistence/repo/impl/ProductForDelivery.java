package com.capgemini.onlineStore.persistence.repo.impl;

public class ProductForDelivery {
    private String name;
    private int amount;

    public ProductForDelivery(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
