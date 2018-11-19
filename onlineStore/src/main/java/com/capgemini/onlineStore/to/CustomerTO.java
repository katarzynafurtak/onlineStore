package com.capgemini.onlineStore.to;

import com.capgemini.onlineStore.persistence.embedded.ContactData;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class CustomerTO extends AbstractTO {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private ContactData contactData;
    @NotNull
    private Set<TransactionTO> transactions;

    public CustomerTO() {
    }

    public CustomerTO(String firstName, String lastName, LocalDate dateOfBirth, ContactData contactData, Set<TransactionTO> transactions) {
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

    public Set<TransactionTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionTO> transactions) {
        this.transactions = transactions;
    }

    public static class CustomerTOBuilder {
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private ContactData contactData;
        private Set<TransactionTO> transactions;

        public CustomerTOBuilder() {
        }

        public CustomerTOBuilder(String firstName, String lastName, LocalDate dateOfBirth, ContactData contactData, Set<TransactionTO> transactions) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.dateOfBirth = dateOfBirth;
            this.contactData = contactData;
            this.transactions = transactions;
        }

        public CustomerTOBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerTOBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerTOBuilder withDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public CustomerTOBuilder withContactData(ContactData contactData) {
            this.contactData = contactData;
            return this;
        }

        public CustomerTOBuilder withTransactions(Set<TransactionTO> transactionsToBeAdded) {
            this.transactions.addAll(transactionsToBeAdded);
            return this;
        }

        public CustomerTO build() {
            return new CustomerTO(firstName, lastName, dateOfBirth, contactData, transactions);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerTO that = (CustomerTO) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(contactData, that.contactData) &&
                Objects.equals(transactions, that.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth, contactData, transactions);
    }

    @Override
    public String toString() {
        return "CustomerTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", contactData=" + contactData +
                ", transactions=" + transactions +
                '}';
    }
}

