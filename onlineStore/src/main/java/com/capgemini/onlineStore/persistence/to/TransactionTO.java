package com.capgemini.onlineStore.persistence.to;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.persistence.entity.CustomerEntity;

public class TransactionTO extends AbstractTO {

    private Status status;
    private CustomerEntity customer;

    public TransactionTO() {
    }

    public TransactionTO(Status status, CustomerEntity customer) {
        this.status = status;
        this.customer = customer;
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
}
