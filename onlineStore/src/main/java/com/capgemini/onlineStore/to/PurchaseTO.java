package com.capgemini.onlineStore.to;

import com.capgemini.onlineStore.persistence.datatype.Status;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PurchaseTO extends AbstractTO {

    @NotNull
    private LocalDateTime date;

    @NotNull
    private Status status;

    @NotNull
    private List<OrderProductTO> orders;

    public PurchaseTO() {
    }

    public PurchaseTO(Status status, List<OrderProductTO> orders, LocalDateTime date) {
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

    public List<OrderProductTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderProductTO> orders) {
        this.orders = orders;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public static class PurchaseTOBuilder {
        private Status status;
        private List<OrderProductTO> orders;
        private LocalDateTime date;

        public PurchaseTOBuilder() {
        }

        public PurchaseTOBuilder(Status status, List<OrderProductTO> orders, LocalDateTime date) {
            this.status = status;
            this.orders = orders;
            this.date = date;
        }

        public PurchaseTOBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public PurchaseTOBuilder withOrders(List<OrderProductTO> ordersToBeAdded) {
            this.orders = ordersToBeAdded;
            return this;
        }

        public PurchaseTOBuilder withOrder(OrderProductTO order) {
            if (orders == null) {
                orders = new ArrayList<>();
            }
            orders.add(order);
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
}


