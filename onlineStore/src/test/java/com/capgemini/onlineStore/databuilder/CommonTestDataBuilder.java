package com.capgemini.onlineStore.databuilder;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.persistence.embedded.ContactData;
import com.capgemini.onlineStore.persistence.entity.CustomerEntity;
import com.capgemini.onlineStore.persistence.entity.OrderProductEntity;
import com.capgemini.onlineStore.persistence.entity.ProductEntity;
import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class CommonTestDataBuilder {

    public static CustomerEntity.CustomerEntityBuilder commonCustomerBuilder() {
        ContactData contactData = new ContactData.ContactDataBuilder()
                .withCity("Warszawa")
                .withStreet("Warszawska")
                .withNumberOfHouse("5")
                .withNumberOfFlat(4)
                .withPostcode("00-467")
                .withEmail("warszawa@rentalcar.pl")
                .withPhoneNumber("609456777")
                .build();

        return new CustomerEntity.CustomerEntityBuilder()
                .withFirstName("Adam")
                .withLastName("Mickiewicz")
                .withContactData(contactData)
                .withDateOfBirth(LocalDate.of(1988, Month.JANUARY, 14));
    }

    public static OrderProductEntity.OrderProductEntityBuilder commonOrderProductBuilder() {
        return new OrderProductEntity.OrderProductEntityBuilder()
                .withAmount(1);
    }

    public static ProductEntity.ProductEntityBuilder commonProductBuilder() {
        return new ProductEntity.ProductEntityBuilder()
                .withName("Telewizor")
                .withPrice(BigDecimal.valueOf(5000))
                .withMarge(BigDecimal.valueOf(40))
                .withWeight(BigDecimal.valueOf(7));
    }

    public static PurchaseEntity.PurchaseEntityBuilder commonPurchaseBuilder() {
        return new PurchaseEntity.PurchaseEntityBuilder()
                .withStatus(Status.COMPLETED);
    }

}
