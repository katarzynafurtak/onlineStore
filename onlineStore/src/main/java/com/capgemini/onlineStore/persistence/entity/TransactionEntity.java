package com.capgemini.onlineStore.persistence.entity;

import com.capgemini.onlineStore.persistence.datatype.Status;

import javax.persistence.*;

@Entity
@Table(name = "TRANSACTION")
public class TransactionEntity extends AbstractEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer")
    private CustomerEntity customer;

    public TransactionEntity() {
    }

    public TransactionEntity(Status status, CustomerEntity customer) {
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
