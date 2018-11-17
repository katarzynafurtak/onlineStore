package com.capgemini.onlineStore.persistence.to;

import com.capgemini.onlineStore.persistence.entity.ProductEntity;
import com.capgemini.onlineStore.persistence.entity.TransactionEntity;

public class OrderProductTO extends AbstractTO {

    private Integer amount;
    private ProductEntity product;
    private TransactionEntity transaction;

    public OrderProductTO() {
    }

    public OrderProductTO(Integer amount, ProductEntity product, TransactionEntity transaction) {
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
