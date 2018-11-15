package com.capgemini.onlineStore.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProduct extends AbstractEntity {

    @Column(nullable = false, length = 5)
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "order_product_id")
    private OrderProduct orderProduct;

    public OrderProduct() {
    }

    public OrderProduct(Integer amount, ProductEntity product, OrderProduct orderProduct) {
        this.amount = amount;
        this.product = product;
        this.orderProduct = orderProduct;
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

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }
}
