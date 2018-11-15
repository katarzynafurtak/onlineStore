package com.capgemini.onlineStore.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT")
public class ProductEntity extends AbstractEntity {

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 10)
    private BigDecimal marge;

    @Column(nullable = false, length = 10)
    private BigDecimal price;

    @Column(nullable = false, length = 10)
    private BigDecimal weight;

    public ProductEntity() {
    }

    public ProductEntity(String name, BigDecimal marge, BigDecimal price, BigDecimal weight) {
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
