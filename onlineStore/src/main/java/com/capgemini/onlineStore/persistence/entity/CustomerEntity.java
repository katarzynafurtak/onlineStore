package com.capgemini.onlineStore.persistence.entity;

import com.capgemini.onlineStore.persistence.embedded.ContactData;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity extends AbstractEntity {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private LocalDate dateOfBirth;

    @Embedded
    private ContactData contactData;

    @OneToMany(mappedBy = "customer")
    private Set<TransactionEntity> transactions = new HashSet<>();

    public CustomerEntity() {
    }

    public CustomerEntity(String firstName, String lastName, LocalDate dateOfBirth, ContactData contactData, Set<TransactionEntity> transactions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.contactData = contactData;
        this.transactions = transactions;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ContactData getContactData() {
        return contactData;
    }

    public void setContactData(ContactData contactData) {
        this.contactData = contactData;
    }

    public Set<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionEntity> transactions) {
        this.transactions = transactions;
    }


}
