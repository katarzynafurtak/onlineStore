package com.capgemini.onlineStore.validator.impl;

import com.capgemini.onlineStore.to.CustomerTO;
import com.capgemini.onlineStore.to.OrderProductTO;
import com.capgemini.onlineStore.to.PurchaseTO;
import com.capgemini.onlineStore.validator.Validator;
import com.capgemini.onlineStore.validator.exception.InvalidOrderWeightException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OneOrderWeightValidator implements Validator {

    private static final int MAX_ORDER_WEIGHT = 25;

    @Override
    public void validate(CustomerTO customer, PurchaseTO purchase) {
        boolean invalidPurchase = purchase.getOrders().stream().anyMatch(this::isOrderWeightTooBig);

        if(invalidPurchase) {
            throw new InvalidOrderWeightException(String.format("This order weights more than %s kg.", MAX_ORDER_WEIGHT));
        }
    }

    private boolean isOrderWeightTooBig(OrderProductTO order) {
        return getTotalOrderWeight(order).doubleValue() > MAX_ORDER_WEIGHT;
    }

    private BigDecimal getTotalOrderWeight(OrderProductTO order) {
        return order.getProduct().getWeight().multiply(BigDecimal.valueOf(order.getAmount()));
    }
}
