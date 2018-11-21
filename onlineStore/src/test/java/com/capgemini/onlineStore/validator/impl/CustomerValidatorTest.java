package com.capgemini.onlineStore.validator.impl;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.to.CustomerTO;
import com.capgemini.onlineStore.to.PurchaseTO;
import com.capgemini.onlineStore.validator.exception.InvalidPurchaseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.capgemini.onlineStore.databuilder.CommonValidatorTestBuilder.highPricePurchase;
import static com.capgemini.onlineStore.databuilder.CommonValidatorTestBuilder.lowPricePurchase;
import static com.capgemini.onlineStore.databuilder.CommonValidatorTestBuilder.purchaseWithStatus;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerValidatorTest {

    private CustomerValidator validator;

    @Test
    public void shouldPassCustomerMoreExperiencedWithLowTotalPricePurchase() {
        CustomerTO customer = new CustomerTO.CustomerTOBuilder()
                .withPurchase(purchaseWithStatus(Status.COMPLETED))
                .withPurchase(purchaseWithStatus(Status.COMPLETED))
                .withPurchase(purchaseWithStatus(Status.COMPLETED))
                .build();
        PurchaseTO purchase = lowPricePurchase();

        try {
            validator.validate(customer, purchase);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void shouldPassCustomerMoreExperiencedWithHighTotalPricePurchase() {
        CustomerTO customer = new CustomerTO.CustomerTOBuilder()
                .withPurchase(purchaseWithStatus(Status.COMPLETED))
                .withPurchase(purchaseWithStatus(Status.COMPLETED))
                .withPurchase(purchaseWithStatus(Status.COMPLETED))
                .build();
        PurchaseTO purchase = highPricePurchase();

        try {
            validator.validate(customer, purchase);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void shouldPassCustomerLessExperiencedWithLowTotalPricePurchase() {
        CustomerTO customer = new CustomerTO.CustomerTOBuilder()
                .withPurchase(purchaseWithStatus(Status.COMPLETED))
                .withPurchase(purchaseWithStatus(Status.COMPLETED))
                .withPurchase(purchaseWithStatus(Status.IN_PROGRESS))
                .build();
        PurchaseTO purchase = lowPricePurchase();

        try {
            validator.validate(customer, purchase);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void shouldPassCustomerLessExperiencedWithHighTotalPricePurchase() {
        CustomerTO customer = new CustomerTO.CustomerTOBuilder()
                .withPurchase(purchaseWithStatus(Status.COMPLETED))
                .withPurchase(purchaseWithStatus(Status.COMPLETED))
                .withPurchase(purchaseWithStatus(Status.IN_PROGRESS))
                .build();
        PurchaseTO purchase = highPricePurchase();

        try {
            validator.validate(customer, purchase);
        } catch (Exception e) {
            fail();
        }
    }

    @Test(expected = InvalidPurchaseException.class)
    public void shouldFailCustomerLessExperiencedWithHighTotalPricePurchase() {
        CustomerTO customer = new CustomerTO.CustomerTOBuilder()
                .withPurchase(purchaseWithStatus(Status.COMPLETED))
                .withPurchase(purchaseWithStatus(Status.COMPLETED))
                .withPurchase(purchaseWithStatus(Status.COMPLETED))
                .build();
        PurchaseTO purchase = lowPricePurchase();

        validator.validate(customer, purchase);
    }
}
