package com.capgemini.onlineStore.persistence.embedded;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import javax.persistence.Embeddable;

@Embeddable
public class ContactData {

    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 50)
    private String street;

    @Column(length = 5, nullable = false)
    private String numberOfHouse;

    @Column(length = 5)
    private Integer numberOfFlat;

    @Column(length = 6, nullable = false)
    private String postcode;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 9, nullable = false)
    private String phoneNumber;

    public ContactData() {
    }

    public ContactData(String city, String street, String numberOfHouse, Integer numberOfFlat, String postcode,
                       String email, String phoneNumber) {
        this.city = city;
        this.street = street;
        this.numberOfHouse = numberOfHouse;
        this.numberOfFlat = numberOfFlat;
        this.postcode = postcode;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static class ContactDataBuilder {

        private String city;
        private String street;
        private String numberOfHouse;
        private Integer numberOfFlat;
        private String postcode;
        private String email;
        private String phoneNumber;

        public ContactDataBuilder() {
            super();
        }

        public ContactDataBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public ContactDataBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public ContactDataBuilder withNumberOfHouse(String numberOfHouse) {
            this.numberOfHouse = numberOfHouse;
            return this;
        }

        public ContactDataBuilder withNumberOfFlat(Integer numberOfFlat) {
            this.numberOfFlat = numberOfFlat;
            return this;
        }

        public ContactDataBuilder withPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public ContactDataBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ContactDataBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ContactData build() {
            return new ContactData(city, street, numberOfHouse, numberOfFlat, postcode, email, phoneNumber);
        }
    }
}