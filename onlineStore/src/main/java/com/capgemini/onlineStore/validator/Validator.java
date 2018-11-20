package com.capgemini.onlineStore.validator;

import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;

public interface Validator {

    void validateAmountOfPurchasedProducts(PurchaseEntity purchaseEntity);
}
