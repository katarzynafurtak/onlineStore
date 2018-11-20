package com.capgemini.onlineStore.persistence.entity;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.to.CustomerTO;
import com.capgemini.onlineStore.to.OrderProductTO;
import com.capgemini.onlineStore.to.PurchaseTO;
import com.querydsl.core.types.Order;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PURCHASE")
public class PurchaseEntity extends AbstractEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "purchase")
    private Set<OrderProductEntity> orders = new HashSet<>();

    public PurchaseEntity() {
    }

    public PurchaseEntity(Status status, CustomerEntity customer, Set<OrderProductEntity> orders) {
        this.status = status;
        this.customer = customer;
        this.orders = orders;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public Set<OrderProductEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderProductEntity> orders) {
        this.orders = orders;
    }

    public static class PurchaseEntityBuilder {
        private Status status;
        private CustomerEntity customer;
        private Set<OrderProductEntity> orders;

        public PurchaseEntityBuilder() {
        }

        public PurchaseEntityBuilder(Status status, CustomerEntity customer, Set<OrderProductEntity> orders) {
            this.status = status;
            this.customer = customer;
            this.orders = orders;
        }

        public PurchaseEntity.PurchaseEntityBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public PurchaseEntity.PurchaseEntityBuilder withCustomer(CustomerEntity customer) {
            this.customer = customer;
            return this;
        }

        public PurchaseEntity.PurchaseEntityBuilder withOrders(Set<OrderProductEntity> ordersToBeAdded) {
            this.orders.addAll(ordersToBeAdded);
            return this;
        }

        public PurchaseEntity build() {
            return new PurchaseEntity(status, customer, orders);
        }
    }
}
