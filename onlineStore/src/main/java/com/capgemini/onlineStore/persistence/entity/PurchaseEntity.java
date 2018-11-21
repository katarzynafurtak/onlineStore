package com.capgemini.onlineStore.persistence.entity;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.google.common.collect.Sets;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PURCHASE")
public class PurchaseEntity extends AbstractEntity {

    @Column
    private LocalDateTime date;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany
    private Set<OrderProductEntity> orders = new HashSet<>();

    public PurchaseEntity() {
    }

    public PurchaseEntity(Status status, Set<OrderProductEntity> orders, LocalDateTime date) {
        this.status = status;
        this.orders = orders;
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<OrderProductEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderProductEntity> orders) {
        this.orders = orders;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public static class PurchaseEntityBuilder {
        private Status status;
        private Set<OrderProductEntity> orders;
        private LocalDateTime date;

        public PurchaseEntityBuilder() {
            orders = Sets.newHashSet();
        }

        public PurchaseEntityBuilder(Status status, Set<OrderProductEntity> orders, LocalDateTime date) {
            this.status = status;
            this.orders = orders;
            this.date = date;
        }

        public PurchaseEntity.PurchaseEntityBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public PurchaseEntity.PurchaseEntityBuilder withOrders(Set<OrderProductEntity> ordersToBeAdded) {
            this.orders.addAll(ordersToBeAdded);
            return this;
        }

        public PurchaseEntity.PurchaseEntityBuilder withDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public PurchaseEntity build() {
            return new PurchaseEntity(status, orders, date);
        }
    }
}
