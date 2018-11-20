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

    @OneToMany
    private Set<PurchaseEntity> purchases = new HashSet<>();

    public CustomerEntity() {
    }

    public CustomerEntity(String firstName, String lastName, LocalDate dateOfBirth, ContactData contactData, Set<PurchaseEntity> purchases) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.contactData = contactData;
        this.purchases = purchases;
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

    public Set<PurchaseEntity> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<PurchaseEntity> purchases) {
        this.purchases = purchases;
    }

    public static class CustomerEntityBuilder {
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private ContactData contactData;
        private Set<PurchaseEntity> purchases = new HashSet<>();

        public CustomerEntityBuilder() {
        }

        public CustomerEntityBuilder(String firstName, String lastName, LocalDate dateOfBirth, ContactData contactData, Set<PurchaseEntity> purchases) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.dateOfBirth = dateOfBirth;
            this.contactData = contactData;
            this.purchases = purchases;
        }

        public CustomerEntityBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerEntityBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerEntityBuilder withDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public CustomerEntityBuilder withContactData(ContactData contactData) {
            this.contactData = contactData;
            return this;
        }

        public CustomerEntityBuilder withPurchases(Set<PurchaseEntity> purchasesToBeAdded) {
            this.purchases.addAll(purchasesToBeAdded);
            return this;
        }

        public CustomerEntity build() {
            return new CustomerEntity(firstName, lastName, dateOfBirth, contactData, purchases);
        }
    }
}
