package com.capgemini.onlineStore.validator.impl;

import com.capgemini.onlineStore.to.CustomerTO;
import com.capgemini.onlineStore.to.OrderProductTO;
import com.capgemini.onlineStore.to.PurchaseTO;
import com.capgemini.onlineStore.validator.Validator;
import com.capgemini.onlineStore.validator.exception.InvalidPurchaseException;
import org.springframework.stereotype.Component;

@Component
public class AmountOfTheSameProductValidator implements Validator {

    private static final int MAX_AMOUNT_OF_PRODUCTS = 5;
    private static final int SINGLE_PRODUCT_PRICE = 7000;

    @Override
    public void validate(CustomerTO customer, PurchaseTO purchase) {

        boolean invalidPurchase = purchase.getOrders().stream()
                .anyMatch(this::isOrderValid);

        if(invalidPurchase) {
            throw new InvalidPurchaseException(String.format("Purchase cannot have more than %s same products with unit price bigger than %s zł each.",
                    MAX_AMOUNT_OF_PRODUCTS, SINGLE_PRODUCT_PRICE));
        }
    }

    private boolean isOrderValid(OrderProductTO order) {
        return (order.getAmount() > MAX_AMOUNT_OF_PRODUCTS && order.getProduct().getPrice().doubleValue() > SINGLE_PRODUCT_PRICE);
    }
}
