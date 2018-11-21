package com.capgemini.onlineStore.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProductEntity extends AbstractEntity {

    @Column
    private Integer amount;

    @Column
    private BigDecimal sellPrice;

    @ManyToOne
    @JoinColumn(name = "product")
    private ProductEntity product;

    public OrderProductEntity() {
    }

    public OrderProductEntity(Integer amount, ProductEntity product, BigDecimal sellPrice) {
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

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public static class OrderProductEntityBuilder {
        private Integer amount;
        private ProductEntity product;
        private BigDecimal sellPrice;

        public OrderProductEntityBuilder() {
        }

        public OrderProductEntityBuilder(Integer amount, ProductEntity product, BigDecimal sellPrice) {
            this.amount = amount;
            this.product = product;
            this.sellPrice = sellPrice;
        }

        public OrderProductEntity.OrderProductEntityBuilder withAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public OrderProductEntity.OrderProductEntityBuilder withProduct(ProductEntity product) {
            this.product = product;
            return this;
        }

        public OrderProductEntity.OrderProductEntityBuilder withSellPrice(BigDecimal sellPrice) {
            this.sellPrice = sellPrice;
            return this;
        }

        public OrderProductEntity build() {
            return new OrderProductEntity(amount, product, sellPrice);
        }
    }
}
