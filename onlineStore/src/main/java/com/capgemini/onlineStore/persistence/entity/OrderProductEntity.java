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

    @ManyToOne
    @JoinColumn(name = "purchase")
    private PurchaseEntity purchase;

    public OrderProductEntity() {
    }

    public OrderProductEntity(Integer amount, ProductEntity product, PurchaseEntity purchase) {
        this.amount = amount;
        this.product = product;
        this.purchase = purchase;
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

    public PurchaseEntity getPurchase() {
        return purchase;
    }

    public void setPurchase(PurchaseEntity purchase) {
        this.purchase = purchase;
    }

    public static class OrderProductEntityBuilder {
        private Integer amount;
        private ProductEntity product;
        private PurchaseEntity purchase;

        public OrderProductEntityBuilder() {
        }

        public OrderProductEntityBuilder(Integer amount, ProductEntity product, PurchaseEntity purchase) {
            this.amount = amount;
            this.product = product;
            this.purchase = purchase;
        }

        public OrderProductEntity.OrderProductEntityBuilder withAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public OrderProductEntity.OrderProductEntityBuilder withProduct(ProductEntity product) {
            this.product = product;
            return this;
        }

        public OrderProductEntity.OrderProductEntityBuilder withPurchase(PurchaseEntity purchase) {
            this.purchase = purchase;
            return this;
        }

        public OrderProductEntity build() {
            return new OrderProductEntity(amount, product, purchase);
        }
    }
}
