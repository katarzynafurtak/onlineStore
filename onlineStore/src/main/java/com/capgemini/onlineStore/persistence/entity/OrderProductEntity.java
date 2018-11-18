package com.capgemini.onlineStore.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProductEntity extends AbstractEntity {

    @Column
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private TransactionEntity transaction;

    public OrderProductEntity() {
    }

    public OrderProductEntity(Integer amount, ProductEntity product, TransactionEntity transaction) {
        this.amount = amount;
        this.product = product;
        this.transaction = transaction;
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

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }
}
