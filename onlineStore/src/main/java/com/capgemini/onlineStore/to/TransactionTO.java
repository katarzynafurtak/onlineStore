package com.capgemini.onlineStore.to;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.to.CustomerTO;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class TransactionTO extends AbstractTO {

    @NotNull
    private Status status;
    @NotNull
    private CustomerTO customer;

    public TransactionTO() {
    }

    public TransactionTO(Status status, CustomerTO customer) {
        this.status = status;
        this.customer = customer;
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

    public static class TransactionTOBuilder {
        private Status status;
        private CustomerTO customer;

        public TransactionTOBuilder() {
        }

        public TransactionTOBuilder(Status status, CustomerTO customer) {
            this.status = status;
            this.customer = customer;
        }

        public TransactionTOBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public TransactionTOBuilder withCustomer(CustomerTO customer) {
            this.customer = customer;
            return this;
        }

        public TransactionTO build() {
            return new TransactionTO(status, customer);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionTO that = (TransactionTO) o;
        return status == that.status &&
                Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, customer);
    }

    @Override
    public String toString() {
        return "TransactionTO{" +
                "status=" + status +
                ", customer=" + customer +
                '}';
    }
}
