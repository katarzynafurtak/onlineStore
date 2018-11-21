package com.capgemini.onlineStore.validator.impl;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.to.CustomerTO;
import com.capgemini.onlineStore.to.PurchaseTO;
import com.capgemini.onlineStore.validator.Validator;
import com.capgemini.onlineStore.validator.exception.InvalidPurchaseException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CustomerValidator implements Validator {

    private static final int MIN_AMOUNT_OF_COMPLETED_PURCHASES = 3;
    private static final int MAX_COST_OF_PURCHASE = 5000;

    @Override
    public void validate(CustomerTO customer, PurchaseTO purchase) {
        if(!(isNumberOfPurchasesRight(customer) || isTotalCostOfPurchaseSmallEnough(purchase))) {
            throw new InvalidPurchaseException(String.format("Customer that has less than %s completed purchases cannot order over %s zł.",
                    MIN_AMOUNT_OF_COMPLETED_PURCHASES, MAX_COST_OF_PURCHASE));
        }
    }

    private boolean isNumberOfPurchasesRight(CustomerTO customer) {
        long amountOfCompletedPurchases = customer.getPurchases().stream()
                .map(PurchaseTO::getStatus)
                .filter(status -> status == Status.COMPLETED)
                .count();
        return amountOfCompletedPurchases >= MIN_AMOUNT_OF_COMPLETED_PURCHASES;
    }

    private boolean isTotalCostOfPurchaseSmallEnough(PurchaseTO purchase) {
        BigDecimal totalCost = purchase.getOrders().stream()
                .map(o->o.getSellPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalCost.doubleValue() <= MAX_COST_OF_PURCHASE;
    }
}
