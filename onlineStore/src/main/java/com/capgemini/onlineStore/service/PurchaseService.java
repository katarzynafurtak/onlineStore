package com.capgemini.onlineStore.service;

import com.capgemini.onlineStore.persistence.entity.ProductEntity;
import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;
import com.capgemini.onlineStore.to.CustomerTO;
import com.capgemini.onlineStore.to.PurchaseTO;

import java.math.BigDecimal;

public interface PurchaseService {

    PurchaseEntity savePurchase(CustomerTO customer, PurchaseTO purchase);

    public BigDecimal calculateCostOfOnePurchase(PurchaseEntity purchaseEntity);

    BigDecimal getTotalPriceOfOneProduct(ProductEntity productEntity);
}
