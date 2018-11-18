package com.capgemini.onlineStore.persistence.to;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class OrderProductTO extends AbstractTO {

    @NotNull
    private Integer amount;
    @NotNull
    private ProductTO product;
    @NotNull
    private TransactionTO transaction;

    public OrderProductTO() {
    }

    public OrderProductTO(Integer amount, ProductTO product, TransactionTO transaction) {
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

    public ProductTO getProduct() {
        return product;
    }

    public void setProduct(ProductTO product) {
        this.product = product;
    }

    public TransactionTO getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionTO transaction) {
        this.transaction = transaction;
    }

    public static class OrderProductTOBuilder {
        private Integer amount;
        private ProductTO product;
        private TransactionTO transaction;

        public OrderProductTOBuilder() {
        }

        public OrderProductTOBuilder(Integer amount, ProductTO product, TransactionTO transaction) {
            this.amount = amount;
            this.product = product;
            this.transaction = transaction;
        }

        public OrderProductTOBuilder withAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public OrderProductTOBuilder withProduct(ProductTO product) {
            this.product = product;
            return this;
        }

        public OrderProductTOBuilder withTransaction(TransactionTO transaction) {
            this.transaction = transaction;
            return this;
        }

        public OrderProductTO build() {
            return new OrderProductTO(amount, product, transaction);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductTO that = (OrderProductTO) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(product, that.product) &&
                Objects.equals(transaction, that.transaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, product, transaction);
    }

    @Override
    public String toString() {
        return "OrderProductTO{" +
                "amount=" + amount +
                ", product=" + product +
                ", transaction=" + transaction +
                '}';
    }
}

