package com.capgemini.onlineStore.validator.impl;

import com.capgemini.onlineStore.to.CustomerTO;
import com.capgemini.onlineStore.to.OrderProductTO;
import com.capgemini.onlineStore.to.ProductTO;
import com.capgemini.onlineStore.to.PurchaseTO;
import com.capgemini.onlineStore.validator.Validator;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OneOrderWeightValidatorTest {

    private Validator validator = new OneOrderWeightValidator();

    @Test
    public void shouldPassForLightProduct() {
        PurchaseTO purchase = new PurchaseTO.PurchaseTOBuilder()
                .withOrder(new OrderProductTO.OrderProductTOBuilder()
                        .withAmount(1)
                        .withProduct(new ProductTO.ProductTOBuilder()
                                .withWeight(BigDecimal.valueOf(1))
                                .build())
                        .build())
                .build();

        try {
            validator.validate(null, purchase);
        } catch (Exception e) {
            fail();
        }
    }

}