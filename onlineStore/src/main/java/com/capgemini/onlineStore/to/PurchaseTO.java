package com.capgemini.onlineStore.to;

import com.capgemini.onlineStore.persistence.datatype.Status;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class PurchaseTO extends AbstractTO {

    @NotNull
    private LocalDateTime date;

    @NotNull
    private Status status;

    @NotNull
    private Set<OrderProductTO> orders;

    public PurchaseTO() {
    }

    public PurchaseTO(Status status, Set<OrderProductTO> orders, LocalDateTime date) {
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

    public Set<OrderProductTO> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderProductTO> orders) {
        this.orders = orders;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public static class PurchaseTOBuilder {
        private Status status;
        private Set<OrderProductTO> orders;
        private LocalDateTime date;

        public PurchaseTOBuilder() {
        }

        public PurchaseTOBuilder(Status status, Set<OrderProductTO> orders, LocalDateTime date) {
            this.status = status;
            this.orders = orders;
            this.date = date;
        }

        public PurchaseTOBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public PurchaseTOBuilder withOrders(Set<OrderProductTO> ordersToBeAdded) {
            this.orders.addAll(ordersToBeAdded);
            return this;
        }

        public PurchaseTOBuilder withDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public PurchaseTO build() {
            return new PurchaseTO(status, orders, date);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseTO that = (PurchaseTO) o;
        return status == that.status &&
                Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, orders);
    }

    @Override
    public String toString() {
        return "PurchaseTO{" +
                "status=" + status +
                ", orders=" + orders +
                '}';
    }
}
