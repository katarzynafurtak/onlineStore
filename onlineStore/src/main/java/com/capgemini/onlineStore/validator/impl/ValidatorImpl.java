package com.capgemini.onlineStore.validator.impl;

import com.capgemini.onlineStore.persistence.entity.OrderProductEntity;
import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;
import com.capgemini.onlineStore.service.PurchaseService;
import com.capgemini.onlineStore.validator.Validator;
import com.capgemini.onlineStore.validator.exception.InvalidPurchaseException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional(readOnly = true)
public class ValidatorImpl implements Validator {

    private PurchaseService purchaseService;

    @Override
    public void validateAmountOfPurchasedProducts(PurchaseEntity purchaseEntity) {
        if (!getListOfOrdersWithSpecificAmountAndProductPrice(purchaseEntity).isEmpty()) {
            throw new InvalidPurchaseException("Purchase cannot have more than 5 same products with unit Price bigger than 7000 zł each. ");
        }
    }

    private List<OrderProductEntity> getListOfOrdersWithSpecificAmountAndProductPrice(PurchaseEntity purchaseEntity) {
        return purchaseEntity.getOrders().stream()
                .filter(o->o.getAmount()>5 && o.getProduct().getPrice().compareTo(BigDecimal.valueOf(7000))>0)
                .collect(Collectors.toList());
    }


}
