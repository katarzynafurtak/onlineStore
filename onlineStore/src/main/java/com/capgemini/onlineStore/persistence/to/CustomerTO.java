package com.capgemini.onlineStore.persistence.to;

import com.capgemini.onlineStore.persistence.entity.ContactData;
import com.capgemini.onlineStore.persistence.entity.TransactionEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CustomerTO extends AbstractTO {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private ContactData contactData;
    private Set<TransactionEntity> transactions = new HashSet<>();

    public CustomerTO() {
    }

    public CustomerTO(String firstName, String lastName, LocalDate dateOfBirth, ContactData contactData, Set<TransactionEntity> transactions) {
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
