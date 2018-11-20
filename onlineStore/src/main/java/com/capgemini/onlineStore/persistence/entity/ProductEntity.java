package com.capgemini.onlineStore.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "SELECT c FROM ProductEntity c"),
        @NamedQuery(name = "Product.findByName", query = "SELECT c FROM ProductEntity c WHERE c.name = :name "),

        @NamedQuery(name = "Product.findByPrimaryKey", query = "SELECT p FROM ProductEntity p WHERE p.id = :id"),
})
@Entity
@Table(name = "PRODUCT")
public class ProductEntity extends AbstractEntity {

    @Column
    private String name;

    @Column
    private BigDecimal marge;

    @Column
    private BigDecimal price;

    @Column
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

    public static class ProductEntityBuilder {
        private String name;
        private BigDecimal marge;
        private BigDecimal price;
        private BigDecimal weight;

        public ProductEntityBuilder() {
        }

        public ProductEntityBuilder(String name, BigDecimal marge, BigDecimal price, BigDecimal weight) {
            this.name = name;
            this.marge = marge;
            this.price = price;
            this.weight = weight;
        }

        public ProductEntity.ProductEntityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductEntity.ProductEntityBuilder withMarge(BigDecimal marge) {
            this.marge = marge;
            return this;
        }

        public ProductEntity.ProductEntityBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductEntity.ProductEntityBuilder withWeight(BigDecimal weight) {
            this.weight = weight;
            return this;
        }

        public ProductEntity build() {
            return new ProductEntity(name, marge, price, weight);
        }
    }
}
