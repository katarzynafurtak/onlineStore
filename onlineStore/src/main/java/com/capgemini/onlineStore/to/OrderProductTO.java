package com.capgemini.onlineStore.to;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class OrderProductTO extends AbstractTO {

    @NotNull
    private Integer amount;
    @NotNull
    private ProductTO product;
    @NotNull
    private BigDecimal sellPrice;

    public OrderProductTO() {
    }

    public OrderProductTO(Integer amount, ProductTO product, BigDecimal sellPrice) {
        this.amount = amount;
        this.product = product;
        this.sellPrice = calculateSellPrice();
    }

    private BigDecimal calculateSellPrice() {
        return product.getPrice()
                .multiply(BigDecimal.valueOf(100)
                        .divide(BigDecimal.valueOf(100).subtract(product.getMarge() ), RoundingMode.HALF_UP));
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

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductTO that = (OrderProductTO) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, product);
    }

    @Override
    public String toString() {
        return "OrderProductTO{" +
                "amount=" + amount +
                ", product=" + product +
                '}';
    }

    public static class OrderProductTOBuilder {
        private Integer amount;
        private ProductTO product;
        private BigDecimal sellPrice;

        public OrderProductTOBuilder() {
        }

        public OrderProductTOBuilder(Integer amount, ProductTO product, BigDecimal sellPrice) {
            this.amount = amount;
            this.product = product;
            this.sellPrice = sellPrice;
        }

        public OrderProductTOBuilder withAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public OrderProductTOBuilder withProduct(ProductTO product) {
            this.product = product;
            return this;
        }

        public OrderProductTOBuilder withSellPrice(BigDecimal sellPrice) {
            this.product = product;
            return this;
        }

        public OrderProductTO build() {
            return new OrderProductTO(amount, product, sellPrice);
        }
    }
}

