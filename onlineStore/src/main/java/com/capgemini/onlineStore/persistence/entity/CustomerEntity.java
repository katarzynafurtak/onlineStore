package com.capgemini.onlineStore.persistence.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity extends AbstractEntity {

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 50)
    private String street;

    @Column(name = "number_of_house", nullable = false, length = 5)
    private String numberOfHouse;

    @Column(name = "number_of_flat", length = 5)
    private Integer numberOfFlat;

    @Column(nullable = false, length = 6)
    private String postcode;

    @OneToMany(mappedBy = "customer")
    private Set<TransactionEntity> transactions = new HashSet<>();

    public CustomerEntity() {
    }

    public CustomerEntity(String firstName, String lastName, LocalDate dateOfBirth, String email, String city, String street, String numberOfHouse, Integer numberOfFlat, String postcode, Set<TransactionEntity> transactions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.city = city;
        this.street = street;
        this.numberOfHouse = numberOfHouse;
        this.numberOfFlat = numberOfFlat;
        this.postcode = postcode;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumberOfHouse() {
        return numberOfHouse;
    }

    public void setNumberOfHouse(String numberOfHouse) {
        this.numberOfHouse = numberOfHouse;
    }

    public Integer getNumberOfFlat() {
        return numberOfFlat;
    }

    public void setNumberOfFlat(Integer numberOfFlat) {
        this.numberOfFlat = numberOfFlat;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Set<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionEntity> transactions) {
        this.transactions = transactions;
    }


}
