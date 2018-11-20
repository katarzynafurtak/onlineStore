package com.capgemini.onlineStore.to;

import com.capgemini.onlineStore.to.ProductTO;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class OrderProductTO extends AbstractTO {

    @NotNull
    private Integer amount;
    @NotNull
    private ProductTO product;
    @NotNull
    private PurchaseTO purchase;

    public OrderProductTO() {
    }

    public OrderProductTO(Integer amount, ProductTO product, PurchaseTO purchase) {
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

    public ProductTO getProduct() {
        return product;
    }

    public void setProduct(ProductTO product) {
        this.product = product;
    }

    public PurchaseTO getPurchase() {
        return purchase;
    }

    public void setPurchase(PurchaseTO purchase) {
        this.purchase = purchase;
    }

    public static class OrderProductTOBuilder {
        private Integer amount;
        private ProductTO product;
        private PurchaseTO purchase;

        public OrderProductTOBuilder() {
        }

        public OrderProductTOBuilder(Integer amount, ProductTO product, PurchaseTO purchase) {
            this.amount = amount;
            this.product = product;
            this.purchase = purchase;
        }

        public OrderProductTOBuilder withAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public OrderProductTOBuilder withProduct(ProductTO product) {
            this.product = product;
            return this;
        }

        public OrderProductTOBuilder withPurchase(PurchaseTO purchase) {
            this.purchase = purchase;
            return this;
        }

        public OrderProductTO build() {
            return new OrderProductTO(amount, product, purchase);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductTO that = (OrderProductTO) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(product, that.product) &&
                Objects.equals(purchase, that.purchase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, product, purchase);
    }

    @Override
    public String toString() {
        return "OrderProductTO{" +
                "amount=" + amount +
                ", product=" + product +
                ", purchase=" + purchase +
                '}';
    }
}

