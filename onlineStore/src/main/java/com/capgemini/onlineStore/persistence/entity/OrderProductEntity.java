package com.capgemini.onlineStore.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProductEntity extends AbstractEntity {

    @Column
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "product")
    private ProductEntity product;

    public OrderProductEntity() {
    }

    public OrderProductEntity(Integer amount, ProductEntity product) {
        this.amount = amount;
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }


    public static class OrderProductEntityBuilder {
        private Integer amount;
        private ProductEntity product;

        public OrderProductEntityBuilder() {
        }

        public OrderProductEntityBuilder(Integer amount, ProductEntity product) {
            this.amount = amount;
            this.product = product;
        }

        public OrderProductEntity.OrderProductEntityBuilder withAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public OrderProductEntity.OrderProductEntityBuilder withProduct(ProductEntity product) {
            this.product = product;
            return this;
        }

        public OrderProductEntity build() {
            return new OrderProductEntity(amount, product);
        }
    }
}
