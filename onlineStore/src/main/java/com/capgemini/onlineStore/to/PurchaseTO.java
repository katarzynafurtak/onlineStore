package com.capgemini.onlineStore.to;

import com.capgemini.onlineStore.persistence.datatype.Status;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

public class PurchaseTO extends AbstractTO {

    @NotNull
    private Status status;
    @NotNull
    private CustomerTO customer;
    @NotNull
    private Set<OrderProductTO> orders;

    public PurchaseTO() {
    }

    public PurchaseTO(Status status, CustomerTO customer, Set<OrderProductTO> orders) {
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

    public CustomerTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerTO customer) {
        this.customer = customer;
    }

    public Set<OrderProductTO> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderProductTO> orders) {
        this.orders = orders;
    }

    public static class PurchaseTOBuilder {
        private Status status;
        private CustomerTO customer;
        private Set<OrderProductTO> orders;

        public PurchaseTOBuilder() {
        }

        public PurchaseTOBuilder(Status status, CustomerTO customer, Set<OrderProductTO> orders) {
            this.status = status;
            this.customer = customer;
            this.orders = orders;
        }

        public PurchaseTOBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public PurchaseTOBuilder withCustomer(CustomerTO customer) {
            this.customer = customer;
            return this;
        }

        public PurchaseTOBuilder withOrders(Set<OrderProductTO> ordersToBeAdded) {
            this.orders.addAll(ordersToBeAdded);
            return this;
        }

        public PurchaseTO build() {
            return new PurchaseTO(status, customer, orders);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseTO that = (PurchaseTO) o;
        return status == that.status &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, customer, orders);
    }

    @Override
    public String toString() {
        return "PurchaseTO{" +
                "status=" + status +
                ", customer=" + customer +
                ", orders=" + orders +
                '}';
    }
}
