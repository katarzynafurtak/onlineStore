package com.capgemini.onlineStore.service;

import com.capgemini.onlineStore.persistence.entity.ProductEntity;
import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;

import java.math.BigDecimal;

public interface PurchaseService {

    public BigDecimal calculateCostOfOnePurchase(PurchaseEntity purchaseEntity);

    BigDecimal getTotalPriceOfOneProduct(ProductEntity productEntity);
}
