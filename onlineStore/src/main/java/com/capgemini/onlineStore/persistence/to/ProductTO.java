package com.capgemini.onlineStore.persistence.to;

import java.math.BigDecimal;

public class ProductTO extends AbstractTO {

    private String name;
    private BigDecimal marge;
    private BigDecimal price;
    private BigDecimal weight;

    public ProductTO() {
    }

    public ProductTO(String name, BigDecimal marge, BigDecimal price, BigDecimal weight) {
        this.name = name;
        this.marge = marge;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMarge() {
        return marge;
    }

    public void setMarge(BigDecimal marge) {
        this.marge = marge;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
